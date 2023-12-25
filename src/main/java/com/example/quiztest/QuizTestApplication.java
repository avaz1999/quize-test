package com.example.quiztest;

import com.example.quiztest.project.base.BaseEntity;
import com.example.quiztest.project.repositories.impl.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
@EntityScan(basePackageClasses = BaseEntity.class)
@EnableJpaAuditing
public class QuizTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuizTestApplication.class, args);
    }

}
