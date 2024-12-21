package ru.java.teamProject.SmartTaskFlow.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.java.teamProject.SmartTaskFlow.dto.board.BoardDTO;
import ru.java.teamProject.SmartTaskFlow.dto.board.CreateBoardDTO;
import ru.java.teamProject.SmartTaskFlow.dto.board.UpdateBoardDTO;
import ru.java.teamProject.SmartTaskFlow.entity.Board;
import ru.java.teamProject.SmartTaskFlow.entity.User;
import ru.java.teamProject.SmartTaskFlow.repository.BoardRepository;
import ru.java.teamProject.SmartTaskFlow.repository.UserRepository;
import ru.java.teamProject.SmartTaskFlow.service.abstr.BoardService;

import java.util.List;
import java.util.stream.Collectors;


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

    private BoardDTO buildDto(Board board){
        return new BoardDTO()
                .setId(board.getId())
                .setName(board.getName())
                .setArchived(board.getArchived())
                .setMembers(board.getMembers().stream().map(User::getId).collect(Collectors.toList()));
    }


    @Override
    public BoardDTO createBoard(String email, CreateBoardDTO boardDTO) {
        Board board = new Board();
        board.setName(boardDTO.getName());
        boardRepository.save(board);

        return buildDto(board);
    }

    @Override
    public BoardDTO updateBoardName(Long boardId, UpdateBoardDTO boardDTO) {
        log.info("Updating board name for board ID: {}", boardId);
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));
        board.setName(boardDTO.getName());
        boardRepository.save(board);
        return buildDto(board);
    }

    @Override
    public void deleteBoard(Long boardId) {
        log.info("Deleting board with ID: {}", boardId);
        boardRepository.deleteById(boardId);
    }

    @Override
    public BoardDTO addMember(Long boardId, Long userId) {
        log.info("Adding member to board ID: {}", boardId);
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        board.getMembers().add(user);
        user.getBoards().add(board);

        boardRepository.save(board);
        userRepository.save(user);

        return buildDto(board);
    }

    @Override
    public List<BoardDTO> getAllBoards(String email) {
        log.info("Fetching all boards for user: {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        return boardRepository.findByMembersContaining(user)
                .stream()
                .map(this::buildDto).toList();
    }
}



