package com.microservice.jobservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservice.jobservice.dto.UserDto;

@FeignClient(name = "user-service", path = "/v1/user")
public interface UserServiceClient {
    @GetMapping("/getUserById/{id}")
    ResponseEntity<UserDto> getUserById(@PathVariable String id);
}
