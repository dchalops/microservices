package com.microservice.sampleservice.dto;

import com.microservice.sampleservice.enums.Role;
import lombok.Data;

@Data
public class AuthUserDto {
    private String id;
    private String username;
    private String password;
    private Role role;
}