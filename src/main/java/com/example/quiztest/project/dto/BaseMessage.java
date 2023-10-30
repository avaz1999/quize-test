package com.example.quiztest.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BaseMessage {
    private Integer code;
    private String message;
    public static final BaseMessage OK = new BaseMessage(0, "OK");

}
