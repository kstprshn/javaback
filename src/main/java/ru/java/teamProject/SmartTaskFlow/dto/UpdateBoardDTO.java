package ru.java.teamProject.SmartTaskFlow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class UpdateBoardDTO {
    @NotNull
    @Size(min = 3, max = 100)
    private String name;
}

