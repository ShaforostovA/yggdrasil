package com.ntgspiyggdrasil.yggdrasil.models;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum ERole {
    ROLE_USER,
    ROLE_MODERATOR,
    ROLE_ADMIN,
    ROLE_SUPER_ADMIN;
//    USER(Set.of(Permission.DEVELOPER_READ)),
//    MODERATOR(Set.of(Permission.DEVELOPER_READ)),
//    ADMIN(Set.of(Permission.DEVELOPER_READ)),
//    SUPER_ADMIN(Set.of(Permission.DEVELOPER_READ));
    
//    private final Set<Permission> permissions;
//
//    ERole(Set<Permission> permissions) {
//        this.permissions = permissions;
//    }
//
//    public Set<Permission> getPermissions() {
//        return permissions;
//    }
//
//    public Set<SimpleGrantedAuthority> getAuthorities() {
//        return getPermissions().stream().map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
//                .collect(Collectors.toSet());
//    }
}
