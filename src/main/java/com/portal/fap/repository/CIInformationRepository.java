package com.portal.fap.repository;

import com.portal.fap.entity.CIInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CIInformationRepository extends JpaRepository<CIInformation, Long> {
    public boolean existsByIdCard(String idCard);
}
