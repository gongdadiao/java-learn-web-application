/***********************************************************
 * @Description : 学生完成考试记录表
 * @author      : 龚研
 * @date        : 2019-05-16 23:40
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.beans.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class ExamRecord {
    /**
     * 主键
     */
    @Id
    private String examRecordId;
    /**
     * 参与的考试的id
     */
    private String examId;

    /**
     * 参与者，即user的id
     */
    private String examJoinerId;
    /**
     * 参加考试的日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date examJoinDate;
    /**
     * 考试耗时(秒)
     */
    private Integer examTimeCost;
    /**
     * 考试得分
     */
    private Integer examJoinScore;

    private String examOptionUrl;
    /*
    exam
    homework
     */
    private String examOrHomework;

    private String examStatus;
}
