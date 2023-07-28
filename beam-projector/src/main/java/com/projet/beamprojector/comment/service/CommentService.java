package com.projet.beamprojector.comment.service;


import com.projet.beamprojector.board.repository.BoardRepository;
import com.projet.beamprojector.comment.exception.CommentException;
import com.projet.beamprojector.comment.exception.type.CommentErrorCode;
import com.projet.beamprojector.comment.repository.CommentRepository;
import com.projet.beamprojector.domain.entity.Board;
import com.projet.beamprojector.domain.entity.Comment;
import com.projet.beamprojector.domain.entity.Member;
import com.projet.beamprojector.dto.comment.CommentDto;
import com.projet.beamprojector.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    // 생성
    public CommentDto.CommentResponse createComment(CommentDto.CreateCommentRequest createCommentRequest, String memberId) {
        log.info("서비스 진입성공!!!");

        Member member = memberRepository.findByMemberId(memberId);
        Board board = boardRepository.findById(createCommentRequest.getBoardNum()).orElse(null);

        if (board == null || member == null) {
            throw new CommentException(
                    CommentErrorCode.COMMENT_MEMBER_ONLY_ALLOWED);
        }

        LocalDateTime createTime = LocalDateTime.now();
        Comment comment = new Comment();
        comment.setBoard(board);
        comment.setComments(createCommentRequest.getComments());
        comment.setCreateAt(createTime);
        comment.setMember(member);

        commentRepository.save(comment);
        return CommentDto.CommentResponse.toResponse(comment);
    }

    // 조회
    public List<CommentDto.CommentResponse> getComment(Long boardNum) {
        log.info("boardNum Type: {}", boardNum);
        log.info("commentRepository : {}", commentRepository.findAllByBoardId(boardNum).toString());
        List<Comment> comments = commentRepository.findAllByBoardId(boardNum);
        return CommentDto.CommentResponse.toResponse(comments);
    }

    // 수정
    public CommentDto.CommentResponse updateComment(
            String memberId,
            CommentDto.UpdateCommentRequest updateCommentRequest) {
        Comment comment = commentRepository.findById(updateCommentRequest.getCommentNum())
                .orElseThrow(() -> new CommentException(CommentErrorCode.COMMENT_INVALID_ACCESS));

        // writer : 작성자
        String writer = comment.getMember().getMemberId();

        if (!writer.equals(memberId)) {
            throw new CommentException(CommentErrorCode.COMMENT_INVALID_ACCESS);
        }

        LocalDateTime modifiedTime = LocalDateTime.now();
        comment.setComments(updateCommentRequest.getComments());
        comment.setModifiedAt(modifiedTime);
        commentRepository.save(comment);
        log.info("수정 완료");
        return CommentDto.CommentResponse.toResponse(comment);
    }

    // 삭제
    public void deleteComment(Long commentNum, String memberId
    ) {
        Comment comment = commentRepository.findById(commentNum)
                .orElseThrow(() -> new CommentException(CommentErrorCode.COMMENT_INVALID_ACCESS));

        String writer = comment.getMember().getMemberId();

        if (!writer.equals(memberId)) {
            throw new CommentException(CommentErrorCode.COMMENT_DELETE_FAILED);
        }

        commentRepository.deleteById(commentNum);
    }
}
