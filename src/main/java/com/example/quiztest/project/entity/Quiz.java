package com.example.quiztest.project.entity;

import com.example.quiztest.project.base.BaseEntity;
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
    private String title;
    @ManyToMany
    private List<Question> questions = new ArrayList<>();
    @ManyToOne
    private User user;
}
