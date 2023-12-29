package com.example.quiztest.project.dto;

import com.example.quiztest.project.entity.Question;
import com.example.quiztest.project.enums.Difficulty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Stream;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionForTakeTestResponse {
    private Long id;
    private String title;
    private Difficulty difficulty;
    private List<AnswerResponse> answers;

    public static QuestionForTakeTestResponse toDto(Question question, List<AnswerResponse> answerResponse) {
        QuestionForTakeTestResponse questionResponse = new QuestionForTakeTestResponse();
        questionResponse.setId(question.getId());
        questionResponse.setTitle(question.getTitle());
        questionResponse.setDifficulty(question.getDifficulty());
        questionResponse.setAnswers(answerResponse);
        return questionResponse;
    }
}
