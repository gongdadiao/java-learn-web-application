/***********************************************************
 * @Description : json解析考试信息
 * @author      : 龚研
 * @date        : 2019-05-16 23:40
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.beans.jsonobject;

import java.util.List;

public class OneExam {
    String examId;
    String examName;
    String examAvatar;
    String examDescription;
    Integer examScore;
    Integer examFinalScore;
    Integer examScoreDanxuan;
    Integer examScoreDuoxuan;
    Integer examScorePanduan;
    Integer examScoreTiankong;
    Integer examScoreWenda;
    Integer examTimeLimit;
    Integer examTimeCost;
    List<OneQuestion> danxuanList;
    List<OneQuestion> duoxuanList;
    List<OneQuestion> panduanList;
    List<OneQuestion> tiankongList;
    List<OneQuestion> wendaList;

    public Integer getExamTimeCost() {
        return examTimeCost;
    }

    public void setExamTimeCost(Integer examTimeCost) {
        this.examTimeCost = examTimeCost;
    }

    public Integer getExamFinalScore() {
        return examFinalScore;
    }

    public void setExamFinalScore(Integer examFinalScore) {
        this.examFinalScore = examFinalScore;
    }

    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getExamAvatar() {
        return examAvatar;
    }

    public void setExamAvatar(String examAvatar) {
        this.examAvatar = examAvatar;
    }

    public String getExamDescription() {
        return examDescription;
    }

    public void setExamDescription(String examDescription) {
        this.examDescription = examDescription;
    }

    public Integer getExamScore() {
        return examScore;
    }

    public void setExamScore(Integer examScore) {
        this.examScore = examScore;
    }

    public Integer getExamScoreDanxuan() {
        return examScoreDanxuan;
    }

    public void setExamScoreDanxuan(Integer examScoreDanxuan) {
        this.examScoreDanxuan = examScoreDanxuan;
    }

    public Integer getExamScoreDuoxuan() {
        return examScoreDuoxuan;
    }

    public void setExamScoreDuoxuan(Integer examScoreDuoxuan) {
        this.examScoreDuoxuan = examScoreDuoxuan;
    }

    public Integer getExamScorePanduan() {
        return examScorePanduan;
    }

    public void setExamScorePanduan(Integer examScorePanduan) {
        this.examScorePanduan = examScorePanduan;
    }

    public Integer getExamScoreTiankong() {
        return examScoreTiankong;
    }

    public void setExamScoreTiankong(Integer examScoreTiankong) {
        this.examScoreTiankong = examScoreTiankong;
    }

    public Integer getExamScoreWenda() {
        return examScoreWenda;
    }

    public void setExamScoreWenda(Integer examScoreWenda) {
        this.examScoreWenda = examScoreWenda;
    }

    public Integer getExamTimeLimit() {
        return examTimeLimit;
    }

    public void setExamTimeLimit(Integer examTimeLimit) {
        this.examTimeLimit = examTimeLimit;
    }

    public List<OneQuestion> getDanxuanList() {
        return danxuanList;
    }

    public void setDanxuanList(List<OneQuestion> danxuanList) {
        this.danxuanList = danxuanList;
    }

    public List<OneQuestion> getDuoxuanList() {
        return duoxuanList;
    }

    public void setDuoxuanList(List<OneQuestion> duoxuanList) {
        this.duoxuanList = duoxuanList;
    }

    public List<OneQuestion> getPanduanList() {
        return panduanList;
    }

    public void setPanduanList(List<OneQuestion> panduanList) {
        this.panduanList = panduanList;
    }

    public List<OneQuestion> getTiankongList() {
        return tiankongList;
    }

    public void setTiankongList(List<OneQuestion> tiankongList) {
        this.tiankongList = tiankongList;
    }

    public List<OneQuestion> getWendaList() {
        return wendaList;
    }

    public void setWendaList(List<OneQuestion> wendaList) {
        this.wendaList = wendaList;
    }
}
