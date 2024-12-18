package ru.java.teamProject.SmartTaskFlow.dto;

import java.util.UUID;

public class TaskDTO {
    private UUID id;
    private String name;
    private String priority;
    private Integer orderIndex;
    private UUID columnId;
    private String columnName;
    private List<SubtaskDTO> subtasks;
    private List<CommentDTO> comments;
    private boolean archived;
}