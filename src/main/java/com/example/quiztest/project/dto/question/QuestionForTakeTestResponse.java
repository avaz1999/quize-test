package com.example.quiztest.project.dto.question;

import com.example.quiztest.project.dto.answer.AnswerResponse;
import com.example.quiztest.project.enums.Difficulty;
import com.example.quiztest.project.projection.GetQuesrionForQuiz;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionForTakeTestResponse {
    private Long id;
    private String title;
    private Difficulty difficulty;
    private List<AnswerResponse> answers;

    public static QuestionForTakeTestResponse toDto(GetQuesrionForQuiz question, List<AnswerResponse> answerResponse) {
        QuestionForTakeTestResponse questionResponse = new QuestionForTakeTestResponse();
        questionResponse.setId(question.getId());
        questionResponse.setTitle(question.getTitle());
        questionResponse.setDifficulty(Difficulty.valueOf(question.getDifficulty()));
        questionResponse.setAnswers(answerResponse);
        return questionResponse;
    }
}
