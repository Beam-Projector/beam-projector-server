package com.projet.beamprojector.dto.board;

import com.projet.beamprojector.domain.entity.Board;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class BoardDto {
    @ToString
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateBoardRequest {

        @NotBlank(message = "제목을 입력해 주세요.")
        @Schema(description = "제목 입력란")
        private String title;
        @NotBlank(message = "내용을 입력해 주세요.")
        @Schema(description = "내용 입력란")
        private String content;

        @Schema(description = "공개범위")
        private String disclosure;

        @Schema(description = "카테고리 분류번호")
        private Long categoryName;
    }

    @ToString
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateBoardRequest {

        @Schema(description = "게시글번호")
        private Long boardNum;

        @NotBlank(message = "제목을 입력해 주세요.")
        @Schema(description = "제목 입력란")
        private String title;
        @NotBlank(message = "내용을 입력해 주세요.")
        @Schema(description = "내용 입력란")
        private String content;

        @Schema(description = "공개범위")
        private String disclosure;

        @Schema(description = "카테고리 분류번호")
        private Long categoryName;

    }

    @ToString
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BoardResponse {

        @Schema(description = "게시글번호")
        private Long boardNum;
        @NotBlank(message = "제목을 입력해 주세요.")
        @Schema(description = "제목 입력란")
        private String title;
        @NotBlank(message = "내용을 입력해 주세요.")
        @Schema(description = "내용 입력란")
        private String content;
        @Schema(description = "공개범위")
        private String disclosure;

        @Schema(description = "게시글 조회수")
        private Long boardHits;

        @Schema(description = "카테고리 분류번호")
        private Long categoryName;

        @Schema(description = "작성시간")
        private LocalDateTime createAt;
        @Schema(description = "수정시간")
        private LocalDateTime modifiedAt;

        public static BoardResponse toResponse(Board board) {
            return BoardResponse.builder()
                    .boardNum(board.getId())
                    .title(board.getTitle())
                    .createAt(board.getCreateAt())
                    .content(board.getContent())
                    .boardHits(board.getBoardHits())
                    .categoryName(board.getCategoryName())
                    .disclosure(board.getDisclosure())
                    .modifiedAt(board.getModifiedAt())
                    .build();
        }

        public static List<BoardDto.BoardResponse> toResponse(List<Board> list) {
            return list.stream().map(BoardDto.BoardResponse::toResponse).collect(Collectors.toList());
        }


    }

}
