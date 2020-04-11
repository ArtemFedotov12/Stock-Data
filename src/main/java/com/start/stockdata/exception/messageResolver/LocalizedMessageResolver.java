package com.start.stockdata.exception.messageResolver;

import javax.servlet.http.HttpServletRequest;

public interface LocalizedMessageResolver<T extends RuntimeException> {

     String getLocalizedMessage(T exception, HttpServletRequest request);

}
