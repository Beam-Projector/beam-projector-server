package com.projet.beamprojector.member.exception.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberErrorCode {
	DUPLICATED_MEMBER_ID("중복된 아이디 입니다!"),
	DUPLICATED_MEMBER_EMAIL("중복된 이메일 입니다!"),
	DUPLICATED_MEMBER_NICKNAME("중복된 닉네임 입니다!"),

	;
	private final String description;
}
