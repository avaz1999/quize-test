package com.example.quiztest.project.entity;

import com.example.quiztest.project.base.BaseEntity;
import com.example.quiztest.project.dto.QuestionRequest;
import com.example.quiztest.project.enums.Difficulty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Question extends BaseEntity {
    private String title;
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty = Difficulty.EASY;
    @ManyToOne
    private Category category;

    public static Question create(QuestionRequest request, Category category) {
        Question question = new Question();
        question.setTitle(request.getTitle());
        question.setDifficulty(request.getDifficulty() != null ? request.getDifficulty() : Difficulty.EASY);
        question.setCategory(category);
        return question;
    }

    public static Question edit(Question question, QuestionRequest request) {
        question.setTitle(request.getTitle() != null ? request.getTitle(): question.getTitle());
        question.setDifficulty(request.getDifficulty());
        return question;
    }
}
