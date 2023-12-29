package com.example.quiztest.project.repositories;

import com.example.quiztest.project.entity.User;
import com.example.quiztest.project.entity.UserTestResult;

import java.util.List;

public interface UserTestResultRepository extends BaseRepository<UserTestResult> {
    List<UserTestResult> findAllByUserAndDeletedFalse(User user);
}
