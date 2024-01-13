package com.example.quiztest.project.dto.category;

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
    private Long id;
    private String name;
    private Integer count;

    public static CategoryRequest toDto(Category c) {
        CategoryRequest request = new CategoryRequest();
        request.setId(c.getId());
        request.setName(c.getName());
        return request;
    }
}
