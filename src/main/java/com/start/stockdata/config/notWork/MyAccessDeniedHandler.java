package com.start.stockdata.config.notWork;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.NoArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class MyAccessDeniedHandler implements AccessDeniedHandler  {


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) {

        System.out.println("h11111111111111111111111");
        throw  new StockAccessDeniedException(request,accessDeniedException);


    }

}