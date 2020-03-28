package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.UserOTP;
@Repository
public interface UserOTPRepository extends JpaRepository<UserOTP, Integer> {
	
	public List<UserOTP> findByUserId(int userId);
}
