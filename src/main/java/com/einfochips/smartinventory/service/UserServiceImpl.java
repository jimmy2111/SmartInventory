package com.einfochips.smartinventory.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.einfochips.smartinventory.model.RoleEntity;
import com.einfochips.smartinventory.model.User;
import com.einfochips.smartinventory.userrepository.RoleRepository;
import com.einfochips.smartinventory.userrepository.UserRepository;

@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public void createUserWithRole(String email, String name,String roleName) {
        // Create a new role if it doesn't exist
        RoleEntity role = roleRepository.findByName(roleName);
        if (role == null) {
            role = new RoleEntity(roleName);
            roleRepository.save(role);
        }

        // Create a new user with the given email, name, and password, and assign the role to the user
        User user = userRepository.findByEmail(email);
        if(user==null) {
        userRepository.save(new User(name, email,Arrays.asList(role)));
        }
        else {
        	user.setEmail(email);
        	user.setName(name);
        	userRepository.save(user);
        }
    }
}

