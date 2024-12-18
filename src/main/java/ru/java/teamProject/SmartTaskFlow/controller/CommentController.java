package ru.java.teamProject.SmartTaskFlow.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.java.teamProject.SmartTaskFlow.entity.Comment;
import ru.java.teamProject.SmartTaskFlow.service.CommentService;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody Map<String, String> request) {
        UUID taskId = UUID.fromString(request.get("taskId"));
        UUID authorId = UUID.fromString(request.get("authorId"));
        String content = request.get("content");
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.addComment(taskId, authorId, content));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable UUID commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}