package com.example.quiztest.project.entity;

import com.example.quiztest.project.base.BaseEntity;
import com.example.quiztest.project.dto.QuizRequest;
import com.example.quiztest.project.enums.QuizStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Quiz extends BaseEntity {
    private Long time;
    private QuizStatus status;
    private Short questionSize;
    @ManyToMany
    private List<Question> questions = new ArrayList<>();
    @ManyToOne
    private User user;

    public static Quiz create(QuizRequest request) {
        Quiz quiz = new Quiz();
        quiz.setStatus(QuizStatus.CREATE);
        quiz.setTime(request.getTime());
        return quiz;
    }
}
