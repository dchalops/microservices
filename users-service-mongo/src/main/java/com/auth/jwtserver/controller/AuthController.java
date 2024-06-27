package com.auth.jwtserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.jwtserver.dto.LoginDto;
import com.auth.jwtserver.dto.SignupDto;
import com.auth.jwtserver.dto.TokenDto;
import com.auth.jwtserver.service.AuthService;
import com.auth.jwtserver.utility.ResponseBuilder;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
	@Autowired
	AuthService authService;
	
	@Operation(summary = "Inicio de sesión de usuario")
    @PostMapping("/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginDto dto) {
    	TokenDto data = authService.login(dto);
    	return ResponseBuilder.build(HttpStatus.OK, null, "Successfully Logged In", data);
    }

	@Operation(summary = "Cierre de sesión de usuario desde un solo dispositivo")
    @PostMapping("/logout")
    public ResponseEntity<Object> logout(@Valid @RequestBody TokenDto dto) {
        authService.logout(dto);
        return ResponseBuilder.build(HttpStatus.OK, null, "Successfully Logged Out", null);
    }

	@Operation(summary = "Cierre de sesión de usuario en todos los dispositivos")
    @PostMapping("/logout-all")
    public ResponseEntity<Object> logoutAll(@Valid @RequestBody TokenDto dto) {
        authService.logoutAll(dto);
    	return ResponseBuilder.build(HttpStatus.OK, null, "Successfully Logged Out Of All Devices", null);
    }

	@Operation(summary = "Generar nuevo token de acceso")
    @PostMapping("/access-token")
    public ResponseEntity<Object> accessToken(@Valid @RequestBody TokenDto dto) {
        TokenDto data = authService.accessToken(dto);
        return ResponseBuilder.build(HttpStatus.OK, null, "Successfully Generated New Access Token", data);
    }

	@Operation(summary = "Generar nuevo token de actualización")
    @PostMapping("/refresh-token")
    public ResponseEntity<Object> refreshToken(@RequestBody TokenDto dto) {
        TokenDto data = authService.refreshToken(dto);
        return ResponseBuilder.build(HttpStatus.OK, null, "Successfully Generated New Refresh Token", data);
    }
}
