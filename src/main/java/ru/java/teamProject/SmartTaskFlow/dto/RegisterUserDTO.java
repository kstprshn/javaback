package ru.java.teamProject.SmartTaskFlow.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RegisterUserDTO {
    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 6, max = 20)
    private String password;

    @NotNull
    private String confirmPassword;
}