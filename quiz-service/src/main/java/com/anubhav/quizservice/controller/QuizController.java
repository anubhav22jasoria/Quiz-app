package com.anubhav.quizservice.controller;

import com.anubhav.quizservice.model.QuestionWrapper;
import com.anubhav.quizservice.model.QuizDto;
import com.anubhav.quizservice.model.Response;
import com.anubhav.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;
    @PostMapping("create")
    public ResponseEntity<String> create(@RequestBody QuizDto quizDto) {
        return quizService.create(quizDto.getCategory(),quizDto.getNoOfQuestions(),quizDto.getTitle());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> responses){
        return quizService.calculate(id,responses);
    }
}
