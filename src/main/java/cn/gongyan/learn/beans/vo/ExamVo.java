/***********************************************************
 * @Description : 服务器发送给教师的考试信息
 * @author      : 龚研
 * @date        : 2019-05-16 23:40
 * @qq          : 1085704190
 ***********************************************************/

package cn.gongyan.learn.beans.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Date;

@Data
public class ExamVo {
    @JsonProperty("id")
    private String examId;
    @JsonProperty("name")
    private String examName;
    @JsonProperty("avatar")
    private String examAvatar;
    @JsonProperty("desc")
    private String examDescription;

    @JsonProperty("url")
    private String examQuestionsUrl;

    @JsonProperty("score")
    private Integer examScore;

    @JsonProperty("danxuanScore")
    private Integer examScoreDanxuan;

    @JsonProperty("duoxuanScore")
    private Integer examScoreDuoxuan;

    @JsonProperty("panduanScore")
    private Integer examScorePanduan;

    @JsonProperty("tiankongScore")
    private Integer examScoreTiankong;

    @JsonProperty("wendaScore")
    private Integer examScoreWenda;
    /**
     * 考试的创建人，根据id从用户表中查取姓名
     */
    @JsonProperty("creator")
    private String examCreator;

    /**
     * 考试限制的时间，单位为分钟
     */
    @JsonProperty("elapse")
    private Integer examTimeLimit;

    /**
     * 开始时间
     */
    @JsonProperty("startDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date examStartDate;

    /**
     * 结束时间
     */
    @JsonProperty("endDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date examEndDate;

    /**
     * 创建时间
     */
    @JsonProperty("createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonProperty("updateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;


}
