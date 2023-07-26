package com.projet.beamprojector.comment.exception;

import com.projet.beamprojector.comment.exception.type.CommentErrorCode;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentErrorResponse {
    private CommentErrorCode commentErrorCode;
    private String errorMessage;

}
