package com.ntgspiyggdrasil.yggdrasil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ntgspiyggdrasil.yggdrasil.models.DocumentPermission;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentPermissionsRepository extends JpaRepository<DocumentPermission, Long> {
}
