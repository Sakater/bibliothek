package com.example.bibliothek.appUser;


import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@AllArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @GetMapping("/getSession")
    public String getSession(HttpServletRequest request) {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser= (AppUser) authentication.getPrincipal();
        return appUser.getFirstName();

       // ServletContext context= session.getServletContext();



        // (String) session.getAttribute("SPRING_SECURITY_CONTEXT");
    }
    /*@PostMapping("/addNewUser")
    public String addNewUser(@RequestBody AppUserRequest request){
        return appUserService.addNewUser(request);
    }*/

}
