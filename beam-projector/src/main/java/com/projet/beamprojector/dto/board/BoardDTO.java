package com.projet.beamprojector.dto.board;

//data transfer object

import com.projet.beamprojector.domain.entity.Board;
import com.projet.beamprojector.domain.entity.Comment;
import com.projet.beamprojector.dto.comment.CommentDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;



public class BoardDTO {
//    private Long id;
////    private String memberId;
//    private String title;
//    private String content;
//    private int boardHits;
////    private LocalDateTime createAt;
////    private String modifiedAt;
//    private Long categoryName;
//    private String disclosure;
//
//    public BoardDTO(Long id,  String title, int boardHits, LocalDateTime createAt) {
//        this.id = id;
//
//        this.title = title;
//        this.boardHits = boardHits;
////        this.createAt = createAt;
    //}



//    public static BoardDTO toBoardDTO(Board board) {
//        BoardDTO boardDTO = new BoardDTO();
//        boardDTO.setId(boardDTO.getId());
//        boardDTO.setTitle(boardDTO.getTitle());
////        boardDTO.setMemberId(boardDTO.getMemberId());
//        boardDTO.setContent(boardDTO.getContent());
//        boardDTO.setCategoryName(board.getCategoryName());
//        boardDTO.setDisclosure(board.getDisclosure());
//        boardDTO.setBoardHits(board.getBoardHits());
////        boardDTO.setCreateAt(board.getCreateAt());
////        boardDTO.setCreateAt(board.getModifiedAt());
//        return boardDTO;
//
//    }

    @ToString
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateBoardRequest{
        private String title;
        private String content;
        private String disclosure;
        private Long categoryName;


//        @NotBlank(message = "내용을 입력해 주세요.")
//        @Schema(description = "댓글 입력란")
//        private String comments;
//
//
//        @Schema(description = "작성시간")
//        private LocalDateTime createAt ;

    }
    @ToString
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateBoardRequest{
        private Long boardNum;
        private String title;
        private String content;
        private String disclosure;
        private Long categoryName;


//        @NotBlank(message = "내용을 입력해 주세요.")
//        @Schema(description = "댓글 입력란")
//        private String comments;
//
//
//        @Schema(description = "작성시간")
//        private LocalDateTime createAt ;

    }
    @ToString
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BoardResponse {
        private Long boardNum;
        private String memberId;
        private String title;
        private String content;
        private String disclosure;
        private Long categoryName;
        private LocalDateTime createAt;
        private LocalDateTime modifiedAt;

        public static BoardResponse toResponse(Board board){
            return BoardResponse.builder()
                    .memberId(board.getMember().getMemberId())
                    .boardNum(board.getId())
                    .title(board.getTitle())
                    .createAt(board.getCreateAt())
                    .content(board.getContent())
                    .categoryName(board.getCategoryName())
                    .disclosure(board.getDisclosure())
                    .modifiedAt(board.getModifiedAt())
                    .build();
        }
        public static List<BoardDTO.BoardResponse> toResponse(List<Board> list){
            return list.stream().map(BoardDTO.BoardResponse::toResponse).collect(Collectors.toList());
        }


//        @NotBlank(message = "내용을 입력해 주세요.")
//        @Schema(description = "댓글 입력란")
//        private String comments;
//
//
//        @Schema(description = "작성시간")
//        private LocalDateTime createAt ;

    }

}
