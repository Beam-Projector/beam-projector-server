package com.projet.beamprojector.member.exception;

import com.projet.beamprojector.member.exception.type.MemberErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberErrorResponse {

	private MemberErrorCode errorCode;
	private String errorMessage;
}
