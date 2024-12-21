package ru.java.teamProject.SmartTaskFlow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.java.teamProject.SmartTaskFlow.dto.AddCommentDTO;
import ru.java.teamProject.SmartTaskFlow.entity.Comment;
import ru.java.teamProject.SmartTaskFlow.service.CommentServiceImpl;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentServiceImpl commentServiceImpl;

    @Autowired
    public CommentController(CommentServiceImpl commentServiceImpl) {
        this.commentServiceImpl = commentServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody AddCommentDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commentServiceImpl.addComment(request.getTaskId(), request.getAuthorId(), request.getContent()));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentServiceImpl.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}