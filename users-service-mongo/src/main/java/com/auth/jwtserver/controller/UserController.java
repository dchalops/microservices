package com.auth.jwtserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.jwtserver.document.User;
import com.auth.jwtserver.dto.SignupDto;
import com.auth.jwtserver.dto.TokenDto;
import com.auth.jwtserver.service.UserService;
import com.auth.jwtserver.utility.ResponseBuilder;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
    
	@Autowired
    UserService userService;

    @Operation(summary = "Nuevo usuario")
	@PostMapping("/newUser")
    public ResponseEntity<Object> signup(@Valid @RequestBody SignupDto dto) {
        TokenDto responseData = userService.signup(dto);
        return ResponseBuilder.build(HttpStatus.CREATED, null, "The user was created correctly", responseData);
    }

    @Operation(summary = "Editar usuario")
    @PutMapping("/edit/{id}")
	@SecurityRequirement(name = "bearerAuthToken")
    public ResponseEntity<Object> editUser(@AuthenticationPrincipal User user, @PathVariable String id, @Valid @RequestBody SignupDto dto) {
        TokenDto responseData = userService.editUser(dto, id);
        return ResponseBuilder.build(HttpStatus.CREATED, null, "The user was edited successfully", responseData);
    }

    @Operation(summary = "Obtener usuarios")
    @GetMapping("/userList")
	//@SecurityRequirement(name = "bearerAuthToken")
    public ResponseEntity<Object> getUserProfile() {
        List<User> users = userService.findAll();
    	return ResponseBuilder.build(HttpStatus.OK, null, "Success", users);
    }

	@Operation(summary = "Obtener usuario por ID")
    @GetMapping("/{id}")
    //@PreAuthorize("#user.id == #id")
	//@SecurityRequirement(name = "bearerAuthToken")
    public ResponseEntity<Object> getUserById(@AuthenticationPrincipal User user, @PathVariable String id) {
    	List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }
    
	@Operation(summary = "Eliminar usuario")
    @DeleteMapping("/{id}")
    //@PreAuthorize("#user.id == #id")
	//@SecurityRequirement(name = "bearerAuthToken")
    public ResponseEntity<Object> deleteUserById(@AuthenticationPrincipal User user, @PathVariable String id) {
    	userService.deleteUserById(id);
    	return ResponseBuilder.build(HttpStatus.OK, null, "Account Deleted", null);
    }
}
