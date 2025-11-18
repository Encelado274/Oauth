package com.example.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth.dtos.AuthenticationDTO;
import com.example.auth.dtos.UsertInsertDTO;
import com.example.auth.repositories.UserRepository;
import com.example.auth.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private UserService userService;
 
	@PostMapping("/login")
	public ResponseEntity<Void> login(@RequestBody AuthenticationDTO dto){
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(dto.login(), dto.password())
		);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/register")
	public ResponseEntity<Void> register(@RequestBody UsertInsertDTO dto) {
		if(repository.existsByLogin(dto.login())) return ResponseEntity.badRequest().build();
		
		userService.userInsert(dto);
		return ResponseEntity.ok().build();
	}
}
