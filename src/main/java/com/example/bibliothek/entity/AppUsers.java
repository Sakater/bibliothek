package com.example.bibliothek.entity;

import com.example.bibliothek.roles.AppUserRoles;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AppUsers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId", insertable = false, updatable = false, nullable = false)
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthdate;
    private AppUserRoles role;
    private String pseudonym;
    private String password;

    public AppUsers(String firstName,
                    String lastName,
                    String email,
                    LocalDate birthdate,
                    String pseudonym,
                    String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthdate = birthdate;
        this.pseudonym = pseudonym;
        this.password = password;
        this.role=AppUserRoles.USER;
    }
}
