package com.genome.dx.core.domain.base;

import com.genome.dx.core.code.BrdCateCd;
import com.genome.dx.core.code.UseCd;
import com.genome.dx.core.model.ModelBase;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
@ApiModel
public class BrdBase extends ModelBase implements Serializable {

    @Id
    @Column(name = "BRD_SEQ")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long brdSeq;

    @Column(name = "CATE_CD")
    @Enumerated(EnumType.STRING)
    private BrdCateCd cateCd;

    @Column(name = "TITL")
    private String titl;

    @Column(name = "CONT")
    private String cont;

    @Column(name = "USE_CD")
    @Enumerated(EnumType.STRING)
    private UseCd useCd;

    @Column(name = "REG_DT")
    private ZonedDateTime regDt;

    @Column(name = "ADM_SEQ")
    private Integer admSeq;

    @PrePersist
    protected void onCreate() {
        if (regDt == null) {
            regDt = ZonedDateTime.now();
        }
    }

}
