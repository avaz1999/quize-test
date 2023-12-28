package com.example.quiztest.project.repositories;

import com.example.quiztest.project.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends BaseRepository<Category> {
    Category findByNameAndDeletedFalse(String name);

}
