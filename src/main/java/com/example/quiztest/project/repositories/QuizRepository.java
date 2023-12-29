package com.example.quiztest.project.repositories;

import com.example.quiztest.project.entity.Category;
import com.example.quiztest.project.entity.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends BaseRepository<Quiz> {
    Page<Quiz> findAllByDeletedFalse(Pageable pageable);
    Page<Quiz> findAllByCategoryAndDeletedFalse(Category category,Pageable pageable);
}
