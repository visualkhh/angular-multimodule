package com.genome.dx.core.repository;

import com.genome.dx.core.domain.Brd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrdRepository extends JpaRepository<Brd, Long> {

}
