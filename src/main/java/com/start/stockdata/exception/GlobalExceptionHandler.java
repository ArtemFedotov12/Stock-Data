package com.start.stockdata.exception;

import com.start.stockdata.exception.exception.UndefinedException;
import com.start.stockdata.exception.handler.ApplicationExceptionHandler;
import com.start.stockdata.exception.exception.StockException;
import com.start.stockdata.exception.exception.UserByIdNotFoundException;
import com.start.stockdata.exception.wrapper.StockExceptionWrapper;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final ApplicationExceptionHandler<StockExceptionWrapper, StockException> applicationExceptionHandler;

    public GlobalExceptionHandler(ApplicationExceptionHandler<StockExceptionWrapper, StockException> applicationExceptionHandler) {
        this.applicationExceptionHandler = applicationExceptionHandler;
    }

    // error handle for @Valid
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        body.put("status", status.value());

        //Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);

    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<StockExceptionWrapper> handleError(Throwable ex, HttpServletRequest request) {
        return applicationExceptionHandler.handleException(new UndefinedException(ex),request);
    }

    @ExceptionHandler(UserByIdNotFoundException.class)
    public ResponseEntity<StockExceptionWrapper> handleError(UserByIdNotFoundException ex, HttpServletRequest request) {
        return applicationExceptionHandler.handleException(ex,request);
    }


}
