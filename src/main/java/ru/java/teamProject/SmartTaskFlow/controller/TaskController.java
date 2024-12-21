package ru.java.teamProject.SmartTaskFlow.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.java.teamProject.SmartTaskFlow.dto.comment.CreateCommentDTO;
import ru.java.teamProject.SmartTaskFlow.dto.subtask.CreateSubTaskDTO;
import ru.java.teamProject.SmartTaskFlow.dto.subtask.UpdateSubTaskDTO;
import ru.java.teamProject.SmartTaskFlow.dto.task.CreateTaskDTO;
import ru.java.teamProject.SmartTaskFlow.dto.task.TaskDTO;
import ru.java.teamProject.SmartTaskFlow.dto.task.UpdateTaskDTO;
import ru.java.teamProject.SmartTaskFlow.service.abstr.SubtaskService;
import ru.java.teamProject.SmartTaskFlow.service.abstr.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final SubtaskService subtaskService;

    @GetMapping("/columns/{columnId}/tasks")
    public ResponseEntity<List<TaskDTO>> getTasksInColumn(@PathVariable Long columnId) {
        return ResponseEntity.ok(taskService.getTasksInColumn(columnId));
    }

    @PostMapping("/columns/{columnId}/tasks")
    public ResponseEntity<?> addTaskToColumn(@PathVariable Long columnId, @Valid @RequestBody CreateTaskDTO taskDTO) {
        return ResponseEntity.ok(taskService.addTaskToColumn(columnId, taskDTO));
    }

    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<?> updateTask(@PathVariable Long taskId, @Valid @RequestBody UpdateTaskDTO taskDTO) {
        return ResponseEntity.ok(taskService.updateTask(taskId, taskDTO));
    }

    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.ok("Task deleted successfully.");
    }

    @PostMapping("/tasks/{taskId}/move")
    public ResponseEntity<?> moveTask(@PathVariable Long taskId, @RequestParam Long targetColumnId) {
        return ResponseEntity.ok(taskService.moveTask(taskId, targetColumnId));
    }

    @PostMapping("/tasks/{taskId}/comments")
    public ResponseEntity<?> addCommentToTask(@PathVariable Long taskId, @Valid @RequestBody CreateCommentDTO commentDTO) {
        return ResponseEntity.ok(taskService.addCommentToTask(taskId, commentDTO));
    }

    @PostMapping
    public ResponseEntity<?> createTask(@RequestBody CreateTaskDTO taskCreateRequest) {

        TaskDTO createdTask = taskService.createTask(
                taskCreateRequest.getPanelId(), taskCreateRequest.getName(),
                taskCreateRequest.getPriority(), taskCreateRequest.getOrderIndex()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }


    @PatchMapping("/{taskId}/assign/{userId}")
    public ResponseEntity<?> assignUser(@PathVariable Long taskId, @PathVariable Long userId) {
        return ResponseEntity.ok(taskService.assignUser(taskId, userId));
    }


    // checklist
    @PostMapping("/tasks/{taskId}/subtasks")
    public ResponseEntity<?> addSubTask(@PathVariable Long taskId, @Valid @RequestBody CreateSubTaskDTO subTaskDTO) {
        return ResponseEntity.ok(subtaskService.createSubtask(taskId, subTaskDTO));
    }


    @PostMapping("/{taskId}/archive")
    public ResponseEntity<?> archiveTask(@PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.archiveTask(taskId));
    }

    @PostMapping("/{taskId}/unarchive")
    public ResponseEntity<?> unArchiveTask(@PathVariable Long taskId) {
        return ResponseEntity.ok(taskService.unArchiveTask(taskId));
    }

    @GetMapping("/tasks/archive")
    public ResponseEntity<?> getArchivedTasks(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(taskService.getArchivedTasks(email));
    }
}


