/***********************************************************
 * @Description : json解析题目信息
 * @author      : 龚研
 * @date        : 2019-05-16 23:40
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.beans.jsonobject;

import java.util.List;

public class OneQuestion {
    Integer questionId;
    String questionHshId;
    String questionContent;
    String questionType;
    List<String> questionOptions;
    Integer score;
    Integer finalScore;
    String userAnswer;
    String answer;
    String result;
    String questionDescription;

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    @Override
    public String toString() {
        return "OneQuestion{" +
                "questionId=" + questionId +
                ", questionHshId='" + questionHshId + '\'' +
                ", questionContent='" + questionContent + '\'' +
                ", questionType='" + questionType + '\'' +
                ", questionOptions=" + questionOptions +
                ", score=" + score +
                ", finalScore=" + finalScore +
                ", userAnswer='" + userAnswer + '\'' +
                ", answer='" + answer + '\'' +
                ", result='" + result + '\'' +
                '}';
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestionHshId() {
        return questionHshId;
    }

    public void setQuestionHshId(String questionHshId) {
        this.questionHshId = questionHshId;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public List<String> getQuestionOptions() {
        return questionOptions;
    }

    public void setQuestionOptions(List<String> questionOptions) {
        this.questionOptions = questionOptions;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(Integer finalScore) {
        this.finalScore = finalScore;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
