package com.example.quiztest.project.entity;

import com.example.quiztest.project.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class UserTestResult extends BaseEntity {
    @ManyToOne
    private User user;
    @ManyToOne
    private Quiz quiz;
    private short numberOfTests;
    private Integer rightAnswer;
    private Double score;

    public static UserTestResult create(Quiz quiz, User user) {
        UserTestResult result = new UserTestResult();
        result.setQuiz(quiz);
        result.setUser(user);
        return result;
    }
}
