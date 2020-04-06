package cn.gongyan.learn.service;

public interface TimeService {
    //用户申请考试时，查看考试状态 只有没开始做和正在做
    String getExamStatus(String userId,String examId);
    //用户初次考试
    Long examTimerLogin(String userId,String examId,Long examDuration);
    //获取时间，返回给用户
    Long getRestTime(String userId,String examId);
    //用户交卷了
    Long examFinished(String userId,String examId,Long examDuration);
}
