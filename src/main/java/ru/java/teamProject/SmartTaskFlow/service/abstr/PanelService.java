package ru.java.teamProject.SmartTaskFlow.service.abstr;

import ru.java.teamProject.SmartTaskFlow.entity.Panel;

import java.util.UUID;

public interface PanelService {
    Panel createPanel(UUID boardId, String name, Integer orderIndex);
    Panel updatePanel(UUID panelId, String newName);
    void deletePanel(UUID panelId);
}
