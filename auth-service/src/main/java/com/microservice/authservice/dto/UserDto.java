package com.microservice.authservice.dto;

import com.microservice.authservice.enums.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private String id;
    private String username;
    private String password;
    private String email;
    private String first_name;
    private String last_name;
    private Role role;
}
