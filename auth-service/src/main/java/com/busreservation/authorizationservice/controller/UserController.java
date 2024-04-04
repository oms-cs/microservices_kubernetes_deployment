package com.busreservation.authorizationservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.busreservation.authorizationservice.model.dto.UserRequestDto;
import com.busreservation.authorizationservice.service.UserRegistrationService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/auth")
public class UserController {

    @Autowired
    private final UserRegistrationService userRegistrationService;

    @PostMapping("/signup")
    public ResponseEntity registerUser(@RequestBody UserRequestDto userRequestDto){
        userRegistrationService.registerUser(userRequestDto);
        return ResponseEntity.ok().body("User "+ userRequestDto.username()+" Created Successfully");
    }

    @GetMapping("/test")
    public String publicPage(){
        return "This is A Public Page!!";
    }
}
