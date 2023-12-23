package com.example.quiztest.project.controller;

import com.example.quiztest.project.base.ApiResponse;
import com.example.quiztest.project.dto.QuestionRequest;
import com.example.quiztest.project.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("question")
public class QuestionController {
    private final QuestionService service;

    public QuestionController(QuestionService service) {
        this.service = service;
    }
    @PostMapping("{categoryId}")
    public ResponseEntity<?> create(@PathVariable Long categoryId, @Valid @RequestBody QuestionRequest request){
        return ApiResponse.controller(service.create(request,categoryId));
    }
}
