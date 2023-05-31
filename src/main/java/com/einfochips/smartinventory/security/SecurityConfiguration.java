package com.einfochips.smartinventory.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.einfochips.smartinventory.service.CustomOAuth2UserService;
import com.einfochips.smartinventory.service.CustomUserDetailsService;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{
	private static final Logger logger=LoggerFactory.getLogger(SecurityConfiguration.class);
	@Autowired
	private CustomOAuth2UserService customOAuth2UserService;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		CorsConfigurationSource configurationSource = new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(Arrays.asList(
                        "http://localhost:4200"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setMaxAge(3600L);
                return config;
            }
        };
		http.cors().configurationSource(configurationSource).and().csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers("/publish-message","/viewproducts","/searchforproduct","/viewallsuppliers","/addsupplier","/publishfordevice/*").permitAll()
				.anyRequest().authenticated().and()
				.oauth2Login();
		logger.info("Calling oAuth2UserService");
		return http.build();
	}
//	@Bean
//    public GrantedAuthoritiesMapper userAuthoritiesMapper() {
//        return (authorities) -> {
//            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
//            logger.info("Calling From Authorities Mapper");
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//            if (authentication instanceof OAuth2AuthenticationToken) {
//                OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
//                for (GrantedAuthority authority : authorities) {
//                    if (authority.getAuthority().equals("OIDC_USER")) {
//                        logger.info("USER Scope setting");
//                        mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//                        System.out.println(mappedAuthorities.toString());
//                    } else if (oauthToken.getPrincipal().getAttribute("email").equals("pateljimi21@gmail.com")) {
//                        logger.info("ADMIN Setting");
//                        mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//                    }
//                }
//            }
//            else {
//            	System.out.println("No Authentication Token Found");
//            }
//            return mappedAuthorities;
//        };
//    }
	

   
}
