package com.anuradha.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anuradha.blog.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	
}
