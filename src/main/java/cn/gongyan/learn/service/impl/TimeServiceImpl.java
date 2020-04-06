/***********************************************************
 * @Description : 计时服务，目前针对考试计时
 * @author      : 龚研
 * @date        : 2019-05-16 23:40
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.service.impl;

import cn.gongyan.learn.service.RecordService;
import cn.gongyan.learn.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class TimeServiceImpl implements TimeService {
    @Autowired
    RecordService examService;

    Hashtable<String ,Date> timeHashTable=new Hashtable<>();

    @Override
    synchronized public String getExamStatus(String userId, String examId) {
        boolean contains = timeHashTable.containsKey(userId + "-" + examId);
        if(contains){
            //若存在
            return "doing";
        }else {
            //若不存在
            return "wait";
        }
    }

    @Override
    synchronized public Long examTimerLogin(String userId, String examId, Long examDuration) {
        //examDuration以分钟为单位
        timeHashTable.put(userId+"-"+examId,new Date());
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //查看map中是否还有考试
                boolean b = timeHashTable.containsKey(userId + "-" + examId);
                if(b){
                    //还在map中存在
                    System.out.println("用户没有按时交，记0分");
                    timeHashTable.remove(userId + "-" + examId);
                    examService.addBaijuanRecord(userId,examId);
                }
            }
        },examDuration*60*1000);
        //返回秒
        return examDuration*60;
    }

    @Override
    synchronized public Long getRestTime(String userId, String examId) {
        boolean b = timeHashTable.containsKey(userId + "-" + examId);
        if(b){
            //若存在，表示在做
            Date date = timeHashTable.get(userId + "-" + examId);
            long duration = new Date().getTime() - date.getTime();
            System.out.println("考试已经过了:");
            System.out.println(duration/1000);
            return duration/1000;
        }else{
            try {
                throw new Exception("查看剩余时间的时候出错");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0L;
        }
    }

    @Override
    synchronized public Long examFinished(String userId, String examId,Long examDuration) {
        boolean b = timeHashTable.containsKey(userId + "-" + examId);
        if(b){
            Date date = timeHashTable.get(userId + "-" + examId);
            timeHashTable.remove(userId + "-" + examId);
            long duration = new Date().getTime() - date.getTime();
            return duration;
        }else {
            return examDuration*1000*60;
        }
    }


}
