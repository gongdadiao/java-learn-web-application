package cn.gongyan.learn.service;

import cn.gongyan.learn.beans.entity.Question;
import cn.gongyan.learn.beans.jsonobject.OneQuestion;

import java.util.List;

public interface QuestionService {
    List<Question> getQuestions(Integer number);
    Integer createOneQuestion(OneQuestion oneQuestion);
}
