package com.example.quiztest.project.repositories;

import com.example.quiztest.project.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findAllByDeletedFalse();
    User findByIdAndDeletedFalse(Long id);
}
