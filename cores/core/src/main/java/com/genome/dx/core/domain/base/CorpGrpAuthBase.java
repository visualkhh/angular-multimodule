package com.genome.dx.core.domain.base;

import com.genome.dx.core.model.ModelBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(callSuper=false)
public class CorpGrpAuthBase extends ModelBase implements Serializable {
    @Id
    @Column(name = "CORP_GRP_AUTH_SEQ")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer corpGrpAuthSeq;

    @Column(name = "CORP_GRP_SEQ")
    private Integer corpGrpSeq;

    @Column(name = "AUTH_ID")
    private String authId;
}
