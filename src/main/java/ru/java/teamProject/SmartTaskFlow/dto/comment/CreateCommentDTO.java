package ru.java.teamProject.SmartTaskFlow.dto.comment;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCommentDTO {
    @NotNull
    private String content;
}