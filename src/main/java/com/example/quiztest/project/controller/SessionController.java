package com.example.quiztest.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class SessionController {

    @GetMapping("/home")
    public String getAllCourses(Model model){
        return "index";
    }

}
