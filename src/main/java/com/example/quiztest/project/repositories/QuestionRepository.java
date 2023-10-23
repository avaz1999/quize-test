package com.example.quiztest.project.repositories;

import com.example.quiztest.project.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {
}
