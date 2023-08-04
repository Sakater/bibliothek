package com.example.bibliothek.appUser;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/profile")
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping("/getSession")
    public String getSession(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = (AppUser) authentication.getPrincipal();
        return appUser.getFirstName();

        // ServletContext context= session.getServletContext();


        // (String) session.getAttribute("SPRING_SECURITY_CONTEXT");
    }
    /*@PostMapping("/addNewUser")
    public String addNewUser(@RequestBody AppUserRequest request){
        return appUserService.addNewUser(request);
    }*/


    @GetMapping("/username")
    public AppUser getAppUser(@RequestParam("username") String username) {
        return appUserService.findByUserName(username);
    }

    @GetMapping("")
    public AppUser getProfile(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String access_token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("ThisIsMySecret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(access_token);
                String username = decodedJWT.getSubject();
                return appUserService.findByUserName(username);
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        } else {
            throw new RuntimeException("You are not logged in...");
        }
    }
}
