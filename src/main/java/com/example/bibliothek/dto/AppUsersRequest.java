package com.example.bibliothek.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class AppUsersRequest {

    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthdate;
    private String pseudonym;
    private String password;

    public boolean isEmpty() {
        if (firstName.isEmpty() && lastName.isEmpty() &&
                email.isEmpty() && birthdate == null &&
                pseudonym.isEmpty() && password.isEmpty()){
            return true;
        }
            return false;
    }

}
