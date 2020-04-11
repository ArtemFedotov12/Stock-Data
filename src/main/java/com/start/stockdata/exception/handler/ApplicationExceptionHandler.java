package com.start.stockdata.exception.handler;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface ApplicationExceptionHandler<T, R extends Throwable> {

    ResponseEntity<T> handleException(R exception, HttpServletRequest request);

}
