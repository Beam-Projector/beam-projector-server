package com.projet.beamprojector.comment.exception;

import com.projet.beamprojector.comment.exception.type.CommentErrorCode;
import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class CommentException extends RuntimeException{

    private CommentErrorCode commentErrorCode;
    private String errorMessage;

    public CommentException(CommentErrorCode commentErrorCode) {
        this.commentErrorCode = commentErrorCode;
        this.errorMessage = commentErrorCode.getDescription();
    }

}
