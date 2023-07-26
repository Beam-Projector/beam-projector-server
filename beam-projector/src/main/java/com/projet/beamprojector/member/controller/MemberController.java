package com.projet.beamprojector.member.controller;

import com.projet.beamprojector.config.Security.TokenMemberId;
import com.projet.beamprojector.dto.member.MemberDto;
import com.projet.beamprojector.dto.member.MemberDto.MemberResponse;
import com.projet.beamprojector.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity<MemberDto.MemberResponse> getMember(
		@TokenMemberId String memberId) {
		log.info("회원 정보 조회 memberId => {}", memberId);
		return ResponseEntity.ok(memberService.getMember(memberId));
	}

	@Operation(summary = "회원 정보 수정", description = "JWT를 받고 회원 정보를 수정하고 수정된 회원 정보를 반환 합니다.")
	@PutMapping("/")
	public ResponseEntity<MemberResponse> modifyMember(
		@TokenMemberId String memberId,
		@Validated @RequestBody MemberDto.ModifyMemberRequest request) {
		log.info("회원 정보 수정 memberId => {}", memberId);

		return ResponseEntity.ok(memberService.modifyMember(request, memberId));
	}


}
