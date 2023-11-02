package com.example.quiztest.project.entity;

import com.example.quiztest.project.base.BaseEntity;
import com.example.quiztest.project.dto.QuestionDTO;
import com.example.quiztest.project.enums.Difficulty;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Question extends BaseEntity {
    private String questionTitle;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String rightAnswer;
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
    @OneToOne
    private Category category;

    public static Question edit(Question q) {
        q.setQuestionTitle(q.getQuestionTitle() != null ? q.getQuestionTitle() : q.questionTitle);
        q.setOption1(q.getOption1() != null ? q.getOption1() : q.option1);
        q.setOption2(q.getOption1() != null ? q.getOption2() : q.option2);
        q.setOption3(q.getOption1() != null ? q.getOption3() : q.option3);
        q.setOption4(q.getOption1() != null ? q.getOption4() : q.option4);
        q.setRightAnswer(q.getRightAnswer() != null ? q.getRightAnswer() : q.rightAnswer);
        q.setDifficulty(q.getDifficulty() != null ? q.getDifficulty() : q.difficulty);
        q.setCategory(q.getCategory() != null ? q.getCategory() : q.category);
        return q;
    }

    public static Question create(QuestionDTO dto) {
        return new Question(
                dto.getQuestionTitle(),
                dto.getOption1(),
                dto.getOption2(),
                dto.getOption3(),
                dto.getOption4(),
                dto.getRightAnswer(),
                dto.getDifficulty(),
                dto.getCategory()
        );
    }
}
