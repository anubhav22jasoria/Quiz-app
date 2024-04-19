package com.anubhav.quizapp.controller;

import com.anubhav.quizapp.model.QuestionWrapper;
import com.anubhav.quizapp.model.Response;
import com.anubhav.quizapp.service.QuizService;
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
    public ResponseEntity<String> create(@RequestParam String category,@RequestParam int noOfQuestions,@RequestParam String title) {
        return quizService.create(category,noOfQuestions,title);
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
