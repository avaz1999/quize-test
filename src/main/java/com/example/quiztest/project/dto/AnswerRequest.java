package com.example.quiztest.project.dto;

import com.example.quiztest.project.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AnswerRequest {
    private Long id;
    private String answer;
    private Boolean right = false;
    private Question question;
}
