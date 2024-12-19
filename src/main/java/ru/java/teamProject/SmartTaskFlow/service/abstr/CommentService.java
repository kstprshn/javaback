package ru.java.teamProject.SmartTaskFlow.service.abstr;

import ru.java.teamProject.SmartTaskFlow.entity.Comment;


public interface CommentService {
    Comment addComment(Long taskId, Long authorId, String content);
    void deleteComment(Long commentId);
}
