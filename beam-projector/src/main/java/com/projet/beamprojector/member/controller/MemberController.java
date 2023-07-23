package com.projet.beamprojector.member.controller;

import com.projet.beamprojector.domain.entity.Member;
import com.projet.beamprojector.dto.member.MemberDto;
import com.projet.beamprojector.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/members")
@RestController
public class MemberController {

	private final MemberService memberService;

	@Operation(summary = "회원 가입 신청", description = "회원 가입 정보를 받아 회원을 등록합니다.")
	@PostMapping("/signup")
	public ResponseEntity<?> signup(
		@Validated @RequestBody MemberDto.SignupRequest request) {
		log.info("signup request => {}", request);
		Member member = memberService.registerMember(request);
		return ResponseEntity.ok(member.getMemberId()
			+ "님의 회원가입이 완료 되었습니다! 로그인 후 서비스를 사용하실 수 있습니다.");
	}
}
