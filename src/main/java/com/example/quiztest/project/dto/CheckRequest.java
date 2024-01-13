package com.example.quiztest.project.dto;

import com.example.quiztest.project.dto.question.QuestionRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CheckRequest {
    private List<QuestionRequest> questionRequests;
}
