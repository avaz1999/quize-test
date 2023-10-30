package com.example.quiztest.project.repositories;

import com.example.quiztest.project.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
}
