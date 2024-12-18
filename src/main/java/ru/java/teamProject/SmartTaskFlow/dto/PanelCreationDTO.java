package ru.java.teamProject.SmartTaskFlow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
public class PanelCreationDTO {
    @NotNull
    private UUID boardId;

    @NotBlank
    private String name;

    private Integer position;

}

