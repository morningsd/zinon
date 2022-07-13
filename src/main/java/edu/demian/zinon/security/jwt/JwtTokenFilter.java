package edu.demian.zinon.security.jwt;

import edu.demian.zinon.security.exception.JwtAuthenticationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.header}")
    private String authorizationHeader;
    private static final String BEARER = "Bearer ";

    private final JwtTokenProvider tokenProvider;

    public JwtTokenFilter(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            String jwt = parseJwt(request);
            if (jwt != null && tokenProvider.isValid(jwt)) {
                UsernamePasswordAuthenticationToken authentication = tokenProvider.getAuthentication(jwt);
                if (authentication != null) {
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (JwtAuthenticationException e) {
            SecurityContextHolder.clearContext();
            throw new JwtAuthenticationException("JWT token is expired or invalid");
        }

        chain.doFilter(request, response);
    }


    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader(authorizationHeader);
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(BEARER)) {
            return headerAuth.substring(BEARER.length());
        }
        return null;
    }
}
