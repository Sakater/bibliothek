package com.example.bibliothek.appUser;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    /*@PostMapping("/addNewUser")
    public String addNewUser(@RequestBody AppUserRequest request){
        return appUserService.addNewUser(request);
    }*/

}
