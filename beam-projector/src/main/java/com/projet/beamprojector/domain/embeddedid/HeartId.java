package com.projet.beamprojector.domain.embeddedid;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Setter
@Getter
@Embeddable
public class HeartId implements Serializable{

    @Column(name = "board_num")
    private Long boardNum;

    @Column(name = "member_num")
    private Long memberNum;


}
