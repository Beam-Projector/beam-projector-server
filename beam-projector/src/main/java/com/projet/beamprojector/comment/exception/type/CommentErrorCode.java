package com.projet.beamprojector.comment.exception.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommentErrorCode {

    COMMENT_MEMBER_ONLY_ALLOWED("회원만 가능합니다!"),
    COMMENT_DELETE_SUCCESS("삭제 성공"),
    COMMENT_DELETE_FAILED("삭제 실패"),
    COMMENT_INVALID_ACCESS("잘못된 접근입니다!")
    ;

    private final String description;

}
