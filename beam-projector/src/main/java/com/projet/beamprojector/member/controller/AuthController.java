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
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AuthController {

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

	@Operation(summary = "로그인", description = "아이디와 비밀번호를 입력받아 JWT를 반환 합니다.")
	@PostMapping("/login")
	public ResponseEntity<?> login(
		@Validated @RequestBody MemberDto.LoginRequest request) {
		log.info("login request => {}", request.getMemberId());
		String token = memberService.login(request);
		return ResponseEntity.ok(token);
	}

}
