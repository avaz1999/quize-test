package com.example.quiztest.project.repositories;

import com.example.quiztest.project.entity.Question;

public interface QuestionRepository extends BaseRepository<Question> {
    Integer countAllByCategoryIdAndDeletedFalse(Long categoryId);
}
