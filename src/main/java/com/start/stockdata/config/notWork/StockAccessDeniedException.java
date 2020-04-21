package com.start.stockdata.config.notWork;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.access.AccessDeniedException;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Getter
@Setter
public class StockAccessDeniedException extends RuntimeException {

    private static final long serialVersionUID = -7256323249636883144L;

    private final HttpServletRequest request;
    private final AccessDeniedException accessDeniedException;

}
