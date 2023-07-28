package com.projet.beamprojector.board.controller;

import com.projet.beamprojector.board.service.BoardService;
import com.projet.beamprojector.config.Security.TokenMemberId;
import com.projet.beamprojector.dto.board.BoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/save")
    public ResponseEntity<?> save(
            @RequestBody BoardDto.CreateBoardRequest request,
            @TokenMemberId String memberId) {
        log.info("request => {}", request.getTitle());
        return ResponseEntity.ok(boardService.save(request, memberId));
    }

    @GetMapping("/")
    public ResponseEntity<List<BoardDto.BoardResponse>> boards() {

        return ResponseEntity.ok(boardService.boardAllRead());
    }

    // 상세페이지 조회
    @GetMapping("/{boardNum}")
    public ResponseEntity<BoardDto.BoardResponse> findById(
            @Valid @PathVariable("boardNum") Long boardNum) {

        boardService.updateHits(boardNum);
        return ResponseEntity.ok(boardService.findById(boardNum));
    }

    @PutMapping("/")
    public ResponseEntity<BoardDto.BoardResponse> update(
            @RequestBody BoardDto.UpdateBoardRequest request,
            @TokenMemberId String memberId) {

        return ResponseEntity.ok(boardService.update(request, memberId));

    }

    @DeleteMapping("/{boardNum}")
    public ResponseEntity<?> delete(
            @Valid @PathVariable("boardNum") Long boardNum,
            @TokenMemberId String memberId) {

        boardService.delete(boardNum, memberId);
        return ResponseEntity.ok("게시글이 삭제되었습니다 ");
    }


}
