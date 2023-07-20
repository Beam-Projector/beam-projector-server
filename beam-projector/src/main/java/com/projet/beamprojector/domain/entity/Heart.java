package com.projet.beamprojector.domain.entity;

import com.projet.beamprojector.domain.embeddedid.HeartId;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "heart")
public class Heart {

    @EmbeddedId
    private HeartId heartId;

    // FetchType.LAZY : 지연로딩
    // MapsId : 임베디드아이디 클래스에있는 멤버변수 가져옴
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("memberNum")
    @JoinColumn(name = "member_num")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("boardNum")
    @JoinColumn(name = "board_num")
    private Board board;

}
