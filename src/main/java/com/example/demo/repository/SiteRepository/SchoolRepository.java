package com.example.demo.repository.SiteRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SiteModel.Schools;

@Repository
public interface SchoolRepository extends JpaRepository<Schools, Integer>{

	Optional<Schools> findBySchoolCode(String schoolCode);

}
