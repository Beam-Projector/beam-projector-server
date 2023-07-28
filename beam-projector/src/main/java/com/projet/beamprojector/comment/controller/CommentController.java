package com.projet.beamprojector.comment.controller;


import com.projet.beamprojector.comment.service.CommentService;
import com.projet.beamprojector.config.Security.TokenMemberId;
import com.projet.beamprojector.domain.entity.Comment;
import com.projet.beamprojector.dto.comment.CommentDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Console;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/")
    public ResponseEntity<?> createComment(
            @TokenMemberId String memberId,
            @Validated @RequestBody CommentDto.CreateCommentRequest createCommentRequest) {

        return ResponseEntity.ok(commentService.createComment(createCommentRequest, memberId));

        // 추후 exceptionHandler 구현되면 그 때 다시 처리 필요.
/*
        try {
            Comment comment = commentService.createComment(createCommentRequest);
            return ResponseEntity.ok(comment);
        }catch (CommentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
*/
    }

    @GetMapping("/")
    public List<CommentDto.CommentResponse> getComment(@Valid @RequestParam Long boardNum) {
        log.info("조회한다!!!!");
        return commentService.getComment(boardNum);
    }

    @PutMapping("/")
    public CommentDto.CommentResponse updateComment(
            @TokenMemberId String memberId,
            @Validated @RequestBody CommentDto.UpdateCommentRequest updateCommentRequest) {
        log.info("수정 컨트롤러 진입 >>>>>>");

        return commentService.updateComment(memberId, updateCommentRequest);
    }

    @DeleteMapping("/")
    public void deleteComment(@Valid @RequestParam Long commentNum, @TokenMemberId String memberId) {
        log.info("삭제 컨트롤러 들어왔다!!! 들어왔다");
        commentService.deleteComment(commentNum, memberId);
    }

}


