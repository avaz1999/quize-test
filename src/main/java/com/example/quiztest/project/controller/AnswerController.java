package com.example.quiztest.project.controller;

import com.example.quiztest.project.service.AnswerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("answer")
public class AnswerController {
    private final AnswerService service;

    public AnswerController(AnswerService service) {
        this.service = service;
    }
}
