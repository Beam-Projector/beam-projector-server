package com.projet.beamprojector.board.DTO;

//data transfer object

import com.projet.beamprojector.domain.entity.Board;
import com.projet.beamprojector.domain.entity.Member;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@NoArgsConstructor// 기본생성자
@AllArgsConstructor // 모든 필드를 매게변수로 하는 생성자
public class BoardDTO {
    private Long id;
    private Member member;
    private String title;
    private String content;
    private int boardHits;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private Long categoryName;
    private String disclosure;

    public static BoardDTO toBoardDTO(Board board) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardDTO.getId());
        boardDTO.setTitle(boardDTO.getTitle());
        boardDTO.setMember(boardDTO.getMember());
        boardDTO.setContent(boardDTO.getContent());
        boardDTO.setCategoryName(board.getCategoryName());
        boardDTO.setDisclosure(board.getDisclosure());
        boardDTO.setBoardHits(board.getBoardHits());
        boardDTO.setCreateAt(board.getCreateAt());
        boardDTO.setCreateAt(board.getModifiedAt());
        return boardDTO;

    }
}
