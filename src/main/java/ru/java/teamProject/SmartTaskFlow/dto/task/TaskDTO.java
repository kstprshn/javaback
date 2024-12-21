package ru.java.teamProject.SmartTaskFlow.dto.task;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter @Getter
@Accessors(chain = true)
public class TaskDTO {
    private Long id;
    private String name;
    private String priority;
    private Integer orderIndex;
    private boolean archived;

    private String startTime;
    private String endTime;

    private Long panelId;
}