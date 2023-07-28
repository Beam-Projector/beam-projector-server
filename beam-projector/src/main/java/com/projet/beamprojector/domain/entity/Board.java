package com.projet.beamprojector.domain.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.projet.beamprojector.dto.board.BoardDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createAt;


    @Column(name = "modified_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedAt;

    @Size(max = 45)
    @NotNull
    @Column(name = "disclosure", nullable = false, length = 45)
    private String disclosure;

    @NotNull
    @Column(name = "category_name", nullable = false)
    private Long categoryName;

    @Column(name = "board_hits", columnDefinition = "BIGINT DEFAULT 0")
    private Long boardHits;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private Set<Heart> board = new HashSet<>();

    public static Board toSaveEntity(BoardDto.CreateBoardRequest request, Member member) {
        Board boardEntity = new Board();

        boardEntity.setContent(request.getContent());
        boardEntity.setMember(member);
        boardEntity.setDisclosure(request.getDisclosure());
        boardEntity.setCategoryName(request.getCategoryName());
        boardEntity.setTitle(request.getTitle());
        boardEntity.setBoardHits(0L);
        boardEntity.setCreateAt(LocalDateTime.now());
        boardEntity.setModifiedAt(LocalDateTime.now());

        return boardEntity;
    }

}