package com.einfochips.smartinventory.userrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.einfochips.smartinventory.model.User;

public interface UserRepository extends JpaRepository<User, String>{
	//User findByEmailAndPassword(String email,String Password);
	User findByEmail(String email);

}
