package com.example.quiztest.project.repositories;

import com.example.quiztest.project.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserRepository extends BaseRepository<User> {
    List<User> findAllByDeletedFalse(Pageable pageable);
    User findByIdAndDeletedFalse(Long id);
    User findByUsernameAndDeletedFalse(String username);

    boolean existsByUsernameAndDeletedFalse(String username);
}
