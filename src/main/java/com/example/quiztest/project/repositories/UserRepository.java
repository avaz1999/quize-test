package com.example.quiztest.project.repositories;

import com.example.quiztest.project.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findAllByDeletedFalse(Pageable pageable);
    User findByIdAndDeletedFalse(Long id);
}
