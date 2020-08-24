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
public class AdmAuthBase extends ModelBase implements Serializable {
    @Id
    @Column(name = "ADM_AUTH_SEQ")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long admAuthSeq;

    @Column(name = "ADM_SEQ")
    private Long admSeq;

    @Column(name = "AUTH_ID")
    private String authId;
}
