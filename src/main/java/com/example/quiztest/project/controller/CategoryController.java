package com.example.quiztest.project.controller;

import com.example.quiztest.project.base.ApiResponse;
import com.example.quiztest.project.dto.CategoryRequest;
import com.example.quiztest.project.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("category")
public class CategoryController {
    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }
    @PostMapping
    private ResponseEntity<?> create(@Valid @RequestBody CategoryRequest request){
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

    @PutMapping("{id}")
    public ResponseEntity<?> edit(@PathVariable Long id,@RequestBody CategoryRequest request){
        return ApiResponse.controller(service.edit(id,request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return ApiResponse.controller(service.delete(id));
    }
}
