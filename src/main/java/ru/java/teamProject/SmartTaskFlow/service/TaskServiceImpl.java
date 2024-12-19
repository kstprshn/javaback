package ru.java.teamProject.SmartTaskFlow.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.java.teamProject.SmartTaskFlow.dto.*;
import ru.java.teamProject.SmartTaskFlow.entity.*;
import ru.java.teamProject.SmartTaskFlow.entity.enums.Status;
import ru.java.teamProject.SmartTaskFlow.repository.*;
import ru.java.teamProject.SmartTaskFlow.service.abstr.TaskService;

import java.util.List;


@Service
@Slf4j
@Transactional
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final PanelRepository panelRepository;

    private final UserRepository userRepository;

    private final SubtaskRepository subTaskRepository;

    public TaskServiceImpl(TaskRepository taskRepository, PanelRepository panelRepository, UserRepository userRepository, SubtaskRepository subTaskRepository) {
        this.taskRepository = taskRepository;
        this.panelRepository = panelRepository;
        this.userRepository = userRepository;
        this.subTaskRepository = subTaskRepository;
    }

    @Override
    public List<TaskDTO> getTasksInColumn(Long columnId) {
        log.info("Fetching tasks for column ID: {}", columnId);
        return taskRepository.findByPanelId(columnId)
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
    public void addTaskToColumn(Long panelId, CreateTaskDTO taskDTO) {
        log.info("Adding task to column ID: {}", panelId);
        Panel panel = panelRepository.findById(panelId)
                .orElseThrow(() -> new IllegalArgumentException("Column not found"));
        Task task = new Task();
        task.setName(taskDTO.getName());
        task.setPriority(taskDTO.getPriority());
        task.setOrderIndex(taskDTO.getOrderIndex());
        task.setPanel(panel);
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
        Panel targetColumn = panelRepository.findById(targetColumnId)
                .orElseThrow(() -> new IllegalArgumentException("Target column not found"));
        task.setPanel(targetColumn);
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
        return taskRepository.findByCreatorEmailAndArchived(email, true)
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

    @Override
    public Task createTask(Long panelId, String name, String priority, Integer orderIndex) {
        log.info("Creating task in panel ID: {}", panelId);

        // Найти панель по ID
        Panel panel = panelRepository.findById(panelId)
                .orElseThrow(() -> new IllegalArgumentException("Panel not found"));

        // Создать задачу и заполнить поля
        Task task = new Task();
        task.setName(name);
        task.setPriority(priority);
        task.setOrderIndex(orderIndex);
        task.setPanel(panel);

        Task savedTask = taskRepository.save(task);
        return savedTask;
    }

    @Override
    public Task assignUser(Long taskId, Long userId) {
        log.info("Assigning user ID: {} to task ID: {}", userId, taskId);
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        task.getAssignees().add(user);
        return taskRepository.save(task);
    }

    @Override
    public void addSubTask(Long taskId, CreateSubTaskDTO subTaskDTO) {
        log.info("Adding sub-task to task ID: {}", taskId);
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        Subtask subTask = new Subtask();
        subTask.setName(subTaskDTO.getName());
        subTask.setStatus(Status.NEW);
        subTask.setTask(task);

        task.getSubtasks().add(subTask);
        taskRepository.save(task);
    }

    @Override
    public void updateSubTask(Long subTaskId, UpdateSubTaskDTO subTaskDTO) {
        log.info("Updating sub-task ID: {}", subTaskId);
        Subtask subTask = subTaskRepository.findById(subTaskId)
                .orElseThrow(() -> new IllegalArgumentException("Sub-task not found"));

        if (subTaskDTO.getName() != null) {
            subTask.setName(subTaskDTO.getName());
        }
        if (subTaskDTO.getStatus() != null) {
            subTask.setStatus(subTaskDTO.getStatus());
        }
        subTaskRepository.save(subTask);
    }

    @Override
    public void deleteSubTask(Long subTaskId) {
        log.info("Deleting sub-task ID: {}", subTaskId);
        if (!subTaskRepository.existsById(subTaskId)) {
            throw new IllegalArgumentException("Sub-task not found");
        }
        subTaskRepository.deleteById(subTaskId);
    }

    @Override
    public void archiveTask(Long taskId) {
        log.info("Archiving task ID: {}", taskId);
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        task.setArchived(true);
        taskRepository.save(task);
    }


}
