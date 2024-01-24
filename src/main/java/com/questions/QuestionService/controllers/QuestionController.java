package com.questions.QuestionService.controllers;

import com.questions.QuestionService.entities.Question;
import com.questions.QuestionService.services.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/question")
public class QuestionController {
    private QuestionService questionService;

    public QuestionController(QuestionService questionService){
        this.questionService = questionService;
    }
    @PostMapping
    public Question create(@RequestBody Question question){
        return questionService.create(question);
    }

    @GetMapping
    public List<Question> getAll(){
        return questionService.get();
    }
    @GetMapping("/{questionId}")
    public Question getOne( @PathVariable Long questionId){
        return questionService.getOne(questionId);
    }
    //Get all questions of specific quiz
    @GetMapping("/quiz/{quizId}")
    public List<Question> getQuestionOfQuiz(@PathVariable Long quizId){
        return questionService.getQuestionOfQuiz(quizId);
    }
    @PutMapping("/{questionId}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long questionId,@RequestBody Question question){
        try{
            Question updatedQuestion = questionService.update(questionId,question);
            return ResponseEntity.ok(updatedQuestion);
        }catch (RuntimeException ex){
            return ResponseEntity.notFound().build();
        }
    }
}
