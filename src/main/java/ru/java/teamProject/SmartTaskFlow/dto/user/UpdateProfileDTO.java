package ru.java.teamProject.SmartTaskFlow.dto.user;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class UpdateProfileDTO {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
}