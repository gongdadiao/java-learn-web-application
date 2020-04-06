/***********************************************************
 * @Description : 映射用户答卷信息，以用于后台进行批改（其实后带Qo更合适）
 * @author      : 龚研
 * @date        : 2019-05-16 23:40
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.beans.vo;

import lombok.Data;
import java.util.List;

@Data
public class OneExamVo {
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
    List<OneQuestionVo> danxuanList;
    List<OneQuestionVo> duoxuanList;
    List<OneQuestionVo> panduanList;
    List<OneQuestionVo> tiankongList;
    List<OneQuestionVo> wendaList;
}
