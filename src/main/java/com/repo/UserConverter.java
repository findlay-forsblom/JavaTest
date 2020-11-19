package com.repo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCrypt;

import com.repo.dto.ResponseDTO;

@Configuration
public class UserConverter {
	
	private String candidatePass;
	boolean auth;
	public String getCandidatePass() {
		return candidatePass;
	}
	public void setCandidatePass(String candidatePass) {
		this.candidatePass = candidatePass;
	}
	
	public boolean isAuth() {
		return auth;
	}
	public void setAuth(boolean auth) {
		this.auth = auth;
	}
	// @Autowired
	// private UserDTO userDTO;
	public ResponseDTO convert(com.repo.model.User source) {
		// TODO Auto-generated method stub
		ResponseDTO lol = new ResponseDTO ();
		if (source == null) {
			auth = false;
			lol.setMessage("User not found");
		} else {
			auth = BCrypt.checkpw(candidatePass, source.getPassword());
			if (auth) {
				lol.setMessage("Succesfully logged in");
			} else {
				lol.setMessage("Username or password is incorrect");
			}
			// lol.setMessage("User found " +auth);
		}
		
		
		return lol;
	}


}
