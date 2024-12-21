package ru.java.teamProject.SmartTaskFlow.service.abstr;

import ru.java.teamProject.SmartTaskFlow.dto.subtask.CreateSubTaskDTO;
import ru.java.teamProject.SmartTaskFlow.entity.Subtask;


public interface SubtaskService {
    Subtask createSubtask(Long taskId, CreateSubTaskDTO request);
    Subtask updateSubtask(Long subtaskId, CreateSubTaskDTO request);
    void deleteSubtask(Long subtaskId);
}

