package ru.java.teamProject.SmartTaskFlow.service.abstr;

import ru.java.teamProject.SmartTaskFlow.entity.Panel;

import java.util.UUID;

public interface PanelService {
    Panel createPanel(Long boardId, String name, Integer orderIndex);
    Panel updatePanel(Long panelId, String newName);
    void deletePanel(Long panelId);
}
