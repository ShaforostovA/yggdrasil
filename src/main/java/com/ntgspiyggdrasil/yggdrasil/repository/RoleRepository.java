package com.ntgspiyggdrasil.yggdrasil.repository;

import com.ntgspiyggdrasil.yggdrasil.models.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ntgspiyggdrasil.yggdrasil.models.Role;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
