package ru.java.teamProject.SmartTaskFlow.dto;


import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class TaskDTO {
    private Long id;
    private String name;
    private String priority;
    private Integer orderIndex;
    private Long columnId;
    private boolean archived;
}