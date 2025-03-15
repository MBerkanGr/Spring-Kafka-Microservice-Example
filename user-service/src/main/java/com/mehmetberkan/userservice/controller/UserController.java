package com.mehmetberkan.userservice.controller;

import com.mehmetberkan.userservice.dto.AddressResponseDto;
import com.mehmetberkan.userservice.dto.UserCreateRequest;
import com.mehmetberkan.userservice.dto.UserResponse;
import com.mehmetberkan.userservice.model.User;
import com.mehmetberkan.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    private final RestTemplate restTemplate;
    private final UserService userService;

    public UserController(RestTemplate restTemplate, UserService userService) {
        this.restTemplate = restTemplate;
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody UserCreateRequest userCreateRequest) {
        return userService.create(userCreateRequest);
    }

    @GetMapping("/{userId}")
    public UserResponse getUserAddress(@PathVariable Long userId) {
        String url = String.format("http://localhost:8002/api/address/%s", userId);
        ResponseEntity<AddressResponseDto> address = restTemplate.getForEntity(url, AddressResponseDto.class);

        User user = userService.getUserById(address.getBody().getUserId());
        return UserResponse.getUserResponseWithAddress(user, address.getBody());
    }
}
