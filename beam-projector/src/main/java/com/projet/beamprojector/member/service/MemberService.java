package com.projet.beamprojector.member.service;

import com.projet.beamprojector.domain.entity.Member;
import com.projet.beamprojector.dto.member.MemberDto.LoginRequest;
import com.projet.beamprojector.dto.member.MemberDto.MemberResponse;
import com.projet.beamprojector.dto.member.MemberDto.ModifyMemberRequest;
import com.projet.beamprojector.dto.member.MemberDto.SignupRequest;
import com.projet.beamprojector.member.exception.MemberException;
import com.projet.beamprojector.member.exception.type.MemberErrorCode;
import com.projet.beamprojector.member.repository.MemberRepository;
import com.projet.beamprojector.util.JwtUtil;
import java.util.Objects;
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

		isValidatedEmail(request.getEmail());
		isValidatedNickName(request.getNickName());
		if (memberRepository.existsByMemberId(request.getMemberId())) {
			throw new MemberException(
				MemberErrorCode.DUPLICATED_MEMBER_ID);
		}
		String encodedPassword = passwordEncoder.encode(request.getPassword());
		Member member = Member.toEntity(request, encodedPassword);

		memberRepository.save(member);

		return member;
	}

	public String login(LoginRequest request) {

		Member member = isValidatedMemberID(request.getMemberId());
		if (!passwordEncoder.matches(request.getPassword(),
			member.getPassword())) {
			throw new MemberException(MemberErrorCode.PASSWORD_MISMATCH);
		}

		log.info("login member Name : {}", member.getName());

		return JwtUtil.createToken(
			member, key, ACCESS_TOKEN_EXPIRE_TIME);
	}

	public MemberResponse getMember(String memberId) {

		Member member = isValidatedMemberID(memberId);
		return MemberResponse.toMemberResponse(member);
	}

	public MemberResponse modifyMember(

		ModifyMemberRequest request, String memberId) {
		Member member = isValidatedMemberID(memberId);

		String newEmail = request.getEmail();
		if (isEmailModified(member, newEmail)
			&& isValidatedEmail(request.getEmail())) {
			member.setEmail(request.getEmail());
		}

		String newNickName = request.getNickName();
		if (isNickNameModified(member, newNickName)
			&& isValidatedNickName(request.getEmail())) {
			member.setEmail(request.getEmail());
		}

		String newName = request.getName();
		if (isNameModified(member, newName)) {
			member.setName(request.getName());
		}

		String newProfileImageUrl = request.getEmail();
		if (isProfileImageUrlModified(member, newProfileImageUrl)) {
			member.setProfileImageUrl(request.getProfileImageUrl());
		}

		memberRepository.save(member);

		return MemberResponse.toMemberResponse(member);
	}

	public void deleteMember(String memberId) {
		Member member = isValidatedMemberID(memberId);
		memberRepository.delete(member);
	}

	private Member isValidatedMemberID(String memberId) {

		Member member = memberRepository.findByMemberId(memberId);
		if (member == null) {
			throw new MemberException(MemberErrorCode.MEMBER_NOT_FOUND);
		}
		return member;
	}

	private boolean isValidatedEmail(String email) {
		if (memberRepository.existsByEmail(email)) {
			throw new MemberException(
				MemberErrorCode.DUPLICATED_MEMBER_EMAIL);
		}
		return true;
	}

	private boolean isValidatedNickName(String nickName) {
		if (memberRepository.existsByNickName(nickName)) {
			throw new MemberException(
				MemberErrorCode.DUPLICATED_MEMBER_NICKNAME);
		}
		return true;
	}

	private boolean isEmailModified(Member member, String request) {
		return !Objects.equals(member.getEmail(), request);
	}

	private boolean isNickNameModified(Member member, String request) {
		return !Objects.equals(member.getNickName(), request);
	}

	private boolean isNameModified(Member member, String request) {
		return !Objects.equals(member.getName(), request);
	}

	private boolean isProfileImageUrlModified(Member member, String request) {
		return !Objects.equals(member.getName(), request);
	}

}
