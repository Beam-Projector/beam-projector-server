package com.projet.beamprojector.member.exception.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberErrorCode {
	DUPLICATED_MEMBER_ID("중복된 아이디 입니다!"),
	DUPLICATED_MEMBER_EMAIL("중복된 이메일 입니다!"),
	DUPLICATED_MEMBER_NICKNAME("중복된 닉네임 입니다!"),
	PASSWORD_MISMATCH("비밀 번호가 일치하지 않습니다"),
	MEMBER_NOT_FOUND("등록 되지 않은 아이디 입니다"),

	EXPIRED_ACCESS_TOKEN("만료된 토큰 입니다"),
	UNSUPPORTED_TOKEN("지원하지 않는 토큰 입니다"),
	AUTHORIZATION_HEADER_NOT_EMPTY("인증 헤더 값이 비어있습니다"),


	;
	private final String description;
}
