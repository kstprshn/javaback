package ru.java.teamProject.SmartTaskFlow.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.java.teamProject.SmartTaskFlow.dto.*;
import ru.java.teamProject.SmartTaskFlow.entity.Task;
import ru.java.teamProject.SmartTaskFlow.service.TaskServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskServiceImpl taskServiceImpl;

    @GetMapping("/columns/{columnId}/tasks")
    public ResponseEntity<List<TaskDTO>> getTasksInColumn(@PathVariable Long columnId) {
        List<TaskDTO> tasks = taskServiceImpl.getTasksInColumn(columnId);
        return ResponseEntity.ok(tasks);
    }

    @PostMapping("/columns/{columnId}/tasks")
    public ResponseEntity<?> addTaskToColumn(@PathVariable Long columnId, @Valid @RequestBody CreateTaskDTO taskDTO) {
        taskServiceImpl.addTaskToColumn(columnId, taskDTO);
        return ResponseEntity.ok("Task added successfully.");
    }

    @PutMapping("/tasks/{taskId}")
    public ResponseEntity<?> updateTask(@PathVariable Long taskId, @Valid @RequestBody UpdateTaskDTO taskDTO) {
        taskServiceImpl.updateTask(taskId, taskDTO);
        return ResponseEntity.ok("Task updated successfully.");
    }

    @DeleteMapping("/tasks/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
        taskServiceImpl.deleteTask(taskId);
        return ResponseEntity.ok("Task deleted successfully.");
    }

    @PostMapping("/tasks/{taskId}/move")
    public ResponseEntity<?> moveTask(@PathVariable Long taskId, @RequestParam Long targetColumnId) {
        taskServiceImpl.moveTask(taskId, targetColumnId);
        return ResponseEntity.ok("Task moved successfully.");
    }

    @PostMapping("/tasks/{taskId}/comments")
    public ResponseEntity<?> addCommentToTask(@PathVariable Long taskId, @Valid @RequestBody CreateCommentDTO commentDTO) {
        taskServiceImpl.addCommentToTask(taskId, commentDTO);
        return ResponseEntity.ok("Comment added successfully.");
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody CreateTaskDTO taskCreateRequest) {

        Task createdTask = taskServiceImpl.createTask(
                taskCreateRequest.getPanelId(), taskCreateRequest.getName(),
                taskCreateRequest.getPriority(), taskCreateRequest.getOrderIndex()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }


    @PatchMapping("/{taskId}/assign/{userId}")
    public ResponseEntity<Task> assignUser(@PathVariable Long taskId, @PathVariable Long userId) {
        return ResponseEntity.ok(taskServiceImpl.assignUser(taskId, userId));
    }


    // checklist
    @PostMapping("/tasks/{taskId}/subtasks")
    public ResponseEntity<?> addSubTask(@PathVariable Long taskId, @Valid @RequestBody CreateSubTaskDTO subTaskDTO) {
        taskServiceImpl.addSubTask(taskId, subTaskDTO);
        return ResponseEntity.ok("Sub-task added successfully.");
    }

    @PutMapping("/subtasks/{subTaskId}")
    public ResponseEntity<?> updateSubTask(@PathVariable Long subTaskId, @Valid @RequestBody UpdateSubTaskDTO subTaskDTO) {
        taskServiceImpl.updateSubTask(subTaskId, subTaskDTO);
        return ResponseEntity.ok("Sub-task updated successfully.");
    }

    @DeleteMapping("/subtasks/{subTaskId}")
    public ResponseEntity<?> deleteSubTask(@PathVariable Long subTaskId) {
        taskServiceImpl.deleteSubTask(subTaskId);
        return ResponseEntity.ok("Sub-task deleted successfully.");
    }

    @PostMapping("/{taskId}/archive")
    public ResponseEntity<?> archiveTask(@PathVariable Long taskId) {
        taskServiceImpl.archiveTask(taskId);
        return ResponseEntity.ok("Task archived successfully.");
    }

    @PostMapping("/{taskId}/unarchive")
    public ResponseEntity<Void> unarchiveTask(@PathVariable Long taskId) {
        taskServiceImpl.unarchiveTask(taskId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tasks/archive")
    public ResponseEntity<List<TaskDTO>> getArchivedTasks(Authentication authentication) {
        String email = authentication.getName();
        List<TaskDTO> archivedTasks = taskServiceImpl.getArchivedTasks(email);
        return ResponseEntity.ok(archivedTasks);
    }
}


