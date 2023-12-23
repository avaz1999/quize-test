package com.example.quiztest.project.repositories;

import com.example.quiztest.project.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer,Long> {
}
