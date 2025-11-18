package com.example.auth.dtos;

import com.example.auth.entities.Role;

public record UserDTO(String login, Role role) {

}
