package com.example.quiztest.project.controller;

import com.example.quiztest.project.base.ApiResponse;
import com.example.quiztest.project.dto.QuizRequest;
import com.example.quiztest.project.service.QuestionService;
import com.example.quiztest.project.service.QuizService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("quiz")
public class QuizController {
    private final QuizService service;

    public QuizController(QuizService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestParam Short pageSize,@Valid @RequestBody QuizRequest request){
        return ApiResponse.controller(service.create(request,pageSize));
    }
}
