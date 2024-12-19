package ru.java.teamProject.SmartTaskFlow.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreatePanelDTO {
    private Long boardId;
    private String name;
    private Integer orderIndex;
}