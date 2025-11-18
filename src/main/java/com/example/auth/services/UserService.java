package com.example.auth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.auth.dtos.UserDTO;
import com.example.auth.dtos.UsertInsertDTO;
import com.example.auth.entities.User;
import com.example.auth.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private UserRepository repository;
	
	@Transactional(readOnly = true)
	public UserDTO findById(String login) {
		User user = repository.findByLogin(login);
		if(user != null) return new UserDTO(user.getLogin(), user.getRole());
		else return null;
	}
	
	@Transactional(readOnly = true)
	public UserDTO userInsert(UsertInsertDTO dto) {
		if(repository.existsByLogin(dto.login())) return null;
		User user = new User(dto.login(), encoder.encode(dto.password()), dto.role());
		repository.save(user);
		return new UserDTO(user.getLogin(), user.getRole());
	}
}
