package com.projet.beamprojector.dto.member;

import com.projet.beamprojector.domain.entity.Member;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class MemberDto {

	@ToString
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class SignupRequest {

		@NotBlank(message = "아이디를 입력해 주세요.")
		@Schema(description = "아이디")
		private String memberId;

		@Email(message = "이메일 형식으로 입력해 주세요.")
		@NotBlank(message = "이메일 값을 입력해 주세요.")
		@Schema(description = "이메일")
		private String email;

		@Pattern(regexp = "^(?=.*[a-zA-Z])[0-9a-zA-Z]{4,60}$",
			message = "아이디: 알파벳, 숫자를 조합한 4자리~60자리 문자열을 입력해 주세요.")
		@NotBlank(message = "비밀번호 값을 입력해 주세요.")
		@Schema(description = "비밀번호")
		private String password;

		@NotBlank(message = "이름을 입력해 주세요.")
		@Schema(description = "이름")
		private String name;

		@NotBlank(message = "닉네임을 입력해 주세요.")
		@Schema(description = "닉네임")
		private String nickName;

		@Schema(description = "프로필 URL")
		private String profileImageUrl;
	}

	@ToString
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class LoginRequest {

		@NotBlank(message = "아이디를 입력해 주세요.")
		@Schema(description = "아이디")
		private String memberId;

		@Pattern(regexp = "^(?=.*[a-zA-Z])[0-9a-zA-Z]{4,60}$",
			message = "아이디: 알파벳, 숫자를 조합한 4자리~60자리 문자열을 입력해 주세요.")
		@NotBlank(message = "비밀번호 값을 입력해 주세요.")
		@Schema(description = "비밀번호")
		private String password;
	}

	@ToString
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ModifyMemberRequest {

		@Email(message = "이메일 형식으로 입력해 주세요.")
		@NotBlank(message = "이메일 값을 입력해 주세요.")
		@Schema(description = "이메일")
		private String email;

		@NotBlank(message = "이름을 입력해 주세요.")
		@Schema(description = "이름")
		private String name;

		@NotBlank(message = "닉네임을 입력해 주세요.")
		@Schema(description = "닉네임")
		private String nickName;

		@Schema(description = "프로필 URL")
		private String profileImageUrl;
	}

	@ToString
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class MemberResponse {

		private String memberId;
		private String email;
		private String name;
		private String nickName;
		private String profileImageUrl;

		public static MemberResponse toMemberResponse(Member member) {
			return MemberResponse.builder()
				.memberId(member.getMemberId())
				.email(member.getEmail())
				.name(member.getName())
				.nickName(member.getNickName())
				.profileImageUrl(member.getProfileImageUrl())
				.build();
		}
	}


}
