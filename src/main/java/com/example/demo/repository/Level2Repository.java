package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserLevel2;

@Repository
public interface Level2Repository extends JpaRepository<UserLevel2, Integer>{
	
	Optional<UserLevel2> findByuserID(int userId);

}
