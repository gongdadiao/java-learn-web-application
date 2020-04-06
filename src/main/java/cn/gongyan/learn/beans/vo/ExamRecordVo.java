package cn.gongyan.learn.beans.vo;

import cn.gongyan.learn.beans.entity.Exam;
import cn.gongyan.learn.beans.entity.ExamRecord;
import lombok.Data;

@Data
public class ExamRecordVo {
    Exam exam;
    ExamRecord examRecord;
    String userUserName;
}
