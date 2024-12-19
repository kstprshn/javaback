package ru.java.teamProject.SmartTaskFlow.dto;

import lombok.Getter;
import lombok.Setter;
import ru.java.teamProject.SmartTaskFlow.entity.enums.Status;

@Getter @Setter
public class UpdateSubTaskDTO {
    private String name;
    private Status status;
}
