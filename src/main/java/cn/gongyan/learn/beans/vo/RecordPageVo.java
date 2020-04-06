/***********************************************************
 * @Description : 展示给教师的每页学生完成记录列表
 * @author      : 龚研
 * @date        : 2019-05-16 23:40
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.beans.vo;

import cn.gongyan.learn.beans.entity.ExamRecord;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class RecordPageVo {
    /**
     * 分页时每个分页的大小
     */
    private Integer pageSize;

    /**
     * 当前是在第几页，注意要比前端传过来地小1
     */
    private Integer pageNo;

    /**
     * 一共有多少条符合条件的记录
     */
    private Long totalCount;

    /**
     * 一共有多少页
     */
    private Integer totalPage;

    /**
     * 当前页的详细数据
     */
    @JsonProperty("data")
    private List<ExamRecord> recordVoList;
}
