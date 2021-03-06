package com.genome.dx.core.domain;

import com.genome.dx.core.domain.base.BrdBase;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
//@DynamicUpdate
//@DynamicInsert
@Table(name = "T_BRD")
public class Brd extends BrdBase {
}
