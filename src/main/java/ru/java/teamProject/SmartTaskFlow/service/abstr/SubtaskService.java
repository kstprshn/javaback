package ru.java.teamProject.SmartTaskFlow.service.abstr;

import ru.java.teamProject.SmartTaskFlow.entity.Subtask;


public interface SubtaskService {
    Subtask createSubtask(Long taskId, String name);
    Subtask updateSubtask(Long subtaskId, String newName);
    void deleteSubtask(Long subtaskId);
}

