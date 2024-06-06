package com.nocountry.docspotback.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Primary
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CORS extends CorsFilter  {

    @Autowired
    public CORS(@Qualifier("corsConfigurationSource") CorsConfigurationSource configSource) {
        super();
    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        List<String> allowedOrigins = new ArrayList<>();
        allowedOrigins.add("http://localhost:4200");
        allowedOrigins.add("https://docspotback.onrender.com");
        allowedOrigins.add("http://docspotback.onrender.com");

        response.setHeader("Access-Control-Allow-Origin", String.join(", ", allowedOrigins));
        response.setHeader("Access-Control-Allow-Methods", "DELETE, GET, OPTIONS, PATCH, POST, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN");
        response.setHeader("Access-Control-Allow-Credentials", "true");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        }

        chain.doFilter(req, res); // Llamar siempre a la cadena de filtros
    }

    @Override
    public void destroy() {

    }

}
