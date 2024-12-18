package ru.java.teamProject.SmartTaskFlow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.java.teamProject.SmartTaskFlow.entity.Task;

import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {


}