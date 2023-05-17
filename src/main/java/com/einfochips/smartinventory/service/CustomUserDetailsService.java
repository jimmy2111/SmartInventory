package com.einfochips.smartinventory.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.einfochips.smartinventory.model.User;
import com.einfochips.smartinventory.security.SecurityConfiguration;
import com.einfochips.smartinventory.userrepository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	private static final Logger logger=LoggerFactory.getLogger(CustomUserDetailsService.class);
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		logger.info("From Custom User Details Service");
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println(user.getEmail()+" "+user.getRoles());
        return new UserDetailsConfig(user);
        
	}
	

}
