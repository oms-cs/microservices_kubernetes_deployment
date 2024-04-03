package com.airy.ecom.authorizationservice.controller;


import com.airy.ecom.authorizationservice.model.dto.UserRequestDto;
import com.airy.ecom.authorizationservice.service.UserRegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
