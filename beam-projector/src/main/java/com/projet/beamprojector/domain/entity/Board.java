package com.projet.beamprojector.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "board", schema = "beam_projector")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_num", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_num", referencedColumnName = "member_num", nullable = false)
    private Member member;

    @Size(max = 45)
    @NotNull
    @Column(name = "title", nullable = false, length = 45)
    private String title;

    @NotNull
    @Lob
    @Column(name = "content", nullable = false)
    private String content;

    @NotNull
    @Column(name = "create_at", nullable = false)
    private LocalDate createAt;

    @NotNull
    @Column(name = "modified_at", nullable = false)
    private LocalDate modifiedAt;

    @Size(max = 45)
    @NotNull
    @Column(name = "disclosure", nullable = false, length = 45)
    private String disclosure;

    @NotNull
    @Column(name = "category_name", nullable = false)
    private Long categoryName;

    @OneToMany(mappedBy = "board",cascade = CascadeType.ALL)
    private Set<Heart> board = new HashSet<>();

}