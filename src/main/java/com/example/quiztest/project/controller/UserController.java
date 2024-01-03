package com.example.quiztest.project.controller;

import com.example.quiztest.project.base.ApiResponse;
import com.example.quiztest.project.dto.UserDTO;
import com.example.quiztest.project.dto.UserRequest;
import com.example.quiztest.project.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("check-user")
    public ResponseEntity<?> checkUser(@RequestBody UserRequest request){
        return ApiResponse.controller(userService.checkUser(request));
    }

    @GetMapping("all")
    public ResponseEntity<?> getAll(Pageable pageable){
        return ApiResponse.controller(userService.getAll(pageable));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        return ApiResponse.controller(userService.getOne(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UserDTO dto){
        return ApiResponse.controller(userService.create(dto));
    }
}
