package ru.java.teamProject.SmartTaskFlow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "subtasks")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Subtask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

}