package com.projet.beamprojector.board.controller;

import com.projet.beamprojector.dto.board.BoardDTO;
import com.projet.beamprojector.board.service.BoardService;
import com.projet.beamprojector.config.Security.TokenMemberId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> save(@RequestBody BoardDTO.CreateBoardRequest request, @TokenMemberId String memberId){
        log.info("request => {}", request.getTitle());
        boardService.save(request, memberId);
        return ResponseEntity.ok("생성");

    }

    @GetMapping("/")
    public  ResponseEntity<List<BoardDTO.BoardResponse>> findAll(@Valid @RequestParam Long boardNum) {
        //List<BoardDTO> boardDTOList = boardService.findAll();
        //model.addAttribute("boardList", boardDTOList);
        return ResponseEntity.ok(boardService.findAll(boardNum));



    }

    @GetMapping("/search")
    public ResponseEntity<BoardDTO.BoardResponse> findById(@Valid @RequestParam Long boardNum) {
        boardService.updateHits(boardNum);
       //model.addAttribute("board", boardDTO);
        return ResponseEntity.ok( boardService.findById(boardNum));
    }

    @GetMapping ("/update")
    public String updateForm() {
        return "update";

    }
    @PutMapping ("/update")
    public ResponseEntity<BoardDTO.BoardResponse> update(@RequestBody BoardDTO.UpdateBoardRequest request, @TokenMemberId String memberId) {


        return ResponseEntity.ok( boardService.update(request ,memberId));

    }
    @DeleteMapping ("/delete")
    public ResponseEntity<?> delete(@Valid @RequestParam Long boardNum, @TokenMemberId String memberId) {
        boardService.delete(boardNum, memberId);
        return ResponseEntity.ok( "게시글이 삭제되었습니다 ");
    }

//    @GetMapping("/paging")
//    public String paging(@PageableDefault(page = 1)Pageable pageable, Model model) {
//        pageable.getPageNumber();
//        Page<BoardDTO> boardList = boardService.paging(pageable);
//        int blockLimit = 3;
//        int startPage = (((int)(Math.ceil((double)pageable.getPageNumber() / blockLimit))) -1) * blockLimit +1;
//        int endPage = ((startPage + blockLimit -1) < boardList.getTotalPages()) ? startPage + blockLimit -1 : boardList.getTotalPages();
//        model.addAttribute("boardList", boardList);
//        model.addAttribute("startPage", startPage);
//        model.addAttribute("endPage", endPage);
//        return "paging";
//
//    }


}
