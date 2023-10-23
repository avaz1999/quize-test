package com.example.quiztest.project.repositories;

import com.example.quiztest.project.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session,Long> {
}
