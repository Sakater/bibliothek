package com.example.bibliothek.service;


import com.example.bibliothek.dto.AppUsersRequest;
import com.example.bibliothek.entity.AppUsers;
import com.example.bibliothek.repository.AppUsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUsersService {
    private final AppUsersRepository appUsersRepository;

    public String addNewUser(AppUsersRequest request) {
        if (!request.isEmpty()) {
            appUsersRepository.save(new AppUsers(request.getFirstName(),
                    request.getLastName(),
                    request.getEmail(),
                    request.getBirthdate(),
                    request.getPseudonym(),
                    request.getPassword()));
            return "saved";
        }
        return "empty";
    }

}
