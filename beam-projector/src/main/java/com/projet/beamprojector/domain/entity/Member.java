package com.projet.beamprojector.domain.entity;

import com.projet.beamprojector.dto.member.MemberDto;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "member", schema = "beam_projector")
public class Member implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_num", nullable = false)
	private Long id;

	@Size(max = 45)
	@NotNull
	@Column(name = "member_id", nullable = false, length = 45)
	private String memberId;

	@Size(max = 45)
	@NotNull
	@Column(name = "email", nullable = false, length = 45)
	private String email;

	@NotNull
	@Column(name = "password", nullable = false)
	private String password;

	@Size(max = 45)
	@NotNull
	@Column(name = "name", nullable = false, length = 45)
	private String name;

	@Size(max = 45)
	@NotNull
	@Column(name = "nick_name", nullable = false, length = 45)
	private String nickName;

	@Size(max = 45)
	@Column(name = "profile_image_url", length = 45)
	private String profileImageUrl;

	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
	private Set<Heart> member = new HashSet<>();

	public static Member from(MemberDto.SignupRequest request,
		String encodedPassword) {
		Member member = new Member();
		member.memberId = request.getMemberId();
		member.email = request.getEmail();
		member.name = request.getName();
		member.nickName = request.getNickName();
		member.profileImageUrl = request.getProfileImageUrl();
		member.password = encodedPassword;
		return member;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getUsername() {
		return memberId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
}