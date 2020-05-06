package com.start.stockdata.utils;

import lombok.experimental.UtilityClass;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class SecurityUtils {

    public static void initSecurityContext(UserDetails userDetails, Collection<GrantedAuthority> authorities) {
        SecurityContextHolder
                .getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, "null", authorities));
    }

    public Collection<GrantedAuthority> getAdminAuthorities() {

        List<String> authorities = Arrays.asList("ROLE_DEFAULT", "ROLE_ADMIN");

        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public Collection<GrantedAuthority> getUserAuthorities() {

        List<String> authorities = Collections.singletonList("ROLE_DEFAULT");

        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
