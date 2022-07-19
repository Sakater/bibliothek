package com.example.bibliothek.security.passwordToken;

import com.example.bibliothek.appUser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PasswordTokenRepository extends JpaRepository<PasswordToken, Long> {
    PasswordToken findByToken(String Token);

    void deleteByToken(String token);

}
