package com.synectiks.cms.repository;

import com.synectiks.cms.domain.AcademicHistory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AcademicHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AcademicHistoryRepository extends JpaRepository<AcademicHistory, Long> {

}