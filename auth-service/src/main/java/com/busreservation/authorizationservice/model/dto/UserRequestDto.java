package com.busreservation.authorizationservice.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


public record UserRequestDto( int id,
                              @NotBlank(message = "User Name is Mandatory.") String username,
                              @NotBlank(message = "Password is Mandatory.")String password,
                              @NotBlank(message = "User Name is Mandatory.") @Email String emailId,
                              @NotBlank(message = "Mobile Number is Mandatory.")String mobileNumber,
                              boolean isEnabled,
                              LocalDateTime creationDate) {
}
