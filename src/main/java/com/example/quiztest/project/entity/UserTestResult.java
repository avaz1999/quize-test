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
    private int score;
}
