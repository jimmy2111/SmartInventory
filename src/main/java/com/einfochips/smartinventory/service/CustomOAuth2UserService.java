package com.einfochips.smartinventory.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

//import com.einfochips.smartinventory.model.RoleEntity;
import com.einfochips.smartinventory.model.User;
import com.einfochips.smartinventory.userrepository.UserRepository;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService{
	
    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    	OAuth2User user = new DefaultOAuth2User(
                getAuthorities(),
                ((OAuth2AuthenticatedPrincipal) userRequest.getAccessToken()).getAttributes(),
                "email"
        );
        return user;
    }
    private Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

	
	

	
}


