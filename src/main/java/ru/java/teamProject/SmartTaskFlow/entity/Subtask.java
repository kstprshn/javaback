package ru.java.teamProject.SmartTaskFlow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.java.teamProject.SmartTaskFlow.entity.enums.Status;


@Entity
@Table(name = "subtasks")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Subtask {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;

}