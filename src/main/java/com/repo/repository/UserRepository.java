package com.repo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.repo.model.User;


public interface UserRepository extends JpaRepository<User, Long> {
	public List<User> findByUsername(String username);

}
