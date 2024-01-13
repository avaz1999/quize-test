package com.example.quiztest.project.entity;

import com.example.quiztest.project.base.BaseEntity;
import com.example.quiztest.project.dto.category.CategoryRequest;
import jakarta.persistence.Entity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Category extends BaseEntity {
    private String name;

    public static Category edit(Category category, CategoryRequest request) {
        category.setName(request.getName() != null ? request.getName() : category.getName() );
        return category;
    }
}
