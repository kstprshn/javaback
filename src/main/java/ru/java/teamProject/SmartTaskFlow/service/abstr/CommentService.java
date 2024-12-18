package ru.java.teamProject.SmartTaskFlow.service.abstr;

import ru.java.teamProject.SmartTaskFlow.entity.Comment;

import java.util.UUID;

public interface CommentService {
    Comment addComment(UUID taskId, UUID authorId, String content);
    void deleteComment(UUID commentId);
}
