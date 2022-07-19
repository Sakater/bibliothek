package com.example.bibliothek.security.passwordToken;

import com.example.bibliothek.appUser.AppUser;
import com.example.bibliothek.appUser.AppUserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.security.DenyAll;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class PasswordTokenService {
    private final PasswordTokenRepository passwordTokenRepository;
    private final AppUserService appUserService;


    public void createPasswordResetTokenForUser(AppUser appUser, String token) {
        PasswordToken passwordToken
                = new PasswordToken(appUser,token);
        passwordTokenRepository.save(passwordToken);
    }

    public String passwordTokenMail(AppUser appUser, String applicationUrl, String token) {
        String url =
                applicationUrl
                        + "/registration/savePassword?token="
                        + token;

        //sendVerificationEmail()
        log.info("Click the link to Reset your Password: {}",
                url);
        return url;
    }


    public PasswordToken findByToken(String token) {
        return passwordTokenRepository.findByToken(token);
    }

    public void deleteToken(String token) {
        passwordTokenRepository.deleteByToken(token);
    }

    public Optional<AppUser> findAppUserByPasswordToken(String token) {
        return Optional.ofNullable(passwordTokenRepository.findByToken(token).getAppUser());
    }
}
