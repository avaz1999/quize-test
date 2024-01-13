package com.example.quiztest.project.controller;

import com.example.quiztest.project.base.ApiResponse;
import com.example.quiztest.project.dto.question.QuestionResponse;
import com.example.quiztest.project.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<?> create(@PathVariable Long categoryId,
                                    @Valid @RequestBody QuestionResponse request){
        return ApiResponse.controller(service.create(request,categoryId));
    }
    @GetMapping()
    public ResponseEntity<?> getAll(
            @Valid @RequestParam String categoryName,
            Pageable pageable){
        return ApiResponse.controller(service.getAll(pageable,categoryName));
    }
    @GetMapping("{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        return ApiResponse.controller(service.getOne(id));
    }
    @PutMapping("{id}")
    public ResponseEntity<?> edit(@PathVariable Long id,@RequestBody QuestionResponse request){
        return ApiResponse.controller(service.edit(id,request));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return ApiResponse.controller(service.delete(id));
    }
}
