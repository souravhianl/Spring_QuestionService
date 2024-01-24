package com.questions.QuestionService.impl;

import com.questions.QuestionService.entities.Question;
import com.questions.QuestionService.repositories.QuestionRepository;
import com.questions.QuestionService.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question create(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public List<Question> get() {
        return questionRepository.findAll();
    }

    @Override
    public Question getOne(Long id) {
        return questionRepository.findById(id).orElseThrow(()-> new RuntimeException("Questions not found"));
    }

    @Override
    public List<Question> getQuestionOfQuiz(Long quizId) {
        return questionRepository.findByQuizId(quizId);
    }

    @Override
    public Question update(Long questionId, Question updatedQuestion) {
        if (updatedQuestion==null || questionId==null){
            throw new IllegalArgumentException("Question id and question must not be null");
        }
        Question existingQuestion = questionRepository.findById(questionId)
                .orElseThrow(()->new RuntimeException("Question not found with id " +questionId));
        existingQuestion.setQuestion(updatedQuestion.getQuestion());
        return questionRepository.save(existingQuestion);
    }
}

