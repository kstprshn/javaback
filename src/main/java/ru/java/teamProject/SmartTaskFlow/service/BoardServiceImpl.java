package ru.java.teamProject.SmartTaskFlow.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.java.teamProject.SmartTaskFlow.dto.BoardDTO;
import ru.java.teamProject.SmartTaskFlow.dto.CreateBoardDTO;
import ru.java.teamProject.SmartTaskFlow.dto.UpdateBoardDTO;
import ru.java.teamProject.SmartTaskFlow.entity.Board;
import ru.java.teamProject.SmartTaskFlow.entity.User;
import ru.java.teamProject.SmartTaskFlow.repository.BoardRepository;
import ru.java.teamProject.SmartTaskFlow.repository.UserRepository;
import ru.java.teamProject.SmartTaskFlow.service.abstr.BoardService;

import java.util.List;


@Service
@Transactional
@Slf4j
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public BoardServiceImpl(BoardRepository boardRepository, UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createBoard(String email, CreateBoardDTO boardDTO) {
        log.info("Creating board for user: {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Board board = new Board();
        board.setName(boardDTO.getName());
        boardRepository.save(board);
    }

    @Override
    public void updateBoardName(Long boardId, UpdateBoardDTO boardDTO) {
        log.info("Updating board name for board ID: {}", boardId);
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));
        board.setName(boardDTO.getName());
        boardRepository.save(board);
    }

    @Override
    public void deleteBoard(Long boardId) {
        log.info("Deleting board with ID: {}", boardId);
        boardRepository.deleteById(boardId);
    }

    @Override
    public Board addMember(Long boardId, Long userId) {
        log.info("Adding member to board ID: {}", boardId);
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        board.getMembers().add(user);
        return boardRepository.save(board);
    }

    @Override
    public List<BoardDTO> getAllBoards(String email) {
        log.info("Fetching all boards for user: {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return boardRepository.findByMembersContaining(user)
                .stream()
                .map(board -> {
                    BoardDTO boardDTO = new BoardDTO();
                    boardDTO.setId(board.getId());
                    boardDTO.setName(board.getName());
                    boardDTO.setMembers(board.getMembers().stream()
                            .map(User::getEmail)
                            .toList());
                    return boardDTO;
                })
                .toList();
    }
}



