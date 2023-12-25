package com.example.quiztest.project.repositories;

import com.example.quiztest.project.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer,Long> {
    List<Answer> findAllByQuestionIdAndDeletedFalse(Long questionId);
}
