package ru.java.teamProject.SmartTaskFlow.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddCommentDTO {
    private Long taskId;
    private Long authorId;
    private String content;
}
