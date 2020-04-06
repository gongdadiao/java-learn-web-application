package cn.gongyan.learn.service;

import cn.gongyan.learn.beans.entity.Exam;
import cn.gongyan.learn.beans.entity.ExamRecord;
import cn.gongyan.learn.beans.entity.Question;
import cn.gongyan.learn.beans.jsonobject.OneExam;
import cn.gongyan.learn.beans.vo.*;
import java.util.List;

public interface ExamService {
    ExamDetailVo getResultDetail(ExamRecord record);

    /**
     * 获取问题的列表
     *
     * @param pageNo   页码编号
     * @param pageSize 页面大小
     * @return 考试页面对象
     */
    ExamPageVo getExamList(Integer pageNo, Integer pageSize);

    ExamDetailVo getExamDetail(String id,String userId);

    /**
     * 获取考试卡片列表
     *
     * @return 考试卡片列表
     */
    List<ExamCardVo> getExamCardList();

    Exam createExam(OneExam oneExam,String userId);

    ExamRecord addExamRecord(OneExamVo oneExam, String userId, String examId);
    ExamRecord addTestRecord(List<Question> questions,String userId,String testId);
}
