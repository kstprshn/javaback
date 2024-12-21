package ru.java.teamProject.SmartTaskFlow.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.java.teamProject.SmartTaskFlow.dto.subtask.CreateSubTaskDTO;
import ru.java.teamProject.SmartTaskFlow.entity.Subtask;
import ru.java.teamProject.SmartTaskFlow.entity.Task;
import ru.java.teamProject.SmartTaskFlow.entity.enums.Status;
import ru.java.teamProject.SmartTaskFlow.repository.SubtaskRepository;
import ru.java.teamProject.SmartTaskFlow.repository.TaskRepository;
import ru.java.teamProject.SmartTaskFlow.service.abstr.SubtaskService;

import java.util.NoSuchElementException;

@Service
@Transactional
public class SubtaskServiceImpl implements SubtaskService {

    private final SubtaskRepository subtaskRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public SubtaskServiceImpl(SubtaskRepository subtaskRepository, TaskRepository taskRepository) {
        this.subtaskRepository = subtaskRepository;
        this.taskRepository = taskRepository;
    }

    @Override
    public Subtask createSubtask(Long taskId, CreateSubTaskDTO request) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new NoSuchElementException("Task not found"));
        Subtask subtask = new Subtask();
        subtask.setName(request.getName());
        subtask.setStatus(Status.NEW);
        subtask.setTask(task);
        return subtaskRepository.save(subtask);
    }

    @Override
    public Subtask updateSubtask(Long subtaskId, CreateSubTaskDTO request) {
        Subtask subtask = subtaskRepository.findById(subtaskId).orElseThrow(() -> new NoSuchElementException("Subtask not found"));
        subtask.setName(request.getName());
        return subtaskRepository.save(subtask);
    }

    public void deleteSubtask(Long subtaskId) {
        subtaskRepository.deleteById(subtaskId);
    }
}
