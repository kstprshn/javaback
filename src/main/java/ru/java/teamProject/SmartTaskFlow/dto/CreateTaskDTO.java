package ru.java.teamProject.SmartTaskFlow.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateTaskDTO {
    @NotNull
    private String name;

    @NotNull
    private String priority;

    private Integer orderIndex;
}