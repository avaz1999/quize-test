package com.example.quiztest.project.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Locale;

public class HeaderLocaleChangeInterceptor implements HandlerInterceptor {
    private final String headerName;
    private final Log logger = LogFactory.getLog(getClass());

    public HeaderLocaleChangeInterceptor(String headerName) {
        this.headerName = headerName;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String newLocale = request.getHeader(headerName);
        if (newLocale != null) {
            try {
                LocaleContextHolder.setLocale(new Locale(newLocale));
            } catch (IllegalArgumentException ex) {
                logger.info("Ignoring invalid locale value [" + newLocale + "]: " + ex.getMessage());
            }
        } else {
            LocaleContextHolder.setLocale(new Locale("uz"));
        }
        return true;
    }
}
