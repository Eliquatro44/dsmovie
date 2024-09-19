package com.devsuperior3.dsmovie3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior3.dsmovie3.entities.User;


public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByEmail(String email);

}