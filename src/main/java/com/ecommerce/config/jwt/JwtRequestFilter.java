package com.ecommerce.config.jwt;

import com.ecommerce.dto.user.UserDetailDto;
import com.ecommerce.logic.UserLogic;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;

    private final UserLogic userLogic;

    public JwtRequestFilter (JwtTokenUtil jwtTokenUtil, UserLogic userLogic) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userLogic = userLogic;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final String requestTokenHeader =  request.getHeader("Authorization");

        String userCode = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                userCode = jwtTokenUtil.getUserNameFromToken(jwtToken);
            } catch (IllegalAccessError e) {
                log.error("Unable to get JWT Token", e);
                throw e;
            } catch (ExpiredJwtException e) {
                log.info("JWT Token has expired: " + requestTokenHeader);
            }
        }

        if (userCode != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetailDto user = userLogic.findByUserName(userCode);
            log.info("doFilterInternal user name : " + userCode);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        chain.doFilter(request, response);
    }
}
