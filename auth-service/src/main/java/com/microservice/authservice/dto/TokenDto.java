package com.microservice.authservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDto {
    private String token;
    private String refreshToken;
    private UserDto user;
}
