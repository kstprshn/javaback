package ru.java.teamProject.SmartTaskFlow.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.java.teamProject.SmartTaskFlow.dto.subtask.CreateSubTaskDTO;
import ru.java.teamProject.SmartTaskFlow.dto.subtask.UpdateSubTaskDTO;
import ru.java.teamProject.SmartTaskFlow.entity.Subtask;
import ru.java.teamProject.SmartTaskFlow.service.SubtaskServiceImpl;


@RestController
@RequestMapping("/api/subtasks")
public class SubtaskController {

    private final SubtaskServiceImpl subtaskServiceImpl;

    public SubtaskController(SubtaskServiceImpl subtaskServiceImpl) {
        this.subtaskServiceImpl = subtaskServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Subtask> createSubtask(@RequestBody CreateSubTaskDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(subtaskServiceImpl.createSubtask(request.getTaskId(), request));
    }

    @PutMapping("/{subtaskId}")
    public ResponseEntity<Subtask> updateSubtask(@PathVariable Long subtaskId, @RequestBody CreateSubTaskDTO request) {
        return ResponseEntity
                .ok(subtaskServiceImpl.updateSubtask(subtaskId, request));
    }

    @DeleteMapping("/{subtaskId}")
    public ResponseEntity<Void> deleteSubtask(@PathVariable Long subtaskId) {
        subtaskServiceImpl.deleteSubtask(subtaskId);
        return ResponseEntity.noContent().build();
    }
}