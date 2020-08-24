package com.genome.dx.wcore.service;

import com.genome.dx.core.domain.Adm;
import com.genome.dx.wcore.repository.security.AdmRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AdminService {

    @Autowired
    private AdmRepository admRepository;

    //	@Transactional
//	public Integer pulseLginFailCnt(Integer admSeq){
//		return admRepository.pulseLginFailCnt(admSeq);
//	}
    public Adm findByAdmLginId(String admLginId) {
        return admRepository.findByAdmLginId(admLginId);
    }

    public Adm save(Adm adm) {
        return admRepository.save(adm);
    }

//    public Adm findOne(Integer seq) {
//        return admRepository.findOne(seq);
//    }
//
//    public void delete(Integer seq) {
//        admRepository.delete(seq);
//    }
//
//    public Page<Adm> findAll(String admLginId, String nm, AffiliationCd afftCd, UseCd useCd, Pageable pageable) {
//        return admRepository.findAll(admLginId, nm, afftCd, useCd, pageable);
//    }
//
//    public List<Adm> getAdminInfo() {
//        //사용
//        return admRepository.getAdminInfo(UseCd.USE001);
//    }
//	@Transactional
//	public Integer setLginFailCnt(String lginId){
//		return admRepository.setLginFailCnt(lginId);
//	}

}
