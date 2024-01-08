package com.example.quiztest.project.controller;

import com.example.quiztest.project.base.ApiResponse;
import com.example.quiztest.project.dto.CheckRequest;
import com.example.quiztest.project.dto.QuizRequest;
import com.example.quiztest.project.service.QuestionService;
import com.example.quiztest.project.service.QuizService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<?> create(@Valid @RequestBody QuizRequest request){
        return ApiResponse.controller(service.create(request));
    }
    @GetMapping("{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        return ApiResponse.controller(service.getOne(id));
    }

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable){
        return ApiResponse.controller(service.getAll(pageable));
    }

    @GetMapping("by-category")
    public ResponseEntity<?> getByCategory(@RequestParam String categoryName,Pageable pageable){
        return ApiResponse.controller(service.getAllByCategory(categoryName,pageable));
    }

    @GetMapping("take-test/{quizId}")
    public ResponseEntity<?> takeTest(@PathVariable Long quizId){
        return ApiResponse.controller(service.takeTest(quizId));
    }

    @GetMapping("check-quiz/{quizId}")
    public ResponseEntity<?> checkQuiz(@PathVariable Long quizId,@Valid @RequestBody CheckRequest request){
        return ApiResponse.controller(service.checkQuiz(quizId,request));
    }
}
