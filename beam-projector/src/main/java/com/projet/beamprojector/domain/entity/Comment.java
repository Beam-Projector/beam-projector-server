package com.projet.beamprojector.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "comment", schema = "beam_projector")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_num", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "member_num", nullable = false)
    private Long memberNum;

    @NotNull
    @Column(name = "board_num", nullable = false)
    private Long boardNum;

    @Size(max = 45)
    @NotNull
    @Column(name = "comments", nullable = false, length = 45)
    private String comments;

    @NotNull
    @Column(name = "create_at", nullable = false)
    @JsonFormat(pattern = "yyy-MM-dd HH:mm:SS")
    private LocalDate createAt;

    @Column(name = "modified_at")
    @JsonFormat(pattern = "yyy-MM-dd HH:mm:SS")
    private LocalDate modifiedAt;

}