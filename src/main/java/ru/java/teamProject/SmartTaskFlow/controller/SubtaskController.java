package ru.java.teamProject.SmartTaskFlow.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.java.teamProject.SmartTaskFlow.entity.Subtask;
import ru.java.teamProject.SmartTaskFlow.service.SubtaskServiceImpl;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/subtasks")
public class SubtaskController {

    private final SubtaskServiceImpl subtaskServiceImpl;

    public SubtaskController(SubtaskServiceImpl subtaskServiceImpl) {
        this.subtaskServiceImpl = subtaskServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Subtask> createSubtask(@RequestBody Map<String, String> request) {
        UUID taskId = UUID.fromString(request.get("taskId"));
        String name = request.get("name");
        return ResponseEntity.status(HttpStatus.CREATED).body(subtaskServiceImpl.createSubtask(taskId, name));
    }

    @PutMapping("/{subtaskId}")
    public ResponseEntity<Subtask> updateSubtask(@PathVariable UUID subtaskId, @RequestBody Map<String, String> request) {
        String newName = request.get("name");
        String newStatus = request.get("status");
        return ResponseEntity.ok(subtaskServiceImpl.updateSubtask(subtaskId, newName, newStatus));
    }

    @DeleteMapping("/{subtaskId}")
    public ResponseEntity<Void> deleteSubtask(@PathVariable UUID subtaskId) {
        subtaskServiceImpl.deleteSubtask(subtaskId);
        return ResponseEntity.noContent().build();
    }
}