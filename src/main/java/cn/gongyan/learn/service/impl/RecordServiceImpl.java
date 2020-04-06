/***********************************************************
 * @Description : 记录服务
 * @author      : 龚研
 * @date        : 2019-05-16 23:40
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.service.impl;

import cn.gongyan.learn.beans.entity.Exam;
import cn.gongyan.learn.beans.entity.ExamRecord;
import cn.gongyan.learn.beans.entity.User;
import cn.gongyan.learn.beans.vo.ExamRecordVo;
import cn.gongyan.learn.beans.vo.RecordPageVo;
import cn.gongyan.learn.repository.ExamRecordRepository;
import cn.gongyan.learn.repository.ExamRepository;
import cn.gongyan.learn.repository.UserRepository;
import cn.gongyan.learn.service.RecordService;
import cn.gongyan.learn.utils.CommonValues;
import cn.gongyan.learn.utils.DownLoader;
import cn.hutool.core.util.IdUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RecordServiceImpl implements RecordService {
    ExamRecordRepository examRecordRepository;
    UserRepository userRepository;
    ExamRepository examRepository;

    public RecordServiceImpl(ExamRecordRepository examRecordRepository, UserRepository userRepository, ExamRepository examRepository) {
        this.examRecordRepository = examRecordRepository;
        this.userRepository = userRepository;
        this.examRepository = examRepository;
    }

    @Override
    public ExamRecord addBaijuanRecord(String userId, String examId) {
        String uuid = IdUtil.simpleUUID();
        ExamRecord examRecord=new ExamRecord();
        examRecord.setExamId(examId);
        examRecord.setExamJoinDate(new Date());
        examRecord.setExamJoinerId(userId);
        examRecord.setExamOrHomework("exam");
        examRecord.setExamRecordId(uuid);
        String status=CommonValues.examStatusBaijuan;
        examRecord.setExamStatus(status);
        examRecord.setExamJoinScore(0);
        examRecord.setExamOptionUrl("");
        examRecordRepository.save(examRecord);
        return examRecord;
    }

    /**
     * 更新记录的状态和url
     * @param id
     * @param status
     * @param url
     * @return
     */
    @Override
    public Boolean UpdateRecord(String id, String status, String url,Integer finalScore) {
        ExamRecord record = examRecordRepository.findById(id).orElse(null);
        record.setExamStatus(status);
        record.setExamOptionUrl(url);
        record.setExamJoinScore(finalScore);
        return true;
    }

    /**
     * 获得一个记录
     * @param userId
     * @param examId
     * @param type
     * @return
     */
    @Override
    public ExamRecord getOneRecord(String userId, String examId, String type) {
        ExamRecord record = new ExamRecord();
        record.setExamId(examId);
        record.setExamJoinerId(userId);
        record.setExamOrHomework(type);
        Example<ExamRecord> example = Example.of(record);
        ExamRecord result = examRecordRepository.findOne(example).orElse(null);
        return result;
    }

    /**
     * 获得所有等待批改的记录
     * @return
     */
    @Override
    public List<ExamRecord> getAll() {
        ExamRecord record = new ExamRecord();
        record.setExamStatus(CommonValues.examStatusWaitToBeMarked);
        Example<ExamRecord> of = Example.of(record);
        List<ExamRecord> all = examRecordRepository.findAll(of);
        return all;
    }



    @Override
    public List<ExamRecordVo> getUserExamRecords(String userId) {
        User user = userRepository.findById(userId).orElse(null);
        ExamRecord record = new ExamRecord();
        record.setExamOrHomework("exam");
        record.setExamJoinerId(userId);
        Example<ExamRecord> example = Example.of(record);
        List<ExamRecord> recordList = examRecordRepository.findAll(example);
        ArrayList<ExamRecordVo> recordVos = new ArrayList<>();
        for (final ExamRecord rec : recordList) {
            ExamRecordVo recordVo = new ExamRecordVo();
            recordVo.setUserUserName(user.getUserUsername());
            recordVo.setExamRecord(rec);
            final Exam examresult = examRepository.findById(rec.getExamId()).orElse(null);
            //对学生进行信息保密
            Exam exam = new Exam();
            BeanUtils.copyProperties(examresult,exam);
            exam.setExamQuestionsUrl(null);
            exam.setExamAnswersUrl(null);

            recordVo.setExam(exam);
            recordVos.add(recordVo);
        }
        return recordVos;
    }

    @Override
    public RecordPageVo getAllExamRecords(Integer pageNo, Integer pageSize) {
        // 获取考试列表
        // 按照日期降序排列
        Sort sort = new Sort(Sort.Direction.DESC, "examJoinDate");
        // 构造分页请求,注意前端面页面的分页是从1开始的，后端是从0开始地，所以要减去1哈
        PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize, sort);
        Page<ExamRecord> recordPage = examRecordRepository.findAll(pageRequest);
        RecordPageVo recordPageVo = new RecordPageVo();
        // 设置页码
        recordPageVo.setPageNo(pageNo);
        // 设置每页有多少条数据
        recordPageVo.setPageSize(pageSize);
        // 设置总共有多少个元素
        recordPageVo.setTotalCount(recordPage.getTotalElements());
        // 设置一共有多少页
        recordPageVo.setTotalPage(recordPage.getTotalPages());
        // 取出当前页的考试列表
        List<ExamRecord> recordList = recordPage.getContent();
        recordPageVo.setRecordVoList(recordList);
        return recordPageVo;
    }

    @Override
    public String getStudentOptions(String id) {
        ExamRecord record = examRecordRepository.findById(id).orElse(null);
        return DownLoader.download(record.getExamOptionUrl());
    }


}
