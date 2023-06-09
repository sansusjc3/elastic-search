package com.ssafy.mention.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.mention.exception.auth.AuthRuntimeException;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenFilter extends OncePerRequestFilter {
    private JwtTokenProvider jwtTokenProvider;

    public JwtTokenFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(request);
        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication auth = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            filterChain.doFilter(request, response);
        } catch (AuthRuntimeException e) {
            SecurityContextHolder.clearContext();
            response.setStatus(e.getErrorEnum().getHttpStatus().value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            try (OutputStream os = response.getOutputStream()) {
                ObjectMapper objectMapper = new ObjectMapper();

                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("status", e.getErrorEnum().getHttpStatus().value());
                errorResponse.put("message", e.getMessage());

                objectMapper.writeValue(os, errorResponse);
                os.flush();
            }
        } catch (Exception e) {
            filterChain.doFilter(request, response);
        }
    }
}