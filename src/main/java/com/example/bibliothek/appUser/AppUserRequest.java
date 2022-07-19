package com.example.bibliothek.appUser;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class AppUserRequest {

    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthdate;
    private String userName;
    private String password;

    public boolean isEmpty() {
        if (firstName.isEmpty() && lastName.isEmpty() &&
                email.isEmpty() && birthdate == null &&
                userName.isEmpty() && password.isEmpty()){
            return true;
        }
            return false;
    }

}
