package edu.demian.zinon.security.jwt;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class JwtConfigurer extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtTokenFilter tokenFilter;

    public JwtConfigurer(JwtTokenFilter tokenFilter) {
        this.tokenFilter = tokenFilter;
    }


    @Override
    public void configure(HttpSecurity http) {
        http
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}