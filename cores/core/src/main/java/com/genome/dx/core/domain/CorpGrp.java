package com.genome.dx.core.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.genome.dx.core.domain.base.CorpGrpBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false) @Entity
@Table(name="T_CORP_GRP")
public class CorpGrp extends CorpGrpBase {
    @OneToMany
    @JoinColumn(name="CORP_GRP_SEQ" , referencedColumnName  = "CORP_GRP_SEQ")
//    @JsonView({JsonViewFrontEnd.class})
    private List<CorpGrpAuth> corpGrpAuths;
}
