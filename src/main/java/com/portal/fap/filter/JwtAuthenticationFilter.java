package com.portal.fap.filter;

import com.portal.fap.service.AccountService;
import com.portal.fap.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class JwtAuthenticationFilter implements Filter {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private AccountService accountService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String jwt = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (jwt == null || !jwt.startsWith("Bearer")) {
            log.warn("Authentication token is null or in incorrect format");
            filterChain.doFilter(request, response);
            return;
        }

        String token = jwt.split("Bearer")[1].trim();
        String username = jwtUtils.extractUsername(token);
        UserDetails userDetails = getUserDetailsFromUsername(username);
        if (!jwtUtils.validateToken(token, userDetails)) {
            log.warn("Invalid Authentication token");
            filterChain.doFilter(request, response);
            return;
        }
        log.info("this is username : {}", username);
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("Authentication Passed");
        filterChain.doFilter(request, response);

    }

    private UserDetails getUserDetailsFromUsername(String username) {
        return accountService.loadUserByUsername(username);
    }
}
