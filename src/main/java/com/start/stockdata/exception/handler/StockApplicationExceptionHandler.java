package com.start.stockdata.exception.handler;

import com.start.stockdata.exception.exception.StockException;
import com.start.stockdata.exception.wrapper.StockExceptionWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Component
public class StockApplicationExceptionHandler implements ApplicationExceptionHandler<StockExceptionWrapper, StockException> {

   /* final String localizedMessage = localizedMessageResolver.getLocalizedMessage(ex, request);
        log.warn(localizedMessage, ex);
        return new ResponseEntity<>(StockExceptionWrapper.wrap(localizedMessage, ex.getCode()), ex.getStatus());*/
    @Override
    public ResponseEntity<StockExceptionWrapper> handleException(StockException ex, HttpServletRequest request) {
        log.warn(ex.getMessage(),ex);
        return new ResponseEntity<>(StockExceptionWrapper.wrap(ex.getMessage(), ex.getCode()), ex.getStatus());
    }

}
