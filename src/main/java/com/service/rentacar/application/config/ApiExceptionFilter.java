package com.service.rentacar.application.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.rentacar.application.lang.AcceptedLanguageLocaleMapper;
import com.service.rentacar.domain.ApiException;
import com.service.rentacar.i18n.MessageService;
import com.service.rentacar.rest.RestErrorResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@AllArgsConstructor
@Order(1)
@Component
public class ApiExceptionFilter extends OncePerRequestFilter
{
    private final ObjectMapper objectMapper;
    private final AcceptedLanguageLocaleMapper acceptedLanguageLocaleMapper;
    private final MessageService messageService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException {
        try
        {
            filterChain.doFilter(request, response);
        }
        catch (Exception exception)
        {
            if (exception.getClass().isAnnotationPresent(ApiException.class))
            {
                ApiException apiException = exception.getClass().getAnnotation(ApiException.class);

                List<Locale> locales = acceptedLanguageLocaleMapper.toLocales(getAcceptedLanguageHeader(request));
                RestErrorResponse restErrorResponse = RestErrorResponse.of(apiException.code().name(), messageService.resolveMessage(apiException.messageKey(), locales));
                response.setStatus(apiException.status().value());
                response.getWriter().write(objectMapper.writeValueAsString(restErrorResponse));
            }
            else
            {
                response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                response.getWriter().write(exception.getMessage());
            }
        }
    }

    private String getAcceptedLanguageHeader(HttpServletRequest httpServletRequest)
    {
        return httpServletRequest.getHeader(HttpHeaders.ACCEPT_LANGUAGE);
    }
}
