package ru.java.teamProject.SmartTaskFlow.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class LoginDTO {
    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;
}