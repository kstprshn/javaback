package ru.java.teamProject.SmartTaskFlow.service;

import org.springframework.stereotype.Service;
import ru.java.teamProject.SmartTaskFlow.entity.Board;
import ru.java.teamProject.SmartTaskFlow.entity.Panel;
import ru.java.teamProject.SmartTaskFlow.repository.BoardRepository;
import ru.java.teamProject.SmartTaskFlow.repository.PanelRepository;
import ru.java.teamProject.SmartTaskFlow.service.abstr.PanelService;

import java.util.NoSuchElementException;

@Service
public class PanelServiceImpl implements PanelService {

    private final PanelRepository panelRepository;
    private final BoardRepository boardRepository;

    public PanelServiceImpl(PanelRepository panelRepository, BoardRepository boardRepository) {
        this.panelRepository = panelRepository;
        this.boardRepository = boardRepository;
    }

    public Panel createPanel(Long boardId, String name, Integer orderIndex) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new NoSuchElementException("Board not found"));
        Panel panel = new Panel();
        panel.setName(name);
        panel.setOrderIndex(orderIndex);
        panel.setBoard(board);
        return panelRepository.save(panel);
    }

    public Panel updatePanel(Long panelId, String newName) {
        Panel panel = panelRepository.findById(panelId).orElseThrow(() -> new NoSuchElementException("Panel not found"));
        panel.setName(newName);
        return panelRepository.save(panel);
    }

    public void deletePanel(Long panelId) {
        panelRepository.deleteById(panelId);
    }
}
