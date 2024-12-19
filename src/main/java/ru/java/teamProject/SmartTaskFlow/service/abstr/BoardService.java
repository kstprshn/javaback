package ru.java.teamProject.SmartTaskFlow.service.abstr;

import ru.java.teamProject.SmartTaskFlow.dto.BoardDTO;
import ru.java.teamProject.SmartTaskFlow.dto.CreateBoardDTO;
import ru.java.teamProject.SmartTaskFlow.dto.UpdateBoardDTO;
import ru.java.teamProject.SmartTaskFlow.entity.Board;

import java.util.List;
import java.util.UUID;

public interface BoardService {
    void createBoard(String email, CreateBoardDTO boardDTO);
    void updateBoardName(Long boardId, UpdateBoardDTO boardDTO);
    void deleteBoard(Long boardId);
    Board addMember(Long boardId, Long userId);
    List<BoardDTO> getAllBoards(String email);
}
