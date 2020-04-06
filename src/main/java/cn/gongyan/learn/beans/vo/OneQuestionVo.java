/***********************************************************
 * @Description : 题目展示给送给学生来考试
 * @author      : 龚研
 * @date        : 2019-05-16 23:40
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.beans.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
public class OneQuestionVo {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("hashid")
    private String hashId;
    @JsonProperty("content")
    private String questionContent;
    @JsonProperty("score")
    private Integer score;
    @JsonProperty("options")
    private List<String> options;
    @JsonProperty("type")
    private String type;
    @JsonProperty("userAnswer")
    private String userAnswer;
    @JsonProperty("rightAnswer")
    private String rightAnswer;
    private Integer FinalScore;
}
