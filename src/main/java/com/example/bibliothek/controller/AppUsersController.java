package com.example.bibliothek.controller;


import com.example.bibliothek.dto.AppUsersRequest;
import com.example.bibliothek.service.AppUsersService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AppUsersController {

    private final AppUsersService appUsersService;

    @PostMapping("/addNewUser")
    public String addNewUser(@RequestBody AppUsersRequest request){
        return appUsersService.addNewUser(request);
    }

}
