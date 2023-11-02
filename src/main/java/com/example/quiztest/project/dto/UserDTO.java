package com.example.quiztest.project.dto;

import com.example.quiztest.project.enums.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {
    private Long id;
    @NotBlank(message = "Исм Фамиля киритишунгиз мажбурий")
    private String fullName;

    @NotBlank(message = "Username киритишингиз мажбурий")
    private String username;

    @NotBlank(message = "Усернаме киритишингиз мажбурий")
    @Size(min = 6, message = "Парол камида 6 та белгидан иборат бўлиши керак")
    private String password;

    private UserRole role;
}
