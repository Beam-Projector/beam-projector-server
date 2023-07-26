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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository; // 생성되면 주석해제 후 작업 시작

    // 생성
    public Comment createComment(CommentDto.CreateCommentRequest createCommentRequest){
        log.info("서비스 진입성공!!!");
        log.info("CommentDto ,createCommentRequest : {}", createCommentRequest.getMemberNum());
        log.info("createCommentRequest Type: {}", createCommentRequest.getMemberNum());
        Member member = memberRepository.findByMemberId(String.valueOf(createCommentRequest.getMemberNum()));
        log.info("Service!!!!!! >>>>>>>>>>>>>>>>> :{}",createCommentRequest.getMemberNum());
        Board board = boardRepository.findById(createCommentRequest.getBoardNum()).orElse(null);

        // jwt : 토큰 없으면 빠이 !
        // jwt로 1차적인 글쓰기 버튼눌렀을때는 검증ok
        // -> 2차로 저장버튼 눌렀을 시 로그인상태인지 확인 하기 (중요)

        // if 분기처리해야함 board , member 없는경우
        if(board == null || member == null){
            throw new CommentException(
                    CommentErrorCode.DUPLICATED_COMMENT_CREATE);
        }

        // set 직전에 꼭 검증 하기!!
        LocalDateTime createTime = LocalDateTime.now();
        Comment comment = new Comment();
        comment.setBoard(board);
        comment.setComments(createCommentRequest.getComments());
        comment.setCreateAt(createTime);
        comment.setMember(member);


        return commentRepository.save(comment);
    }

    // 조회
    public List<CommentDto.CommentResponse> getComment(Long boardNum) {
        log.info("boardNum Type: {}",boardNum);
        log.info("commentRepository : {}",commentRepository.findAllByBoardId(boardNum).toString());
        List<Comment>comments = commentRepository.findAllByBoardId(boardNum);
//        return commentRepository.findAllByBoardId(boardNum);
        return CommentDto.CommentResponse.toResponse(comments);
    }

    // 수정
    public CommentDto.CommentResponse updateComment(CommentDto.UpdateCommentRequest updateCommentRequest) {
        log.info("수정 서비스 진입!111");
//        Optional<Comment> comment = commentRepository.findById(updateCommentRequest.getCommentNum());
        log.info("수정 서비스 진입!222");
        Comment comment = commentRepository.findById(updateCommentRequest.getCommentNum())
                .orElseThrow(() -> new CommentException(CommentErrorCode.DUPLICATED_COMMENT_BASIC));

        // jwt를 통해서 지금 사용자와 댓글의 사용자를 비교 가능하게 함.
        String member = comment.getMember().getMemberId();


        LocalDateTime modifiedTime = LocalDateTime.now();
        comment.setComments(updateCommentRequest.getComments());
        comment.setModifiedAt(modifiedTime);


        // 검증은 무조건 수정 앞에..!
        commentRepository.save(comment);

/*        if (optionalComment.isPresent()) {
            // 댓글이 존재하는 경우, 해당 댓글 가져오기
//            Comment comment = optionalComment.get();

            // commentResponse객체에서 새로운 댓글내용 가져와서 엔티티에 설정
            comment.setComments(comment.getComments());

            // 댓글 수정시간 변경
            comment.setModifiedAt(LocalDateTime.now());

            // 댓글 엔티티 DB저장 -> 업데이트 댓글 받아옴
            comment = commentRepository.save(comment);

            // 업데이트 댓글 -> CommentDto.CommentResponse로 반환
            return CommentDto.CommentResponse.toResponse(comment);
        }*/

        log.info("수정 완료");
        // 댓글이 없으면 아무것도 하지 않음.
        return CommentDto.CommentResponse.toResponse(comment);
    }

    // 삭제
    public void deleteComment(Long commentNum) {
//        Long commentNum = deleteCommentRequest.getCommentNum();
//        Long memberNum = deleteCommentRequest.getMemberNum();

        // 회원권한 확인 -> 삭제작업 처리
//        Member member = memberRepository.findById(String.valueOf(memberNum))
//                .orElseThrow(() -> new CommentException(CommentErrorCode.DUPLICATED_COMMENT_BASIC));
        log.info("삭제 서비스 들어왔다");
        log.info("11 commentNum Type: {}",commentNum);
        Comment comment = commentRepository.findById(commentNum)
                .orElseThrow(() -> new CommentException(CommentErrorCode.DUPLICATED_COMMENT_BASIC));
        log.info("22 commentNum Type: {}",commentNum);
//        if(!comment.getMember().getId().equals(memberNum)){
//            throw new ResponseStatusException(HttpStatus.FORBIDDEN, CommentErrorCode.DUPLICATED_COMMENT_DELETE_FALSE.toString());
//        }


        commentRepository.deleteById(commentNum);
    }
}
