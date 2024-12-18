package ru.java.teamProject.SmartTaskFlow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.java.teamProject.SmartTaskFlow.entity.Panel;

import java.util.UUID;

@Repository
public interface PanelRepository extends JpaRepository<Panel, UUID> {
}