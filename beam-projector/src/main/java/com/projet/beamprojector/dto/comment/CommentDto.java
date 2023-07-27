package com.projet.beamprojector.dto.comment;

import com.projet.beamprojector.domain.entity.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CommentDto {

    @ToString
    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateCommentRequest {
        @Schema(description = "게시글번호")
        private Long boardNum;
        @Schema(description = "회원번호")
        private Long memberNum;
        @NotBlank(message = "내용을 입력해 주세요.")
        @Schema(description = "댓글 입력란")
        private String comments;
        @Schema(description = "작성시간")
        private LocalDateTime createAt;

    }

    @ToString
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UpdateCommentRequest {
        @NotBlank(message = "내용을 입력해 주세요.")
        @Schema(description = "댓글 입력란")
        private String comments;
        @Schema(description = "게시글번호")
        private Long boardNum;
        @Schema(description = "댓글번호")
        private Long commentNum;
    }

    @ToString
    @Builder
    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CommentResponse {

        @Schema(description = "게시글번호")
        private Long boardNum;
        @Schema(description = "댓글번호")
        private Long commentNum;

        @Schema(description = "회원닉네임")
        private String nickName;

        @NotBlank(message = "내용을 입력해 주세요.")
        @Schema(description = "댓글 입력란")
        private String comments;

        @Schema(description = "작성시간")
        private LocalDateTime createAt;

        public static CommentResponse toResponse(Comment comment) {
            return CommentResponse.builder()
                    .boardNum(comment.getBoard().getId())
                    .commentNum(comment.getId())
                    .nickName(comment.getMember().getNickName())
                    .comments(comment.getComments())
                    .createAt(comment.getCreateAt())
                    .build();
        }

        public static List<CommentResponse> toResponse(List<Comment> list) {
            return list.stream().map(CommentResponse::toResponse).collect(Collectors.toList());
        }
    }


}
