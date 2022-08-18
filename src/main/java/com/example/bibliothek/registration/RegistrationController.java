package com.example.bibliothek.registration;

import com.example.bibliothek.appUser.AppUser;
import com.example.bibliothek.appUser.AppUserService;
import com.example.bibliothek.registration.token.ConfirmationToken;
import com.example.bibliothek.registration.token.ConfirmationTokenService;
import com.example.bibliothek.security.PasswordRequest;
import com.example.bibliothek.security.passwordToken.PasswordTokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/registration")
@Slf4j
@AllArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    private final ConfirmationTokenService confirmationTokenService;
    private final AppUserService appUserService;
    private final PasswordTokenService passwordTokenService;

    @PostMapping("/register")
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "/confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

    @GetMapping("/resendVerifyToken")
    public String resendVerificationToken(@RequestParam("email") String email,
                                          HttpServletRequest request) {
        //find AppUser with Email in Parameter
        AppUser appUser = (AppUser) appUserService.loadUserByUsername(email);

        //
        ConfirmationToken confirmationToken = confirmationTokenService.verifyNewToken(appUser);

        //Build a URL
        String url = registrationService.resendVerificationTokenMail(
                appUser,
                registrationService.applicationUrl(request),
                confirmationToken);
        return "Click the link to verify your account: " + url;
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody PasswordRequest passwordRequest, HttpServletRequest request) {
        AppUser appUser = (AppUser) appUserService.loadUserByUsername(passwordRequest.getEmail());
        String url;
        String token = UUID.randomUUID().toString();
        passwordTokenService.createPasswordResetTokenForUser(appUser, token);
        url = passwordTokenService.passwordTokenMail(
                appUser, registrationService.applicationUrl(request), token);
        return url;
    }

    @PostMapping("/savePassword")
    public String savePassword(@RequestParam("token") String token,
                               @RequestBody PasswordRequest request) {
        String result = registrationService.confirmPasswordToken(token);
        if (!result.equalsIgnoreCase("valid")) {
            return "invalid token";
        }
        Optional<AppUser> appUser = passwordTokenService.findAppUserByPasswordToken(token);

        if (appUser.isPresent()) {
            appUserService.changePassword(appUser.get(), request.getNewPassword());
            return "Password Reset Successfully!";
        }
        return "Invalid Token!";
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestBody PasswordRequest passwordRequest) {
        return registrationService.changePassword(passwordRequest);
    }


}
