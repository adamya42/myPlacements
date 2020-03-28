package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserLevel6;

@Repository
public interface Level6Repository extends JpaRepository<UserLevel6, Integer>{

}
