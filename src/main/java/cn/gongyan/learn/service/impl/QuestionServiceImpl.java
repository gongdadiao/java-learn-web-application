/***********************************************************
 * @Description : 问题服务
 * @author      : 龚研
 * @date        : 2019-05-16 23:40
 * @qq          : 1085704190
 ***********************************************************/
package cn.gongyan.learn.service.impl;

import cn.gongyan.learn.beans.entity.Question;
import cn.gongyan.learn.beans.jsonobject.OneQuestion;
import cn.gongyan.learn.repository.QuestionRepository;
import cn.gongyan.learn.service.QuestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {
    QuestionRepository questionRepository;
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> getQuestions(Integer number) {
        PageRequest request = new PageRequest(0, number);
        Page<Question> all = questionRepository.findAll(request);
        List<Question> questionList = all.getContent();
        return questionList;
    }

    @Override
    public Integer createOneQuestion(OneQuestion oneQuestion) {
        Question question = new Question();
        question.setQuestionAnswers(oneQuestion.getAnswer());
        question.setQuestionContent(oneQuestion.getQuestionContent());
        question.setQuestionDescription(oneQuestion.getQuestionDescription());
        question.setQuestionOptionSplit("#$");
        String options="";
        String type=oneQuestion.getQuestionType();
        if(type.compareTo("duoxuan")==0 || type.compareTo("danxuan")==0) {
            for (String questionOption : oneQuestion.getQuestionOptions()) {
                options = options + "#$" + questionOption;
            }
            if(options.length()>0)
                options=options.substring(2);
        }
        question.setQuestionSelections(options);
        question.setQuestionTypeName(oneQuestion.getQuestionType());
        Question save = questionRepository.save(question);
        return (save==null)?0:1;
    }
}
