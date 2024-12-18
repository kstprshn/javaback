package ru.java.teamProject.SmartTaskFlow.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class BoardDTO {
    private UUID id;
    private String name;
    private List<String> members;
}