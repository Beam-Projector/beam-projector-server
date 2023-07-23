package com.projet.beamprojector.member.service;

import com.projet.beamprojector.domain.entity.Member;
import com.projet.beamprojector.dto.member.MemberDto.SignupRequest;
import com.projet.beamprojector.member.exception.MemberException;
import com.projet.beamprojector.member.exception.type.MemberErrorCode;
import com.projet.beamprojector.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

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
}
