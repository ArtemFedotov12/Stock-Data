package com.start.stockdata.exception;

import com.start.stockdata.config.notWork.StockAccessDeniedException;
import com.start.stockdata.exception.exception.UndefinedException;
import com.start.stockdata.exception.handler.ApplicationExceptionHandler;
import com.start.stockdata.exception.exception.StockException;
import com.start.stockdata.exception.exception.UserByIdNotFoundException;
import com.start.stockdata.exception.wrapper.StockExceptionWrapper;
import com.start.stockdata.exception.wrapper.ValidationExceptionWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler  {

    private final ApplicationExceptionHandler<StockExceptionWrapper, StockException> applicationExceptionHandler;

    public GlobalExceptionHandler(ApplicationExceptionHandler<StockExceptionWrapper, StockException> applicationExceptionHandler) {
        this.applicationExceptionHandler = applicationExceptionHandler;
    }

    /*  error handle for @Valid
     If the bean validation is failed, it will trigger a MethodArgumentNotValidException.
     By default, Spring will send back an HTTP status 400 Bad Request, but no error detail.*/
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionWrapper> handleValidationErrors(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, List<String>> errors = getErrors(ex);
        return new ResponseEntity<>(ValidationExceptionWrapper.wrap("Val",422,errors), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(StockAccessDeniedException.class)
    public ResponseEntity<ValidationExceptionWrapper> handleAuthenticationException(StockAccessDeniedException  ex) {
        return new ResponseEntity<>(ValidationExceptionWrapper.wrap("Val11",403,null), HttpStatus.FORBIDDEN);
    }



    @ExceptionHandler(Throwable.class)
    public ResponseEntity<StockExceptionWrapper> handleError(Throwable ex, HttpServletRequest request) {
        return applicationExceptionHandler.handleException(new UndefinedException(ex), request);
    }

    @ExceptionHandler(StockException.class)
    public ResponseEntity<StockExceptionWrapper> handleError(StockException ex, HttpServletRequest request) {
        return applicationExceptionHandler.handleException(ex, request);
    }

    @ExceptionHandler(UserByIdNotFoundException.class)
    public ResponseEntity<StockExceptionWrapper> handleError(UserByIdNotFoundException ex, HttpServletRequest request) {
        return applicationExceptionHandler.handleException(ex, request);
    }


    private Map<String, List<String>> getErrors(MethodArgumentNotValidException ex) {
        Map<String, List<String>> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            if (!errors.containsKey(fieldName)) {
                errors.put(fieldName, new ArrayList<>(Arrays.asList(errorMessage)));
            } else {
                errors.get(fieldName).add(errorMessage);
            }

        });
        return errors;
    }

}
