package com.projet.beamprojector.member.service;

import com.projet.beamprojector.domain.entity.Member;
import com.projet.beamprojector.dto.member.MemberDto.LoginRequest;
import com.projet.beamprojector.dto.member.MemberDto.SignupRequest;
import com.projet.beamprojector.member.exception.MemberException;
import com.projet.beamprojector.member.exception.type.MemberErrorCode;
import com.projet.beamprojector.member.repository.MemberRepository;
import com.projet.beamprojector.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	@Value("${jwt.token.secret}")
	private String key;
	private static final long ACCESS_TOKEN_EXPIRE_TIME = 30 * 60 * 1000L;

	public Member registerMember(SignupRequest request) {
		if (memberRepository.existsByEmail(request.getEmail())) {
			throw new MemberException(
				MemberErrorCode.DUPLICATED_MEMBER_EMAIL);
		}
		if (memberRepository.existsByNickName(request.getNickName())) {
			throw new MemberException(
				MemberErrorCode.DUPLICATED_MEMBER_NICKNAME);
		}
		if (memberRepository.existsByMemberId(request.getMemberId())) {
			throw new MemberException(
				MemberErrorCode.DUPLICATED_MEMBER_ID);
		}
		String encodedPassword = passwordEncoder.encode(request.getPassword());
		Member member = Member.from(request, encodedPassword);

		memberRepository.save(member);

		return member;
	}

	public String login(LoginRequest request) {

		Member member = memberRepository.findByMemberId(request.getMemberId());
		if (member == null) {
			throw new MemberException(MemberErrorCode.MEMBER_NOT_FOUND);
		}
		if (!passwordEncoder.matches(request.getPassword(),
			member.getPassword())) {
			throw new MemberException(MemberErrorCode.PASSWORD_MISMATCH);
		}

		log.info("login member Name : {}", member.getName());

		return JwtUtil.createToken(
			member.getMemberId(), key, ACCESS_TOKEN_EXPIRE_TIME);
	}
}
