package com.einfochips.smartinventory.userrepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.einfochips.smartinventory.model.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

	RoleEntity findByName(String name);

}
