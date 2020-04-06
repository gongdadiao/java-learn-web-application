/***********************************************************
 * @Description : 考试服务
 * @author      : 龚研
 * @date        : 2020-03-31 08:04
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.service.impl;

import cn.gongyan.learn.beans.entity.Exam;
import cn.gongyan.learn.beans.entity.ExamRecord;
import cn.gongyan.learn.beans.entity.Question;
import cn.gongyan.learn.beans.entity.Resource;
import cn.gongyan.learn.beans.jsonobject.OneExam;
import cn.gongyan.learn.beans.jsonobject.OneQuestion;
import cn.gongyan.learn.beans.vo.*;
import cn.gongyan.learn.common.ExamScore;
import cn.gongyan.learn.repository.*;
import cn.gongyan.learn.service.ExamService;
import cn.gongyan.learn.service.TimeService;
import cn.gongyan.learn.utils.CommonValues;
import cn.gongyan.learn.utils.DownLoader;
import cn.gongyan.learn.utils.TestMark;
import cn.gongyan.learn.utils.UpLoader;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class ExamServiceImpl implements ExamService {
    private final ResourceRepository resourceRepository;
    private final RecordRepository recordRepository;
    private final ExamRepository examRepository;
    private final UserRepository userRepository;
    private final TimeService timeService;
    private final QuestionRepository questionRepository;

    /**
     * 进行依赖注入
     * @param resourceRepository
     * @param recordRepository
     * @param examRepository
     * @param userRepository
     * @param timeService
     * @param questionRepository
     */
    public ExamServiceImpl(ResourceRepository resourceRepository, RecordRepository recordRepository, ExamRepository examRepository, UserRepository userRepository, TimeService timeService, QuestionRepository questionRepository) {
        this.resourceRepository = resourceRepository;
        this.recordRepository = recordRepository;
        this.examRepository = examRepository;
        this.userRepository=userRepository;
        this.timeService = timeService;
        this.questionRepository = questionRepository;
    }

    /**
     *
     * @param pageNo   页码编号
     * @param pageSize 页面大小
     * @return
     */
    @Override
    public ExamPageVo getExamList(Integer pageNo, Integer pageSize) {
        // 按照日期降序排列
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        // 构造分页请求,注意前端面页面的分页是从1开始的，后端是从0开始地，所以要减去1
        PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize, sort);
        Page<Exam> examPage = examRepository.findAll(pageRequest);
        ExamPageVo examPageVo = new ExamPageVo();
        // 设置页码
        examPageVo.setPageNo(pageNo);
        // 设置每页有多少条数据
        examPageVo.setPageSize(pageSize);
        // 设置总共有多少个元素
        examPageVo.setTotalCount(examPage.getTotalElements());
        // 设置一共有多少页
        examPageVo.setTotalPage(examPage.getTotalPages());
        // 取出当前页的考试列表
        List<Exam> examList = examPage.getContent();
        // 需要自定义的exam列表
        List<ExamVo> examVoList = new ArrayList<>();
        // 循环完成每个属性的定制
        for (Exam exam : examList) {
            ExamVo examVo = new ExamVo();
            // 先尽量复制能复制的所有属性
            BeanUtils.copyProperties(exam, examVo);
            // 设置问题的创建者
            examVo.setExamCreator(
                    Objects.requireNonNull(
                            userRepository.findById(
                                    exam.getExamCreatorId()
                            ).orElse(null)
                    ).getUserUsername()
            );
            // 把examVo加到examVoList中
            examVoList.add(examVo);
        }
        examPageVo.setExamVoList(examVoList);
        return examPageVo;
    }

    /**
     *  得到学生考试记录，获取批改结果，显示给学生
     * @param record
     * @return
     */
    public ExamDetailVo getResultDetail(ExamRecord record) {
        ExamDetailVo examDetailVo=new ExamDetailVo();
        final Exam examresult = examRepository.findById(record.getExamId()).orElse(null);
        assert examresult != null;
        Exam exam = new Exam();
        BeanUtils.copyProperties(examresult,exam);
        //对学生进行保密
        exam.setExamAnswersUrl(null);
        exam.setExamQuestionsUrl(null);
        examDetailVo.setExam(exam);
        String download = DownLoader.download(record.getExamOptionUrl());
        OneExam oneExam = JSONObject.parseObject(download, OneExam.class);
        examDetailVo.setDanxuanList(this.convert(oneExam.getDanxuanList()));
        examDetailVo.setDuoxuanList(this.convert(oneExam.getDuoxuanList()));
        examDetailVo.setPanduanList(this.convert(oneExam.getPanduanList()));
        examDetailVo.setTiankongList(this.convert(oneExam.getTiankongList()));
        examDetailVo.setWendaList(this.convert(oneExam.getWendaList()));
        return examDetailVo;
    }

    /**
     *
     * @param id
     * @param userId
     * @return
     */
    @Override
    public ExamDetailVo getExamDetail(String id,String userId) {
        final Exam exam = examRepository.findById(id).orElse(null);
        assert exam != null;
        String status = timeService.getExamStatus(userId, id);
        //获取需要显示的考试剩余时间
        int testTime = exam.getExamTimeLimit();
        testTime*=60;
        if(status.compareTo("doing")==0){
            //已经点开过考试了
            Long timePast = timeService.getRestTime(userId, id);
            testTime = testTime - timePast.intValue();
        }else{
            timeService.examTimerLogin(userId,id,exam.getExamTimeLimit().longValue());
        }
        ExamDetailVo examDetailVo=new ExamDetailVo();
        Exam newExam = new Exam();
        BeanUtils.copyProperties(exam,newExam);
        examDetailVo.setExam(newExam);
        examDetailVo.setRestSeconds(testTime);
        String text = DownLoader.download(exam.getExamQuestionsUrl());
        OneExam oneExam = JSONObject.parseObject(text, OneExam.class);
        //使用convertForStudent，避免多余信息泄露
        examDetailVo.setDanxuanList(this.convertForStudent(oneExam.getDanxuanList()));
        examDetailVo.setDuoxuanList(this.convertForStudent(oneExam.getDuoxuanList()));
        examDetailVo.setPanduanList(this.convertForStudent(oneExam.getPanduanList()));
        examDetailVo.setTiankongList(this.convertForStudent(oneExam.getTiankongList()));
        examDetailVo.setWendaList(this.convertForStudent(oneExam.getWendaList()));
        return examDetailVo;
    }

    /**
     *
     * @return
     */
    @Override
    public List<ExamCardVo> getExamCardList() {
        List<Exam> exams = examRepository.findAll();
        ArrayList<ExamCardVo> list = new ArrayList<>();
        for (Exam ex : exams) {
            ExamCardVo cardVo = new ExamCardVo();
            BeanUtils.copyProperties(ex,cardVo);
            list.add(cardVo);
        }
        return list;
    }

    /**
     *  创建考试
     * @param oneExam 创建者提供的信息模板
     * @param userId
     * @return
     */
    @Override
    public Exam createExam(OneExam oneExam, String userId) {
        String uuid = IdUtil.simpleUUID();
        oneExam.setExamId(uuid);
        Integer final_score=0;
        int i1 = oneExam.getDanxuanList().size() * oneExam.getExamScoreDanxuan();
        int i2 = oneExam.getDuoxuanList().size() * oneExam.getExamScoreDuoxuan();
        int i3 = oneExam.getPanduanList().size() * oneExam.getExamScorePanduan();
        int i4 = oneExam.getTiankongList().size() * oneExam.getExamScoreTiankong();
        int i5 = oneExam.getWendaList().size() * oneExam.getExamScoreWenda();
        final_score=i1+i2+i3+i4+i5;
        oneExam.setExamScore(final_score);
        Exam exam=new Exam();
        BeanUtils.copyProperties(oneExam,exam);
        exam.setExamCreatorId(userId);
        exam.setCreateTime(new Date());
        exam.setUpdateTime(new Date());
        exam.setExamStartDate(new Date());
        exam.setExamEndDate(new Date());
        String url = UpLoader.uploadQiniuText("exam", JSON.toJSONString(oneExam), uuid + "-" + userId);
        exam.setExamQuestionsUrl(url);
        exam.setExamScore(final_score);

        examRepository.save(exam);
        return exam;
    }

    /**
     *  批改考试
     * @param examId
     * @param UserAnswer
     * @return
     */
    OneExam markingTest(String examId,OneExamVo UserAnswer){
        OneExam oneExam=new OneExam();
        Exam exam = this.examRepository.findById(examId).orElse(null);
        String url = exam.getExamQuestionsUrl();
        String download = DownLoader.download(url);
        OneExam examInfo = JSONObject.parseObject(download, OneExam.class);
        oneExam.setExamId(examInfo.getExamId());
        oneExam.setExamScore(examInfo.getExamScore());
        oneExam.setExamAvatar(examInfo.getExamAvatar());
        oneExam.setExamDescription(examInfo.getExamDescription());
        oneExam.setExamName(examInfo.getExamName());
        oneExam.setExamScoreDanxuan(examInfo.getExamScoreDanxuan());
        oneExam.setExamScoreDuoxuan(examInfo.getExamScoreDuoxuan());
        oneExam.setExamScorePanduan(examInfo.getExamScorePanduan());
        oneExam.setExamScoreTiankong(examInfo.getExamScoreTiankong());
        oneExam.setExamScoreWenda(examInfo.getExamScoreWenda());
        oneExam.setExamTimeLimit(examInfo.getExamTimeLimit());
        oneExam.setExamTimeCost(UserAnswer.getExamTimeCost());
        //对四种题型进行批改
        ExamScore score=new ExamScore();
        score.setExamScore(examInfo.getExamScore());
        List<OneQuestion> danxuanList = TestMark.markOneTypeOfQuestions(UserAnswer.getDanxuanList(), examInfo.getDanxuanList(),"danxuan",score,examInfo.getExamScoreDanxuan());
        List<OneQuestion> duoxuanList = TestMark.markOneTypeOfQuestions(UserAnswer.getDuoxuanList(), examInfo.getDuoxuanList(),"duoxuan",score,examInfo.getExamScoreDuoxuan());
        List<OneQuestion> panduanList = TestMark.markOneTypeOfQuestions(UserAnswer.getPanduanList(), examInfo.getPanduanList(),"panduan",score,examInfo.getExamScorePanduan());
        List<OneQuestion> tiankongList = TestMark.markOneTypeOfQuestions(UserAnswer.getTiankongList(), examInfo.getTiankongList(),"tiankong",score,examInfo.getExamScoreTiankong());
        oneExam.setExamFinalScore(score.getCurrentScore());
        //题目列表装入
        oneExam.setDanxuanList(danxuanList);
        oneExam.setDuoxuanList(duoxuanList);
        oneExam.setPanduanList(panduanList);
        oneExam.setTiankongList(tiankongList);
        oneExam.setWendaList(vconvert(UserAnswer.getWendaList()));
        return oneExam;
    }

    /**
     * 批改并添加记录
     * @param oneExam
     * @param userId
     * @param examId
     * @return
     */
    @Override
    public ExamRecord addExamRecord(OneExamVo oneExam,String userId,String examId) {
        String uuid = IdUtil.simpleUUID();
        ExamRecord examRecord=new ExamRecord();
        examRecord.setExamId(examId);
        examRecord.setExamJoinDate(new Date());
        examRecord.setExamJoinerId(userId);
        examRecord.setExamTimeCost(oneExam.getExamTimeCost());
        examRecord.setExamOrHomework("exam");
        examRecord.setExamRecordId(uuid);
        //批改
        OneExam result = this.markingTest(examId, oneExam);
        String status;
        if(result.getWendaList().size()>0){
            status= CommonValues.examStatusWaitToBeMarked;
        }else {
            status=CommonValues.examStatusComplete;
        }
        examRecord.setExamStatus(status);
        examRecord.setExamJoinScore(result.getExamFinalScore());
        String url1 = UpLoader.uploadQiniuText("result", JSON.toJSONString(result), examId);
        examRecord.setExamOptionUrl(url1);
        this.recordRepository.save(examRecord);

        return examRecord;
    }

    @Override
    public ExamRecord addTestRecord(List<Question> questions, String userId, String testId) {
        OneExam oneExam = new OneExam();
        List<OneQuestion> danxuans = new ArrayList<>();
        List<OneQuestion> panduans = new ArrayList<>();
        List<OneQuestion> duoxuans = new ArrayList<>();
        Resource homework = resourceRepository.findById(testId).orElse(null);
        String ids = homework.getResUrl();
        String[] idList = ids.split(",");
        if(idList.length!=questions.size()){
            try {
                throw  new Exception("作业批改出现错误");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
     //   ArrayList<OneQuestion> results = new ArrayList<>();
        Integer rightNum=questions.size();
        for (int i=0;i<idList.length;i++) {
            String id=idList[i];
            Question ans = questionRepository.findById(Integer.parseInt(id)).orElse(null);
            Question users=questions.get(i);
            if(users.getQuestionId()!=ans.getQuestionId()){
                try {
                    throw new Exception("作业批改出现问题");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            OneQuestion res = new OneQuestion();
            res.setQuestionId(ans.getQuestionId());
            res.setQuestionContent(ans.getQuestionContent());
            res.setQuestionType(ans.getQuestionTypeName());
            res.setUserAnswer(users.getQuestionAnswers());
            res.setQuestionDescription(ans.getQuestionSelections());
            res.setAnswer(ans.getQuestionAnswers());
            if(ans.getQuestionAnswers().compareTo(users.getQuestionAnswers())!=0){
                 rightNum--;
            }
            if(ans.getQuestionTypeName().compareTo("danxuan")==0){
                danxuans.add(res);
            }else if(ans.getQuestionTypeName().compareTo("panduan")==0){
                panduans.add(res);
            }else if(ans.getQuestionTypeName().compareTo("duoxuan")==0){
                duoxuans.add(res);
            }
        }
        Float a = new Float(rightNum);
        Float b = new Float(idList.length);
        oneExam.setExamFinalScore((int)(a/b*100f));
        oneExam.setDanxuanList(danxuans);
        oneExam.setPanduanList(panduans);
        oneExam.setDuoxuanList(duoxuans);
        oneExam.setExamId(testId);

        String key = UpLoader.uploadQiniuText("result", JSON.toJSONString(oneExam), testId);
        ExamRecord record = new ExamRecord();
        record.setExamOptionUrl(key);
        record.setExamStatus("complete");
        record.setExamOrHomework("homework");
        record.setExamId(testId);
        record.setExamJoinerId(userId);
        record.setExamJoinScore((int)(a/b*100f));
        record.setExamRecordId(IdUtil.simpleUUID());
        record.setExamJoinDate(new Date());
        recordRepository.save(record);
        return record;
    }


    public List<OneQuestion> vconvert(List<OneQuestionVo> vlist){
        if(vlist==null)
            return null;
        List<OneQuestion> result=new ArrayList<>();
        for (OneQuestionVo item : vlist) {
            OneQuestion question = new OneQuestion();
            BeanUtils.copyProperties(item,question);
            question.setQuestionId(item.getId());
            question.setQuestionOptions(item.getOptions());
            question.setQuestionType(item.getType());
            question.setQuestionContent(item.getQuestionContent());
            result.add(question);
        }
        return result;
    }

    public List<OneQuestionVo> convertForStudent(List<OneQuestion> list){
        if(list==null)
            return null;
        List<OneQuestionVo> result=new ArrayList<>();
        for (OneQuestion oneQuestion : list) {
            OneQuestionVo oneQuestionVo=new OneQuestionVo();
            oneQuestionVo.setId(oneQuestion.getQuestionId());
            oneQuestionVo.setQuestionContent(oneQuestion.getQuestionContent());
            oneQuestionVo.setScore(oneQuestion.getScore());
            oneQuestionVo.setOptions(oneQuestion.getQuestionOptions());
            oneQuestionVo.setType(oneQuestion.getQuestionType());
            oneQuestionVo.setUserAnswer(oneQuestion.getUserAnswer());
            result.add(oneQuestionVo);
        }
        return result;
    }


    public List<OneQuestionVo> convert(List<OneQuestion> list){
        if(list==null)
            return null;
        List<OneQuestionVo> result=new ArrayList<>();
        for (OneQuestion oneQuestion : list) {
            OneQuestionVo oneQuestionVo=new OneQuestionVo();
            oneQuestionVo.setId(oneQuestion.getQuestionId());
            oneQuestionVo.setQuestionContent(oneQuestion.getQuestionContent());
            oneQuestionVo.setScore(oneQuestion.getScore());
            //学生最后获得的分数（问答题需要这个数据）
            oneQuestionVo.setFinalScore(oneQuestion.getFinalScore());
            oneQuestionVo.setOptions(oneQuestion.getQuestionOptions());
            oneQuestionVo.setHashId(oneQuestion.getQuestionHshId());
            oneQuestionVo.setType(oneQuestion.getQuestionType());
            oneQuestionVo.setRightAnswer(oneQuestion.getAnswer());
            oneQuestionVo.setUserAnswer(oneQuestion.getUserAnswer());
            result.add(oneQuestionVo);
        }
        return result;
    }
}
