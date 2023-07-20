package com.projet.beamprojector.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "member", schema = "beam_projector")
public class Member {
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

    @Size(max = 45)
    @NotNull
    @Column(name = "password", nullable = false, length = 45)
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

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private Set<Heart> user = new HashSet<>();
}