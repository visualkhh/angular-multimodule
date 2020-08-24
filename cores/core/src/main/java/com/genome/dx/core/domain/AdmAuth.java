package com.genome.dx.core.domain;

import com.genome.dx.core.domain.base.AdmAuthBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@EqualsAndHashCode(callSuper=false) @Entity
@Table(name="T_ADM_AUTH")
public class AdmAuth extends AdmAuthBase {
	@ManyToOne
	@JoinColumn(name="AUTH_ID" , referencedColumnName  = "AUTH_ID",   insertable = false, updatable = false)
	private Auth auth;
}
