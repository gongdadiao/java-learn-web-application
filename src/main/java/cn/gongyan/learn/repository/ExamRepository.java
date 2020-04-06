package cn.gongyan.learn.repository;

import cn.gongyan.learn.beans.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, String> {
}
