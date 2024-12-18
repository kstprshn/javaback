package ru.java.teamProject.SmartTaskFlow.service.abstr;

import ru.java.teamProject.SmartTaskFlow.entity.Subtask;

import java.util.UUID;

public interface SubtaskService {
    Subtask createSubtask(UUID taskId, String name);
    Subtask updateSubtask(UUID subtaskId, String newName, String newStatus);
    void deleteSubtask(UUID subtaskId);
}

