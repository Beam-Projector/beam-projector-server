package com.projet.beamprojector.board.service;

import com.projet.beamprojector.board.DTO.BoardDTO;
import com.projet.beamprojector.board.repository.BoardRepository;
import com.projet.beamprojector.domain.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

private final BoardRepository boardRepository;

                 //글쓰기
    public void save(BoardDTO boardDTO) {
        Board boardEntity = Board.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);

    }
              // 글목록
    public List<BoardDTO> findAll() {
        List<Board> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for(Board board: boardEntityList) {
            boardDTOList.add(BoardDTO.toBoardDTO(board));
        }
        return boardDTOList;
    }
    @Transactional
    public void updateHits(Long id) {
        boardRepository.updateHits(id);

    }

    public BoardDTO findById(Long id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(board);
            return boardDTO;
        } else {
            return null;
        }
    }

    public BoardDTO update(BoardDTO boardDTO) {
        Board board = Board.toUpdateEntity(boardDTO);
        boardRepository.save(board);
        return findById(boardDTO.getId());


    }


}
