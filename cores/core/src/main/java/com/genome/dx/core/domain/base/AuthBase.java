package com.genome.dx.core.domain.base;

import com.genome.dx.core.model.ModelBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
public class AuthBase extends ModelBase implements Serializable {

    @Id
    @Column(name="AUTH_ID")
    protected String authId;

    @Column(name="AUTH_NM")
    protected String authNm;

    @Column(name="XPLN")
    protected String xpln;
}
