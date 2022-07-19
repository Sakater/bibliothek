package com.example.bibliothek.security.passwordToken;

import com.example.bibliothek.appUser.AppUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class PasswordToken {

    //Expiration time 10 miutes
    private static  final int EXPIRATION_TIME = 10;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private LocalDateTime expirationTime;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_USER_PASSWORD_TOKEN"))
    private AppUser appUser;

    public PasswordToken(AppUser appUser, String token) {
        super();
        this.token = token;
        this.appUser = appUser;
        this.expirationTime = LocalDateTime.now().plusMinutes(EXPIRATION_TIME);
    }

    public PasswordToken(String token) {
        super();
        this.token = token;
        this.expirationTime = LocalDateTime.now().plusMinutes(EXPIRATION_TIME);
    }
}


