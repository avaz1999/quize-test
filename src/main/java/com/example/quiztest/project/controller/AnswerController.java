package com.example.quiztest.project.controller;

import com.example.quiztest.project.base.ApiResponse;
import com.example.quiztest.project.dto.AnswerRequest;
import com.example.quiztest.project.service.AnswerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("answer")
public class AnswerController {
    private final AnswerService service;

    public AnswerController(AnswerService service) {
        this.service = service;
    }
    @PostMapping("{questionId}")
    public ResponseEntity<?> create(@PathVariable Long questionId, @RequestBody AnswerRequest request){
        return ApiResponse.controller(service.create(questionId,request));
    }
    @PutMapping("{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody AnswerRequest request){
        return ApiResponse.controller(service.edit(id,request));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return ApiResponse.controller(service.delete(id));
    }
}
