package cn.gongyan.learn.repository;

import cn.gongyan.learn.beans.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
