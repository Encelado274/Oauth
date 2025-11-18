package com.example.auth.dtos;

import com.example.auth.entities.Role;

public record UsertInsertDTO(String login, String password, Role role) {

}
