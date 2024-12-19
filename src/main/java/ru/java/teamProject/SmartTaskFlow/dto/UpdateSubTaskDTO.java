package ru.java.teamProject.SmartTaskFlow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateSubTaskDTO {
    private String name;
    private String status;
    private boolean completed;
}
