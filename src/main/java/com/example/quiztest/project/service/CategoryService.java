package com.example.quiztest.project.service;

import com.example.quiztest.project.base.ApiResponse;
import com.example.quiztest.project.dto.category.CategoryRequest;
import org.springframework.data.domain.Pageable;


public interface CategoryService {
    ApiResponse<?> create(CategoryRequest request);
    ApiResponse<?> getOne(Long id);
    ApiResponse<?> getAll(Pageable pageable);
    ApiResponse<?> edit(Long id,CategoryRequest request);
    ApiResponse<?> delete(Long id);
}
