package com.example.quiztest.project.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Question extends BaseEntity {
    private String title;
    @OneToMany(mappedBy = "question")
    private List<Answer> answers;
}