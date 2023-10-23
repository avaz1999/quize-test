package com.example.quiztest.project.repositories;

import com.example.quiztest.project.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer,Long> {
}
