package com.projet.beamprojector.board.service;

import com.projet.beamprojector.board.repository.BoardRepository;
import com.projet.beamprojector.domain.entity.Board;
import com.projet.beamprojector.domain.entity.Member;
import com.projet.beamprojector.dto.board.BoardDto;
import com.projet.beamprojector.member.exception.MemberException;
import com.projet.beamprojector.member.exception.type.MemberErrorCode;
import com.projet.beamprojector.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.projet.beamprojector.dto.board.BoardDto.BoardResponse.toResponse;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    //글쓰기
    public BoardDto.BoardResponse save(BoardDto.CreateBoardRequest request, String memberId) {
        Member member = memberRepository.findByMemberId(memberId);
        Board boardEntity = Board.toSaveEntity(request, member);
        boardRepository.save(boardEntity);
        return BoardDto.BoardResponse.toResponse(boardEntity);
    }

    // 카테고리 전체페이지 조회
    public List<BoardDto.BoardResponse> boardAllRead() {
        List<Board> boardList = boardRepository.findAll();
        return BoardDto.BoardResponse.toResponse(boardList);
    }

    // 게시글 상세페이지
    public BoardDto.BoardResponse findById(Long boardNum) {
        Optional<Board> optionalBoard = boardRepository.findById(boardNum);
        return optionalBoard.map(BoardDto.BoardResponse::toResponse).orElse(null);
    }
//    public BoardDTO.BoardResponse findById(Long boardNum) {
//        Optional<Board> optionalBoard = boardRepository.findById(boardNum);
//        if (optionalBoard.isPresent()) {
//            Board board = optionalBoard.get();
//            return toResponse(board);
//        } else {
//            return null;
//        }
//    }

    // 수정
    public BoardDto.BoardResponse update(BoardDto.UpdateBoardRequest request, String memberId) {
        Member writer = memberRepository.findByMemberId(memberId);
        Board board = boardRepository.findById(request.getBoardNum())
                .orElseThrow(() -> new MemberException(MemberErrorCode.DUPLICATED_MEMBER_ID));

        if (!board.getMember().equals(writer)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "이 게시글을 수정할 권한이 없습니다.");
        }

        board.setTitle(request.getTitle());
        board.setContent(request.getContent());
        board.setModifiedAt(LocalDateTime.now());

        boardRepository.save(board);

        return toResponse(board);
    }

    // 삭제
    public void delete(Long boardNum, String memberId) {
        Member member = memberRepository.findByMemberId(memberId);
        boardRepository.deleteById(boardNum);
    }

    // 조회수
    @Transactional
    public void updateHits(Long boardNum) {
        boardRepository.updateHits(boardNum);
    }
}
