/***********************************************************
 * @Description : 作业批改工具
 * @author      : 龚研
 * @date        : 2019-05-17 07:43
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.utils;

import cn.gongyan.learn.beans.jsonobject.OneQuestion;
import cn.gongyan.learn.beans.vo.OneQuestionVo;
import cn.gongyan.learn.common.ExamScore;
import org.springframework.beans.BeanUtils;
import java.util.ArrayList;
import java.util.List;

public class TestMark {
    /**
     * 一道题目是对是错
     * @param userAns
     * @param rightAns
     * @param type
     * @return
     */
    static Boolean rightOrWrong(String userAns,String rightAns,String type){
        if(type.compareTo("duoxuan")==0){
            String[] user_ans = userAns.split(",");
            String[] right_ans = rightAns.split(",");
            //选的选项数量不对，错误
            if(user_ans.length!=right_ans.length)
                return false;
            for (int i=0;i<user_ans.length;i++) {
                //选项不对应，错误
                if(user_ans[i].compareTo(right_ans[i])!=0)
                    return false;
            }
            return true;
        }else if(type.compareTo("panduan")==0 || type.compareTo("danxuan")==0) {
            if(userAns.compareTo(rightAns)==0)
                return true;
            else
                return false;
        }
        else if(type.compareTo("tiankong")==0 ){
            if(userAns.replace(" ","").compareTo(rightAns.replace(" ",""))==0)
                return true;
            else
                return false;
        }
        return true;
    }

    /**
     * 批改一队题目
     * @param questions 用户提交的题目
     * @param answers   题目答案
     * @param type      题目类型
     * @param score     总分统计
     * @param questionScore 当前题目类型的分数
     * @return
     */
    static public List<OneQuestion> markOneTypeOfQuestions(List<OneQuestionVo> questions, List<OneQuestion> answers, String type, ExamScore score, Integer questionScore){
        //鉴空
        if(questions==null || answers==null)
            return null;
        try {
            //鉴别是否题目数量等同
            if(questions.size()!=answers.size())
                throw  new Exception("答案校对出错");
        } catch (Exception e) {
            e.printStackTrace();
        }
        //如果没有该类题目就返回
        if(questions.size()<=0)
            return null;
        List<OneQuestion> results=new ArrayList<>();
        for (int i=0;i<questions.size();i++) {
            //创建该题目结果
            OneQuestion oneResult = new OneQuestion();
            BeanUtils.copyProperties(answers.get(i),oneResult);
            //如果该题目出现问题
            if(answers.get(i).getAnswer()==null){
                try {
                    throw new Exception(type+"批改出现问题:"+String.valueOf(i)+ answers.get(i).toString());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            OneQuestionVo oneUserAnswer = questions.get(i);
            OneQuestion oneRightAnswer = answers.get(i);
            if(rightOrWrong(oneUserAnswer.getUserAnswer(),oneRightAnswer.getAnswer(),type)){
                //批改正确
                oneResult.setFinalScore(questionScore);
                oneResult.setResult("right");
                oneResult.setUserAnswer(oneUserAnswer.getUserAnswer());
                score.add(questionScore);
            }else{
                //出现错误
                oneResult.setFinalScore(0);
                oneResult.setUserAnswer(oneUserAnswer.getUserAnswer());
                oneResult.setResult("wrong");
            }
            results.add(oneResult);
        }
        return  results;
    }
}
