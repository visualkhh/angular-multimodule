package com.genome.dx.core.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.genome.dx.core.domain.base.AdmBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false) @Entity
@Table(name="T_ADM")
public class Adm extends AdmBase implements Serializable{
	@ManyToOne
	@JoinColumn(name = "CORP_GRP_SEQ", referencedColumnName = "CORP_GRP_SEQ", insertable = false, updatable = false)
//	@JsonView({JsonViewFrontEnd.class})
	private CorpGrp corpGrp;

//	@OneToMany
//	@JoinColumn(name="ADM_SEQ" , referencedColumnName  = "ADM_SEQ",   insertable = false, updatable = false)
//	private List<AdmAuth> admAuths;

}
