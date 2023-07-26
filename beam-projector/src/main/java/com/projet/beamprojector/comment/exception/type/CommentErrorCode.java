package com.projet.beamprojector.comment.exception.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommentErrorCode {

    DUPLICATED_COMMENT_CREATE("회원만 가능합니다!"),
    DUPLICATED_COMMENT_DELETE_SUCCESS("삭제 성공"),
    DUPLICATED_COMMENT_DELETE_FALSE("삭제 실패"),
    DUPLICATED_COMMENT_BASIC("잘못된 접근입니다!")
    ;

    private final String description;

}
