package com.projet.beamprojector.member.repository;

import com.projet.beamprojector.domain.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

	boolean existsByEmail(String email);
	boolean existsByMemberId(String memberId);
	boolean existsByNickName(String nickName);

	Member findByMemberId(String memberId);


}
