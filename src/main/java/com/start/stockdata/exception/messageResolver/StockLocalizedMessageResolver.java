package com.start.stockdata.exception.messageResolver;

import com.start.stockdata.config.userDetails.StockUserInfo;
import com.start.stockdata.exception.exception.StockException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Locale;

import static org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME;

@Slf4j
@Component
@RequiredArgsConstructor
public class StockLocalizedMessageResolver implements LocalizedMessageResolver<StockException> {
    private static final Locale defaultLocale = new Locale("ru", "RU");
    private final MessageSource messageSource;

    public String getLocalizedMessage(StockException ex, HttpServletRequest request) {
        try {
            return messageSource.getMessage(ex.getCode(), ex.getArguments(), resolveUserLocale(request));
        } catch (final NoSuchMessageException e) {
            log.debug(e.getLocalizedMessage());
        }
        return ex.getLocalizedMessage();
    }

    private Locale resolveUserLocale(HttpServletRequest request) {
        Locale locale = (Locale) WebUtils.getSessionAttribute(request, LOCALE_SESSION_ATTRIBUTE_NAME);
        if (locale == null) {
            final Principal principal = request.getUserPrincipal();
            if (principal != null) {
                //locale = getUserInfo(principal).getLocale();
            }
        }
        if (locale == null) {
            locale = request.getLocale();
        }
        if (locale == null) {
            locale = defaultLocale;
        }
        return locale;
    }

    private StockUserInfo getUserInfo(Principal principal) {
        final Authentication token = (Authentication) principal;
        return (StockUserInfo) token.getPrincipal();

    }
}
