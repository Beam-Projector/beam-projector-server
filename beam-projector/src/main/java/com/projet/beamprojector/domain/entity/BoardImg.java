package com.projet.beamprojector.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "board_img", schema = "beam_projector")
public class BoardImg {
    @Id
    @Column(name = "image_num", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "board_num", nullable = false)
    private Long boardNum;

    @Size(max = 45)
    @Column(name = "board_image_url", length = 45)
    private String boardImageUrl;

}