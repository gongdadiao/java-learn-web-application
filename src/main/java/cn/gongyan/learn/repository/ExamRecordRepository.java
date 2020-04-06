package cn.gongyan.learn.repository;

import cn.gongyan.learn.beans.entity.ExamRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRecordRepository  extends JpaRepository<ExamRecord, String> {
}
