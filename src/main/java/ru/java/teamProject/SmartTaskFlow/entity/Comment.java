package ru.java.teamProject.SmartTaskFlow.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    @JsonIgnore
    private User author;

    @ManyToOne
    @JoinColumn(name = "task_id", nullable = false)
    @JsonIgnore
    private Task task;

    @Column(nullable = false)
    private LocalDateTime createdDate;

}
