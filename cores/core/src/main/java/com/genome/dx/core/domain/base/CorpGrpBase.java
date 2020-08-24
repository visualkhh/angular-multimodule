package com.genome.dx.core.domain.base;

import com.fasterxml.jackson.annotation.JsonView;
import com.genome.dx.core.model.ModelBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(callSuper=false)
public class CorpGrpBase extends ModelBase implements Serializable {
    @Id
    @Column(name = "CORP_GRP_SEQ")
    @GeneratedValue(strategy= GenerationType.AUTO)
//    @JsonView({JsonViewFrontEnd.class})
    private Integer corpGrpSeq;

    @Column(name = "CORP_GRP_NM")
//    @JsonView({JsonViewFrontEnd.class})
    private String corpGrpNm;

    @Column(name ="REG_DT")
//    @JsonView({JsonViewFrontEnd.class})
    private ZonedDateTime regDt;

    @PrePersist
    protected void onCreate() {
        if (regDt == null) { regDt = ZonedDateTime.now(); }
    }
}
