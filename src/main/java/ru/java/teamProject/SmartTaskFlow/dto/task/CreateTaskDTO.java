package ru.java.teamProject.SmartTaskFlow.dto.task;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateTaskDTO {

    private Long panelId;

    @NotNull
    private String name;

    @NotNull
    private String priority;

    private Integer orderIndex;
}