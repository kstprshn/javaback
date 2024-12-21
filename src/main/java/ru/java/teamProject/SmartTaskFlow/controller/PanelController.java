package ru.java.teamProject.SmartTaskFlow.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.java.teamProject.SmartTaskFlow.dto.panel.CreatePanelDTO;
import ru.java.teamProject.SmartTaskFlow.dto.panel.PanelUpdateDTO;
import ru.java.teamProject.SmartTaskFlow.entity.Panel;
import ru.java.teamProject.SmartTaskFlow.service.PanelServiceImpl;


@RestController
@RequestMapping("/api/panels")
public class PanelController {

    private final PanelServiceImpl panelServiceImpl;

    public PanelController(PanelServiceImpl panelServiceImpl) {
        this.panelServiceImpl = panelServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Panel> createPanel(@RequestBody CreatePanelDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(panelServiceImpl.createPanel(request.getBoardId(), request.getName(), request.getOrderIndex()));
    }

    @PutMapping("/{panelId}")
    public ResponseEntity<Panel> updatePanel(@PathVariable Long panelId, @RequestBody PanelUpdateDTO request) {
        return ResponseEntity
                .ok(panelServiceImpl.updatePanel(panelId, request.getName()));
    }

    @DeleteMapping("/{panelId}")
    public ResponseEntity<Void> deletePanel(@PathVariable Long panelId) {
        panelServiceImpl.deletePanel(panelId);
        return ResponseEntity.noContent().build();
    }
}
