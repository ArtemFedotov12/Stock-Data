package com.start.stockdata.util;

import com.start.stockdata.config.userDetails.StockUserInfo;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@UtilityClass
public class SecurityContextUtil {

    public static Optional<Long> getUserIdFromSecurityContext() {
        return Optional.ofNullable(
                ((StockUserInfo) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal())
                        .getUserId()
        );
    }

}
