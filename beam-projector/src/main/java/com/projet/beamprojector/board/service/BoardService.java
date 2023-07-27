package com.projet.beamprojector.board.service;

import com.projet.beamprojector.dto.board.BoardDTO;
import com.projet.beamprojector.board.repository.BoardRepository;
import com.projet.beamprojector.domain.entity.Board;
import com.projet.beamprojector.domain.entity.Member;
import com.projet.beamprojector.member.exception.MemberException;
import com.projet.beamprojector.member.exception.type.MemberErrorCode;
import com.projet.beamprojector.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.projet.beamprojector.dto.board.BoardDTO.BoardResponse.toResponse;

@Service
@RequiredArgsConstructor

public class BoardService {

private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

                 //글쓰기
    public void save(BoardDTO.CreateBoardRequest request, String memberId) {
        Member member = memberRepository.findByMemberId(memberId);
        Board boardEntity = Board.toSaveEntity(request, member);
        boardRepository.save(boardEntity);

    }
              // 글목록
    public List<BoardDTO.BoardResponse> findAll(Long boardNum) {
        List<Board> boardList = boardRepository.findAll();


        return BoardDTO.BoardResponse.toResponse(boardList);
    }
    @Transactional
    public void updateHits(Long boardNum) {
        boardRepository.updateHits(boardNum);

    }

    public BoardDTO.BoardResponse findById(Long id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            return toResponse(board) ;
        } else {
            return null;
        }
    }

    public BoardDTO.BoardResponse update(BoardDTO.UpdateBoardRequest request, String memberId) {
        Member member = memberRepository.findByMemberId(memberId);
//        Board boardEntity = Board.toUpdateEntity(request, member);
        Board board = boardRepository.findById(request.getBoardNum())
                .orElseThrow(() -> new MemberException(MemberErrorCode.DUPLICATED_MEMBER_ID));
        board.setTitle(request.getTitle());
        board.setContent(request.getContent());
        board.setCategoryName(request.getCategoryName());
        board.setModifiedAt(LocalDateTime.now());
        boardRepository.save(board);


        return toResponse(board);


    }


    public void delete(Long boardNum,String memberId) {
        Member member = memberRepository.findByMemberId(memberId);
        boardRepository.deleteById(boardNum);
    }

//    public Page<BoardDTO> paging(Pageable pageable) {
//        int page = pageable.getPageNumber() -1;
//        int pageLimit = 3; //한페이지에 보여줄 글 갯수
//        Page<Board> boardEntities = boardRepository.findAll(PageRequest.of(page,pageLimit, Sort.by(Sort.Direction.DESC, "id")));
//             System.out.println("boardEntities.getContent() =" + boardEntities.getContent());
//             System.out.println("boardEntities.getTotalElements() =" + boardEntities.getTotalElements());
//             System.out.println("boardEntities.getNumber() =" + boardEntities.getNumber());
//             System.out.println("boardEntities.getTotalPages() =" + boardEntities.getTotalPages());
//             System.out.println("boardEntities.getSize() =" + boardEntities.getSize());
//             System.out.println("boardEntities.hasPrevious() =" + boardEntities.hasPrevious());
//             System.out.println("boardEntities.isFirst() =" + boardEntities.isFirst());
//             System.out.println("boardEntities.isLast() =" + boardEntities.isLast());
//
//             Page<BoardDTO> boardDTOS = boardEntities.map(board -> new BoardDTO(board.getId(),board.getTitle(),board.getBoardHits(),board.getCreateAt()));
//             return boardDTOS;
//    }
}
