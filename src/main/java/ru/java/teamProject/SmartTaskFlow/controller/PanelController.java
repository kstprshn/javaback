package ru.java.teamProject.SmartTaskFlow.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.java.teamProject.SmartTaskFlow.entity.Panel;
import ru.java.teamProject.SmartTaskFlow.service.PanelService;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/panels")
public class PanelController {

    private final PanelService panelService;

    public PanelController(PanelService panelService) {
        this.panelService = panelService;
    }

    @PostMapping
    public ResponseEntity<Panel> createPanel(@RequestBody Map<String, String> request) {
        UUID boardId = UUID.fromString(request.get("boardId"));
        String name = request.get("name");
        Integer orderIndex = Integer.parseInt(request.get("orderIndex"));
        return ResponseEntity.status(HttpStatus.CREATED).body(panelService.createPanel(boardId, name, orderIndex));
    }

    @PutMapping("/{panelId}")
    public ResponseEntity<Panel> updatePanel(@PathVariable UUID panelId, @RequestBody Map<String, String> request) {
        String newName = request.get("name");
        return ResponseEntity.ok(panelService.updatePanel(panelId, newName));
    }

    @DeleteMapping("/{panelId}")
    public ResponseEntity<Void> deletePanel(@PathVariable UUID panelId) {
        panelService.deletePanel(panelId);
        return ResponseEntity.noContent().build();
    }
}
