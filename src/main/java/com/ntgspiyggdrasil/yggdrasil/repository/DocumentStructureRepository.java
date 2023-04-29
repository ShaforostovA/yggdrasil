package com.ntgspiyggdrasil.yggdrasil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ntgspiyggdrasil.yggdrasil.models.DocumentStructure;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentStructureRepository extends JpaRepository<DocumentStructure, Long> {
}
