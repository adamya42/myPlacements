package com.example.demo.services;

import java.util.Optional;

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

}
