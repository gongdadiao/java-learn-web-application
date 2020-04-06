/***********************************************************
 * @Description : 服务器发送给客户端准备考试的学生的考试信息
 * @author      : 龚研
 * @date        : 2019-05-16 23:40
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.beans.vo;

import cn.gongyan.learn.beans.entity.Exam;
import lombok.Data;
import java.util.List;

@Data
public class ExamDetailVo {
    /**
     * 考试的基本信息对象
     */
    private Exam exam;
    private Integer restSeconds;
    private List<OneQuestionVo> danxuanList;
    private List<OneQuestionVo> duoxuanList;
    private List<OneQuestionVo> panduanList;
    private List<OneQuestionVo> tiankongList;
    private List<OneQuestionVo> wendaList;
}
