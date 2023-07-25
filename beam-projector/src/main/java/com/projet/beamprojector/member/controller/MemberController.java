package com.projet.beamprojector.member.controller;

import com.projet.beamprojector.config.Security.TokenMemberId;
import com.projet.beamprojector.dto.member.MemberDto;
import com.projet.beamprojector.dto.member.MemberDto.GetMemberResponse;
import com.projet.beamprojector.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberController {

	private final MemberService memberService;

	@Operation(summary = "회원 정보 조회", description = "JWT를 받고 회원 정보를 반환 합니다.")
	@GetMapping("/")
	public ResponseEntity<MemberDto.GetMemberResponse> getMember(
		@TokenMemberId String memberId) {
		log.info("memberId => {}", memberId);
		return ResponseEntity.ok(memberService.getMember(memberId));
	}


}
