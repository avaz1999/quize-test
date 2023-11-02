package com.example.quiztest.project.entity;

import com.example.quiztest.project.base.BaseEntity;
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
}
