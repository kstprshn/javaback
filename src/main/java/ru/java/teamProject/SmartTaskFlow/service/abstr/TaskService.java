package ru.java.teamProject.SmartTaskFlow.service.abstr;

import ru.java.teamProject.SmartTaskFlow.dto.comment.CreateCommentDTO;
import ru.java.teamProject.SmartTaskFlow.dto.subtask.CreateSubTaskDTO;
import ru.java.teamProject.SmartTaskFlow.dto.subtask.SubTaskDTO;
import ru.java.teamProject.SmartTaskFlow.dto.subtask.UpdateSubTaskDTO;
import ru.java.teamProject.SmartTaskFlow.dto.task.CreateTaskDTO;
import ru.java.teamProject.SmartTaskFlow.dto.task.TaskDTO;
import ru.java.teamProject.SmartTaskFlow.dto.task.UpdateTaskDTO;

import java.util.List;


public interface TaskService {
    List<TaskDTO> getTasksInColumn(Long columnId);
    TaskDTO addTaskToColumn(Long columnId, CreateTaskDTO taskDTO);
    TaskDTO updateTask(Long taskId, UpdateTaskDTO taskDTO);
    void deleteTask(Long taskId);
    TaskDTO moveTask(Long taskId, Long targetColumnId);
    TaskDTO addCommentToTask(Long taskId, CreateCommentDTO commentDTO);
    TaskDTO createTask(Long panelId, String name, String priority, Integer orderIndex);
    TaskDTO assignUser(Long taskId, Long userId);

    List<TaskDTO> getArchivedTasks(String email);


    TaskDTO archiveTask(Long taskId);
    TaskDTO unArchiveTask(Long taskId);
}
