package com.example.bibliothek.registration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.bibliothek.appUser.AppUser;
import com.example.bibliothek.appUser.AppUserRoles;
import com.example.bibliothek.appUser.AppUserService;
import com.example.bibliothek.registration.token.ConfirmationToken;
import com.example.bibliothek.registration.token.ConfirmationTokenService;
import com.example.bibliothek.security.PasswordRequest;
import com.example.bibliothek.security.passwordToken.PasswordTokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/registration")
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

    @GetMapping("token_refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String refreshToken = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("ThisIsMySecret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refreshToken);
                String username = decodedJWT.getSubject();
                AppUser user = appUserService.findByUserName(username);
                List<AppUserRoles> roles = new ArrayList<>();
                roles.add(user.getRole());
                String accessToken = JWT.create()
                        .withSubject(user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", roles.stream().map(AppUserRoles::toString).collect(Collectors.toList()))
                        .sign(algorithm);

                response.setHeader("access_token", accessToken);
                response.setHeader("refresh_token", refreshToken);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("acces_token", accessToken);
                tokens.put("refresh_token", refreshToken);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);

            } catch (Exception exception) {
                log.error("Error logging in: {}", exception.getMessage());
                response.setHeader("error", exception.getMessage());
                //response.sendError(FORBIDDEN.value());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }

        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }


}
