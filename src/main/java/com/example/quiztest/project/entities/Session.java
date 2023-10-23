package com.example.quiztest.project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Service
@Builder
@Entity
public class Session extends BaseEntity {
    private Time time;
    private double result;
    private short numberOfTests;
    private short correctAnswer;
    @ManyToOne
    private User user;
    @OneToMany
    private List<Question> questions;
}