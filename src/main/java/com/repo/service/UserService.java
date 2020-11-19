package com.repo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repo.model.User;
import com.repo.repository.UserRepository;


@Service
public class UserService {
	@Autowired
    private UserRepository repository;
	
    public User findUser(String username) {

        List<User> list = repository.findByUsername(username);
        if (list.size() > 0) {
        	return list.get(0);
        } else {
        	return null;
        }
    }

}
