package ru.java.teamProject.SmartTaskFlow.service;

import jakarta.persistence.Column;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.java.teamProject.SmartTaskFlow.dto.CreateCommentDTO;
import ru.java.teamProject.SmartTaskFlow.dto.CreateTaskDTO;
import ru.java.teamProject.SmartTaskFlow.dto.TaskDTO;
import ru.java.teamProject.SmartTaskFlow.dto.UpdateTaskDTO;
import ru.java.teamProject.SmartTaskFlow.entity.Comment;
import ru.java.teamProject.SmartTaskFlow.entity.Task;
import ru.java.teamProject.SmartTaskFlow.repository.*;
import ru.java.teamProject.SmartTaskFlow.service.abstr.TaskService;

import java.util.List;


@Service
@Slf4j
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final PanelRepository panelRepository;

    public TaskServiceImpl(TaskRepository taskRepository, PanelRepository panelRepository) {
        this.taskRepository = taskRepository;
        this.panelRepository = panelRepository;
    }

    @Override
    public List<TaskDTO> getTasksInColumn(Long columnId) {
        log.info("Fetching tasks for column ID: {}", columnId);
        return taskRepository.findByColumnId(columnId)
                .stream()
                .map(task -> {
                    TaskDTO taskDTO = new TaskDTO();
                    taskDTO.setId(task.getId());
                    taskDTO.setName(task.getName());
                    taskDTO.setPriority(task.getPriority());
                    taskDTO.setOrderIndex(task.getOrderIndex());
                    taskDTO.setArchived(task.isArchived());
                    return taskDTO;
                })
                .toList();
    }

    @Override
    public void addTaskToColumn(Long columnId, CreateTaskDTO taskDTO) {
        log.info("Adding task to column ID: {}", columnId);
        Column column = panelRepository.findById(columnId)
                .orElseThrow(() -> new IllegalArgumentException("Column not found"));
        Task task = new Task();
        task.setName(taskDTO.getName());
        task.setPriority(taskDTO.getPriority());
        task.setOrderIndex(taskDTO.getOrderIndex());
        task.setColumn(column);
        taskRepository.save(task);
    }

    @Override
    public void updateTask(Long taskId, UpdateTaskDTO taskDTO) {
        log.info("Updating task with ID: {}", taskId);
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        if (taskDTO.getName() != null) {
            task.setName(taskDTO.getName());
        }
        if (taskDTO.getPriority() != null) {
            task.setPriority(taskDTO.getPriority());
        }
        if (taskDTO.getOrderIndex() != null) {
            task.setOrderIndex(taskDTO.getOrderIndex());
        }
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long taskId) {
        log.info("Deleting task with ID: {}", taskId);
        taskRepository.deleteById(taskId);
    }

    @Override
    public void moveTask(Long taskId, Long targetColumnId) {
        log.info("Moving task ID: {} to column ID: {}", taskId, targetColumnId);
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        Column targetColumn = panelRepository.findById(targetColumnId)
                .orElseThrow(() -> new IllegalArgumentException("Target column not found"));
        task.setColumn(targetColumn);
        taskRepository.save(task);
    }

    @Override
    public void addCommentToTask(Long taskId, CreateCommentDTO commentDTO) {
        log.info("Adding comment to task ID: {}", taskId);
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        Comment comment = new Comment();
        comment.setContent(commentDTO.getContent());
        comment.setTask(task);
        task.getComments().add(comment);
        taskRepository.save(task);
    }

    @Override
    public List<TaskDTO> getArchivedTasks(String email) {
        log.info("Fetching archived tasks for user: {}", email);
        return taskRepository.findByUserEmailAndArchived(email, true)
                .stream()
                .map(task -> {
                    TaskDTO taskDTO = new TaskDTO();
                    taskDTO.setId(task.getId());
                    taskDTO.setName(task.getName());
                    taskDTO.setArchived(true);
                    return taskDTO;
                })
                .toList();
    }
}
