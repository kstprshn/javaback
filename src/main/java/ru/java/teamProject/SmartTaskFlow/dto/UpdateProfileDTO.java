package ru.java.teamProject.SmartTaskFlow.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class UpdateProfileDTO {
    private String name;
    private String email;
    private String password;
}