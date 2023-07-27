package com.projet.beamprojector.board.controller;

import com.projet.beamprojector.board.DTO.BoardDTO;
import com.projet.beamprojector.board.service.BoardService;
import com.projet.beamprojector.config.Security.TokenMemberId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import javax.validation.Valid;
import java.util.List;
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/save")
    public String saveForm() {return "save";}
    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody BoardDTO boardDTO, @TokenMemberId String memberId){
        log.info("request => {}", boardDTO.getTitle());
        boardService.save(boardDTO, memberId);
        return ResponseEntity.ok(200);

    }

    @GetMapping("/")
    public  ResponseEntity<?> findAll(@Valid @RequestParam Long boardNum) {
        boardService.findAll(boardNum);
        //List<BoardDTO> boardDTOList = boardService.findAll();
        //model.addAttribute("boardList", boardDTOList);
        return ResponseEntity.ok( "조회");


    }

//    @GetMapping("/{id}")
//    public String findById(@PathVariable Long id, Model model) {
//        boardService.updateHits(id);
//        BoardDTO boardDTO = boardService.findById(id);
//        model.addAttribute("board", boardDTO);
//        return "detail";
   // }

    @GetMapping ("/update")
    public String updateForm() {
        return "update";

    }
    @PutMapping ("/update")
    public ResponseEntity<?> update(@Validated @RequestBody BoardDTO boardDTO, @TokenMemberId String memberId) {

        boardService.update(boardDTO ,memberId);
        return ResponseEntity.ok( "게시글이 수정되었습니다 ");

    }
    @DeleteMapping ("/delete")
    public ResponseEntity<?> delete(@Valid @RequestParam Long boardNum, @TokenMemberId String memberId) {
        boardService.delete(boardNum, memberId);
        return ResponseEntity.ok( "게시글이 삭제되었습니다 ");
    }

    @GetMapping("/paging")
    public String paging(@PageableDefault(page = 1)Pageable pageable, Model model) {
        pageable.getPageNumber();
        Page<BoardDTO> boardList = boardService.paging(pageable);
        int blockLimit = 3;
        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) -1) * blockLimit +1;
        int endPage = ((startPage + blockLimit -1) < boardList.getTotalPages()) ? startPage + blockLimit -1 : boardList.getTotalPages();
        model.addAttribute("boardList", boardList);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "paging";

    }


}
