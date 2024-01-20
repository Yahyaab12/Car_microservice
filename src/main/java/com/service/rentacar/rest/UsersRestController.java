package com.service.rentacar.rest;

import com.service.rentacar.domain.user.UserService;
import com.service.rentacar.domain.user.dto.UserRegistration;
import com.service.rentacar.rest.request.UserRegistrationRequest;
import com.service.rentacar.rest.response.UserRegistrationResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UsersRestController
{
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponse> register(@RequestBody UserRegistrationRequest userRegistrationRequest)
    {
        userService.register(new UserRegistration(userRegistrationRequest.getUsername(), userRegistrationRequest.getEmail(), userRegistrationRequest.getPassword()));
        return ResponseEntity.ok().build();
    }
}
