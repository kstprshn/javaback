package ru.java.teamProject.SmartTaskFlow.dto.task;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateTaskDTO {
    private String name;
    private String priority;
    private Integer orderIndex;
}