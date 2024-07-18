package com.microservice.userservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.microservice.userservice.model.UserDetails;
import com.microservice.userservice.enums.Role;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private String id;
    private String username;
    private String email;
    private String phone;
    private Role role;
    private String status;
    private boolean verified;
    private UserDetails userDetails;
}
