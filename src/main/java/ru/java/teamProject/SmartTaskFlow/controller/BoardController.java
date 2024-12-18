package ru.java.teamProject.SmartTaskFlow.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.java.teamProject.SmartTaskFlow.dto.BoardDTO;
import ru.java.teamProject.SmartTaskFlow.dto.CreateBoardDTO;
import ru.java.teamProject.SmartTaskFlow.dto.UpdateBoardDTO;
import ru.java.teamProject.SmartTaskFlow.entity.Board;
import ru.java.teamProject.SmartTaskFlow.service.BoardServiceImpl;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardServiceImpl boardService;

    // Create a new board
    @PostMapping
    public ResponseEntity<?> createBoard(Authentication authentication, @Valid @RequestBody CreateBoardDTO boardDTO) {
        String email = authentication.getName();
        boardService.createBoard(email, boardDTO);
        return ResponseEntity.ok("Board created successfully.");
    }

    // Update board name
    @PutMapping("/{boardId}")
    public ResponseEntity<?> updateBoardName(@PathVariable Long boardId, @Valid @RequestBody UpdateBoardDTO boardDTO) {
        boardService.updateBoardName(boardId, boardDTO);
        return ResponseEntity.ok("Board name updated successfully.");
    }

    // Delete a board
    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
        return ResponseEntity.ok("Board deleted successfully.");
    }

    @PutMapping("/{boardId}/members/{userId}")
    public ResponseEntity<Board> addMember(@PathVariable UUID boardId, @PathVariable UUID userId) {
        return ResponseEntity.ok(boardService.addMember(boardId, userId));
    }

    // Get all boards for a user
    @GetMapping
    public ResponseEntity<List<BoardDTO>> getAllBoards(Authentication authentication) {
        String email = authentication.getName();
        List<BoardDTO> boards = boardService.getAllBoards(email);
        return ResponseEntity.ok(boards);
    }
}


