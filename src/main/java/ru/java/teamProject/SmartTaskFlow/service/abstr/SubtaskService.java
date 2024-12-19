package ru.java.teamProject.SmartTaskFlow.service.abstr;

import ru.java.teamProject.SmartTaskFlow.entity.Subtask;

import java.util.UUID;

public interface SubtaskService {
    Subtask createSubtask(Long taskId, String name);
    Subtask updateSubtask(Long subtaskId, String newName, String newStatus);
    void deleteSubtask(Long subtaskId);
}

