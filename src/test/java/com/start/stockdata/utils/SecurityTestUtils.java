package com.start.stockdata.utils;

import com.start.stockdata.config.userDetails.StockUserInfo;
import lombok.experimental.UtilityClass;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.start.stockdata.utils.TestConstants.*;

@UtilityClass
public class SecurityTestUtils {

    public static RequestPostProcessor initSecurityContext(UserDetails userDetails) {
        SecurityContextHolder
                .getContext()
                .setAuthentication(new UsernamePasswordAuthenticationToken(
                        userDetails,
                        "null",
                        userDetails.getAuthorities()
                ));
        return SecurityMockMvcRequestPostProcessors
                .authentication(new UsernamePasswordAuthenticationToken(
                        userDetails,
                        "",
                        userDetails.getAuthorities()
                ));
    }

    public static Collection<GrantedAuthority> getAdminAuthorities() {

        List<String> authorities = Arrays.asList("ROLE_DEFAULT", "ROLE_ADMIN");

        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public static Collection<GrantedAuthority> getUserAuthorities() {

        List<String> authorities = Collections.singletonList("ROLE_DEFAULT");

        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public static StockUserInfo getAdmin() {
        return new StockUserInfo(1L, EMAIL_ADMIN, getAdminAuthorities());
    }

    public static StockUserInfo getUser() {
        return new StockUserInfo(2L, EMAIL_USER, getUserAuthorities());
    }

}
