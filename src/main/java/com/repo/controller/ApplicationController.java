package com.repo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.repo.UserConverter;
import com.repo.dto.ResponseDTO;
import com.repo.service.UserService;

import com.repo.model.User;




@SpringBootApplication
@RestController
@EntityScan("com.repo.model")
public class ApplicationController {
	
	@Autowired
	private UserService user;
	
	@Autowired
	private UserConverter converter;
	
	
	@RequestMapping("/is-authenticated")
	public ResponseDTO checkAuth(HttpSession session) {
		ResponseDTO reply = new ResponseDTO();
		reply.setSessionID(session.getId());
		String sessionId = (String) session.getAttribute("sessionId");
		
		if (session.getAttribute("isLoggedIn") != null && (Boolean) session.getAttribute("isLoggedIn")) {
			session.setAttribute("isLoggedIn", false);
			reply.setMessage("Currently LoggedIn");
		} else {
			reply.setMessage("Not logged in");
		}
		return reply;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.PUT)
	public ResponseDTO logout(@RequestBody ResponseDTO userInfo, HttpSession session) {
		ResponseDTO reply = new ResponseDTO();
		reply.setSessionID(session.getId());
		String sessionId = (String) session.getAttribute("sessionId");
		
		if (sessionId != null && sessionId.equals(session.getId())) {
			session.invalidate();
			reply.setMessage("Succesfully logged out");
		} else {
			reply.setMessage("Cannot log out since not logged in");
		}
		return reply;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseDTO postBody(@RequestBody User userInfo, HttpSession session) {
		
		String sessionId = null;
		User newUser = user.findUser(userInfo.getUsername());
		converter.setCandidatePass(userInfo.getPassword());
		ResponseDTO userdto = converter.convert(newUser);
		
		sessionId = session.getId();
	
		
		if(converter.isAuth())
			session.setAttribute("sessionId", sessionId);
			session.setAttribute("isLoggedIn", true);
			userdto.setSessionID(sessionId);
		
        return userdto;
    }

}
