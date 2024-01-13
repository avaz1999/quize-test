package com.example.quiztest.project.dto;

import com.example.quiztest.project.dto.question.QuestionForTakeTestResponse;
import com.example.quiztest.project.entity.Quiz;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TakeTestResponse {
    private Long quizId;
    private Short questionSize;
    private List<QuestionForTakeTestResponse> questions;

    public static TakeTestResponse toDto(Quiz quiz, List<QuestionForTakeTestResponse> responses) {
        TakeTestResponse take = new TakeTestResponse();
        take.setQuizId(quiz.getId());
        take.setQuestionSize(quiz.getQuestionSize());
        take.setQuestions(responses);
        return take;
    }
}
