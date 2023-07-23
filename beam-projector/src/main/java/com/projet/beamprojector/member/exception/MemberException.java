package com.projet.beamprojector.member.exception;

import com.projet.beamprojector.member.exception.type.MemberErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class MemberException extends RuntimeException {

	private MemberErrorCode errorCode;
	private String errorMessage;

	public MemberException(MemberErrorCode errorCode) {
		this.errorCode = errorCode;
		this.errorMessage = errorCode.getDescription();
	}
}
