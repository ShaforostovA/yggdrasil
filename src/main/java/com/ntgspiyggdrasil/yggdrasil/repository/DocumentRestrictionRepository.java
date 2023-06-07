package com.ntgspiyggdrasil.yggdrasil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ntgspiyggdrasil.yggdrasil.models.DocumentRestriction;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRestrictionRepository extends JpaRepository<DocumentRestriction, Long> {
}
