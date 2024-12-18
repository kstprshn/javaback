package ru.java.teamProject.SmartTaskFlow.service.abstr;

import ru.java.teamProject.SmartTaskFlow.dto.*;
import ru.java.teamProject.SmartTaskFlow.entity.Task;

import java.util.List;
import java.util.UUID;

public interface TaskService {
    List<TaskDTO> getTasksInColumn(Long columnId);
    void addTaskToColumn(Long columnId, CreateTaskDTO taskDTO);
    void updateTask(Long taskId, UpdateTaskDTO taskDTO);
    void deleteTask(Long taskId);
    void moveTask(Long taskId, Long targetColumnId);
    void addCommentToTask(Long taskId, CreateCommentDTO commentDTO);
    Task createTask(UUID panelId, String name, String priority, Integer orderIndex);
    Task assignUser(UUID taskId, UUID userId);
    void addSubTask(Long taskId, CreateSubTaskDTO subTaskDTO);
    void updateSubTask(Long subTaskId, UpdateSubTaskDTO subTaskDTO);
    void deleteSubTask(Long subTaskId);
    void archiveTask(Long taskId);
    List<TaskDTO> getArchivedTasks(String email);
}
