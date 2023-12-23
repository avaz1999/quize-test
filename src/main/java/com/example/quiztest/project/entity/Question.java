package com.example.quiztest.project.entity;

import com.example.quiztest.project.base.BaseEntity;
import com.example.quiztest.project.dto.QuestionRequest;
import com.example.quiztest.project.enums.Difficulty;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
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
    @OneToOne
    private Category category;

    public static Question create(QuestionRequest request, Category category) {
        Question question = new Question();
        question.setTitle(request.getTitle());
        question.setDifficulty(request.getDifficulty());
        question.setCategory(category);
        return question;
    }
}
