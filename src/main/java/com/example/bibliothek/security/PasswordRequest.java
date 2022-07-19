package com.example.bibliothek.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PasswordRequest {
    private String email;
    private String oldPassword;
    private String newPassword;
}
