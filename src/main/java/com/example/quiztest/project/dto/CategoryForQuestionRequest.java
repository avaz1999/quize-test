package com.example.quiztest.project.dto;

import com.example.quiztest.project.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CategoryForQuestionRequest {
    private Long id;
    private String name;
    public static CategoryForQuestionRequest toDto(Category c) {
        CategoryForQuestionRequest request = new CategoryForQuestionRequest();
        request.setId(c.getId());
        request.setName(c.getName());
        return request;
    }
}
