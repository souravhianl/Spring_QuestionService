package com.questions.QuestionService.services;

import com.questions.QuestionService.entities.Question;

import java.util.List;

public interface QuestionService {
    Question create(Question question);
    List<Question> get();
    Question getOne(Long id);

    List<Question> getQuestionOfQuiz(Long quizId);

    Question update(Long questionId, Question question);
}
