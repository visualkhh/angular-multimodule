package com.genome.dx.wcore.repository.security;

//import com.omnicns.medicine.code.AffiliationCd;
//import com.omnicns.medicine.code.UseCd;
//import com.omnicns.medicine.domain.Adm;
import com.genome.dx.core.domain.Adm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/
public interface AdmRepository extends JpaRepository<Adm, Integer> {

	public Adm findAdmByAdmLginId(String admLginId);
	public Adm findByAdmLginId(String admLginId);


//	@Query(value =
//			" SELECT A FROM Adm as A" +
//					" WHERE " +
//					" COALESCE(A.admLginId,'') like %:admLginId% " +
//					" AND COALESCE(A.admNm,'') like %:admNm% " +
//					" AND (:afftCd is null or A.afftCd = :afftCd) " +
//					" AND (:useCd is null or A.useCd = :useCd) "
//	)
//    Page<Adm> findAll(
//            @Param("admLginId") String admLginId,
//            @Param("admNm") String admNm,
//            @Param("afftCd") AffiliationCd afftCd,
//            @Param("useCd") UseCd useCd,
//            Pageable pageable);
//
//	@Query(value =
//			" SELECT A FROM Adm as A" +
//			" WHERE (:useCd is null or A.useCd = :useCd) "+
//			" AND (A.email is not null) "
//	)
//	List<Adm> getAdminInfo(
//            @Param("useCd") UseCd useCd
//    );


//	Page findAll(String nm, AffiliationCd afftCd, UseCd useCd);


//	@Modifying
//	@Query(value = "UPDATE T_ADM SET LGIN_FAIL_CNT = LGIN_FAIL_CNT+1 WHERE ADM_SEQ = :admSeq", nativeQuery =true)
////	@Query(nativeQuery =true)
//	Integer pulseLginFailCnt(@Param("admSeq")  Integer admSeq);
//
//	@Modifying
//	@Query(value = "UPDATE T_ADM SET LGIN_FAIL_CNT = LGIN_FAIL_CNT+1 WHERE LGIN_ID = :lginId", nativeQuery =true)
//	Integer setLginFailCnt(@Param("lginId") String lginId);
}
