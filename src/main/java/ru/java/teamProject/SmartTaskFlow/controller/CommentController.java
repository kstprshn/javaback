package ru.java.teamProject.SmartTaskFlow.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.java.teamProject.SmartTaskFlow.entity.Comment;
import ru.java.teamProject.SmartTaskFlow.service.CommentServiceImpl;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentServiceImpl commentServiceImpl;

    public CommentController(CommentServiceImpl commentServiceImpl) {
        this.commentServiceImpl = commentServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody Map<String, String> request) {
        UUID taskId = UUID.fromString(request.get("taskId"));
        UUID authorId = UUID.fromString(request.get("authorId"));
        String content = request.get("content");
        return ResponseEntity.status(HttpStatus.CREATED).body(commentServiceImpl.addComment(taskId, authorId, content));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable UUID commentId) {
        commentServiceImpl.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}