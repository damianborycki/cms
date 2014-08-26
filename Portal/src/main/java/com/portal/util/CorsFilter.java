package com.portal.util;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

public class CorsFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    	
            response.addHeader("Access-Control-Allow-Origin", request.getScheme() + "://" + request.getServerName());
            response.addHeader("Access-Control-Allow-Credentials", "true");
            
            if (request.getHeader("Access-Control-Request-Method") != null && request.getMethod().equals("OPTIONS")) {
                response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");
                response.addHeader("Access-Control-Allow-Headers", "X-Requested-With, Origin, Content-Type, Accept");
            }
            
            filterChain.doFilter(request, response);
    }

}