package ru.java.teamProject.SmartTaskFlow.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateSubTaskDTO {
    @NotNull
    private String name;

    private Long taskId;

}
