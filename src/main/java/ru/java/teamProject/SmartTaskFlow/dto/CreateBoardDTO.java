package ru.java.teamProject.SmartTaskFlow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateBoardDTO {
    @NotNull
    @Size(min = 3, max = 100)
    private String name;
}