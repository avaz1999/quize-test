package com.example.quiztest.project.service.impl;

import com.example.quiztest.project.base.ApiResponse;
import com.example.quiztest.project.dto.CategoryRequest;
import com.example.quiztest.project.entity.Category;
import com.example.quiztest.project.exception.CategoryNotFountException;
import com.example.quiztest.project.repositories.CategoryRepository;
import com.example.quiztest.project.repositories.QuestionRepository;
import com.example.quiztest.project.service.CategoryService;
import com.example.quiztest.project.utils.ResponseMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;
    private final QuestionRepository questionRepository;

    public CategoryServiceImpl(CategoryRepository repository, QuestionRepository questionRepository) {
        this.repository = repository;
        this.questionRepository = questionRepository;
    }

    @Override
    public ApiResponse<?> create(CategoryRequest request) {
        repository.save(new Category(request.getName()));
        return new ApiResponse<>(true, ResponseMessage.SUCCESS);
    }

    @Override
    public ApiResponse<?> getOne(Long id) {
        Category category = repository.findByIdAndDeletedFalse(id);
        if (category == null) throw new CategoryNotFountException();
        CategoryRequest request = getCategoryRequest(category);
        return new ApiResponse<>(true, ResponseMessage.SUCCESS, request);
    }

    @Override
    public ApiResponse<?> getAll(Pageable pageable) {
        List<Category> allCategory = repository.findAllNotDeleted();
        List<CategoryRequest> list = new ArrayList<>();
        for (Category category : allCategory) {
            list.add(getCategoryRequest(category));
        }
        return new ApiResponse<>(true, ResponseMessage.SUCCESS, list);
    }

    @Override
    public ApiResponse<?> edit(Long id, CategoryRequest request) {
        Category category = repository.findByIdAndDeletedFalse(id);
        if (category == null) throw new CategoryNotFountException();
        Category edit = Category.edit(category, request);
        CategoryRequest dto = CategoryRequest.toDto(edit);
        return new ApiResponse<>(true,ResponseMessage.SUCCESS,dto);
    }

    @Override
    public ApiResponse<?> delete(Long id) {
        Category category = repository.findByIdAndDeletedFalse(id);
        if (category == null) throw new CategoryNotFountException();
        category.setDeleted(true);
        repository.save(category);
        return new ApiResponse<>(true, ResponseMessage.SUCCESS);
    }

    private CategoryRequest getCategoryRequest(Category category) {
        Integer count = questionRepository.countAllByCategoryIdAndDeletedFalse(category.getId());
        return new CategoryRequest(category.getName(), count);
    }
}
