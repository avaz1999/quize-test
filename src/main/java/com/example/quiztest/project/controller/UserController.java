package com.example.quiztest.project.controller;

import com.example.quiztest.project.dto.UserDto;
import com.example.quiztest.project.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("add")
    public String create(){
        return "user-form";
    }

    @PostMapping("save")
    public String saveUser(
             UserDto dto){
        service.create(dto);
        return "redirect:/users/all";
    }

    @GetMapping("{id}")
    public String getById(@PathVariable Long id,Model model){
        model.addAttribute("getOne",service.getOne(id));
        return "user-by-id";
    }

    @GetMapping("all")
    public String getAll(Model model){
        model.addAttribute("users",service.getAll());
        return "all-users";
    }
}
