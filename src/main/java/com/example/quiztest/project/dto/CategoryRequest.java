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
public class CategoryRequest {
    private String name;
    private Integer count;

    public static CategoryRequest toDto(Category edit) {
        return new CategoryRequest(edit.getName(),null);
    }
}
