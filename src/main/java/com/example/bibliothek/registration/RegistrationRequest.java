package com.example.bibliothek.registration;

import com.example.bibliothek.appUser.AppUserRoles;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class RegistrationRequest {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final LocalDate birthdate;
    private final String userName;
    private final String password;
}
