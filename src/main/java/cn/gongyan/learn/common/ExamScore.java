/***********************************************************
 * @Description : 学生分数封装类
 * @author      : 龚研
 * @date        : 2020-04-1 23:19
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.common;

import lombok.Data;

@Data
public class ExamScore {
    int currentScore=0;
    int examScore;
    public void add(int questionScore){
        this.currentScore+=questionScore;
    }
    public void sub(int questionScore){
        this.currentScore-=questionScore;
    }
}
