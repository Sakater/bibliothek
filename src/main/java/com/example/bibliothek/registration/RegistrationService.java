package com.example.bibliothek.registration;

import com.example.bibliothek.appUser.AppUser;
import com.example.bibliothek.appUser.AppUserRepository;
import com.example.bibliothek.appUser.AppUserRoles;
import com.example.bibliothek.appUser.AppUserService;
import com.example.bibliothek.registration.token.ConfirmationToken;
import com.example.bibliothek.registration.token.ConfirmationTokenService;
import com.example.bibliothek.security.PasswordRequest;
import com.example.bibliothek.security.passwordToken.PasswordToken;
import com.example.bibliothek.security.passwordToken.PasswordTokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
@Slf4j
public class RegistrationService {
    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    //private final EmailSender emailSender;
    private final PasswordTokenService passwordTokenService;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }
        return appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getBirthdate(),
                        AppUserRoles.USER,
                        request.getUserName(),
                        request.getPassword()
                )
        );
    }

    public String confirmPasswordToken(String token) {
        PasswordToken passwordToken = passwordTokenService.findByToken(token);
        if (passwordToken.getToken() == null) {
            throw new IllegalStateException("token not found");
        }
        LocalDateTime expiredAt = passwordToken.getExpirationTime();
        if (expiredAt.isBefore(LocalDateTime.now())) {
            passwordTokenService.deleteToken(token);
            throw new IllegalStateException("token expired");
        }

        return "valid";

    }


    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token);
        if (confirmationToken == null) {
            throw new IllegalStateException("token not found");
        }

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        appUserService.enableAppUser(
                confirmationToken.getAppUser().getEmail());
        return "confirmed";
    }

    public String resendVerificationTokenMail(AppUser appUser, String applicationUrl, ConfirmationToken token) {
        String url =
                applicationUrl +
                        "/registration/confirm?token=" +
                        token.getToken();
        return url;
    }

    public String applicationUrl(HttpServletRequest request) {
        return "http://" +
                request.getServerName() +
                ":" +
                request.getServerPort() +
                request.getContextPath();
    }

    public String changePassword(PasswordRequest passwordRequest) {
        AppUser appUser= (AppUser) appUserService.loadUserByUsername(passwordRequest.getEmail());
        if (!appUserService.checkIfOldPasswordIsValid(appUser,passwordRequest.getOldPassword())){
            return "Password incorrect!";
        }
        appUserService.changePassword(appUser,passwordRequest.getNewPassword());
        return "Password changed Successfully!";
    }
}
