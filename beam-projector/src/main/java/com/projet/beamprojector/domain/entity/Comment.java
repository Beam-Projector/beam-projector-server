package com.projet.beamprojector.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comment", schema = "beam_projector")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_num", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_num", referencedColumnName = "member_num", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_num", referencedColumnName = "board_num", nullable = false)
    private Board board;

    @Size(max = 45)
    @NotNull
    @Column(name = "comments", nullable = false, length = 45)
    private String comments;

    @NotNull
    @Column(name = "create_at", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createAt;

    // 수정시간의 경우 사용자에게 보이지는 않고 DB에만 남아있도록 하는것이 좋을거 같아요
    // 디스코드처럼 수정된 경우 (수정됨) 이라고 보이던지요!
    @Column(name = "modified_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedAt;

}