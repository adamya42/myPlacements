package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserLevel2;
import com.example.demo.model.UserLevel4;

@Repository
public interface Level4Repository extends JpaRepository<UserLevel4, Integer>{

	Optional<UserLevel4> findByuserID(int userID);

	//Optional<UserLevel4> findByUserid(int userId);

}
