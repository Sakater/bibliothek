package com.example.bibliothek.registration.token;

import com.example.bibliothek.appUser.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public void saveConfirmationToken(ConfirmationToken token){
        confirmationTokenRepository.save(token);
    }


    public ConfirmationToken getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
    public ConfirmationToken verifyNewToken(AppUser appUser) {
        //load the tokenrow in the db by the UserId
        ConfirmationToken confirmationToken=getTokenByUserId(appUser.getUserId());
        //give the token a new random TokenString
        confirmationToken.setToken(UUID.randomUUID().toString());
        //Extend the expiring-time for the token
        confirmationToken.setExpiresAt(LocalDateTime.now().plusMinutes(15));
        //save the new token in its row
        saveConfirmationToken(confirmationToken);
        return confirmationToken;
    }

    private ConfirmationToken getTokenByUserId(Long userId) {
        return confirmationTokenRepository.findByAppUser_UserId(userId);
    }


}

