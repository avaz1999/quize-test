package com.example.quiztest.project.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Answer extends BaseEntity {
    private String variant;
    @ManyToOne
    private Question question;
    private boolean tureOrFalse;
}