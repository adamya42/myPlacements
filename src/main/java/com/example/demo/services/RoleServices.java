package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.repository.RoleRepository;
@Service
public class RoleServices {
	
	@Autowired
	private RoleRepository roleRepository;

	public Optional<Role> getRoleById(int role) {
	Optional <Role> roles = roleRepository.findById(role);
		return roles;
	}
	
	public Optional<Role> getRoleByName(String roleAs){
		Optional <Role> roleDetail = roleRepository.findByRoleAs(roleAs);
		return roleDetail;
	}
	
	public List<Role> getAllRoles(){
		List<Role> roles = roleRepository.findAll();
		return roles;
	}

	public void addRole(@Valid Role role) {
		roleRepository.save(role);
		
	}

}
