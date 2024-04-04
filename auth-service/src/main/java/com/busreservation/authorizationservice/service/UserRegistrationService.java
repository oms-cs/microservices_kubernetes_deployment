package com.busreservation.authorizationservice.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.busreservation.authorizationservice.model.User;
import com.busreservation.authorizationservice.model.dto.UserRequestDto;
import com.busreservation.authorizationservice.repository.AuthorityRepository;
import com.busreservation.authorizationservice.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserRegistrationService implements UserDetailsService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final AuthorityRepository authorityRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public User registerUser(UserRequestDto userRequestDto){
        User user = new User();
        user.setUsername(userRequestDto.username());
        user.setPassword(passwordEncoder.encode(userRequestDto.password()));
        user.setEmailId(userRequestDto.emailId());
        user.setMobileNumber(userRequestDto.mobileNumber());
        user.setCreationDate(LocalDateTime.now());
        user.setEnabled(true);
        user.setAuthorities(authorityRepository.findAll());
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
