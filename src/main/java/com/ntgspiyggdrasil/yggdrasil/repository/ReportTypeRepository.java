package com.ntgspiyggdrasil.yggdrasil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ntgspiyggdrasil.yggdrasil.models.ReportType;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportTypeRepository extends JpaRepository<ReportType, Long> {
    Boolean existsByName(String name);
}
