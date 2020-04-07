package com.start.stockdata.config.userDetails;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class StockUserInfo extends User {
    private Long userId;
    private String email;

    public StockUserInfo(Long userId, String email, Collection<? extends GrantedAuthority> authorities) {
        super("default", "default", authorities);
        this.userId = userId;
        this.email = email;
    }
}
