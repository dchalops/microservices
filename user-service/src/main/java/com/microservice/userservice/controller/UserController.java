package com.microservice.userservice.controller;

import com.microservice.userservice.dto.AuthUserDto;
import com.microservice.userservice.dto.PaginatedResponse;
import com.microservice.userservice.dto.UserDto;
import com.microservice.userservice.request.RegisterRequest;
import com.microservice.userservice.request.UserUpdateRequest;
import com.microservice.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/save")
    public ResponseEntity<UserDto> save(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(modelMapper.map(userService.saveUser(request), UserDto.class));
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PaginatedResponse<UserDto>> getAll(
            @RequestParam(defaultValue = "") String keyword,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "asc") String sort) {
        
        return ResponseEntity.ok(userService.getAll(keyword, limit, page, sort));
    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(modelMapper.map(userService.getUserById(id), UserDto.class));
    }

    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(modelMapper.map(userService.getUserByEmail(email), UserDto.class));
    }

    @GetMapping("/getUserByUsername/{username}")
    public ResponseEntity<AuthUserDto> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(modelMapper.map(userService.getUserByUsername(username), AuthUserDto.class));
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN') or @userService.getUserById(#request.id).username == principal")
    public ResponseEntity<UserDto> updateUserById(@Valid @RequestPart UserUpdateRequest request) {
        return ResponseEntity.ok(modelMapper.map(userService.updateUserById(request), UserDto.class));
    }

    @DeleteMapping("/deleteUserById/{id}")
    @PreAuthorize("hasRole('ADMIN') or @userService.getUserById(#id).username == principal")
    public ResponseEntity<Void> deleteUserById(@PathVariable String id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/invokeOtherService")
    public String invokeOtherService() {
        String response = restTemplate.getForObject("http://auth-service/v1/auth/login", String.class);
        return response;
    }
}
