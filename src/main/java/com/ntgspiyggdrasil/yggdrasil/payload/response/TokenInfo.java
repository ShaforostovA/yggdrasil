package com.ntgspiyggdrasil.yggdrasil.payload.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@NoArgsConstructor
public class TokenInfo {
    public TokenInfo(boolean isValid, UserShortModel user) {
        this.isValid = isValid;
        this.authorities = user.getAuthorities();
    }
    private boolean isValid;
    private Collection<? extends GrantedAuthority> authorities;;
}
