package ru.java.teamProject.SmartTaskFlow.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCommentDTO {
    @NotNull
    private String content;
}