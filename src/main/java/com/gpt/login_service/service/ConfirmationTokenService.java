package com.gpt.login_service.service;

import com.gpt.login_service.models.ConfirmationToken;
import com.gpt.login_service.repository.ConfirmationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;
    public void saveConfirmationToken(ConfirmationToken confirmationToken){
        confirmationTokenRepository.save(confirmationToken);
    }
    public Optional<ConfirmationToken> getToken(String token){
        return confirmationTokenRepository.findByToken(token);
    }
    public int updateVerifiedAt(String token){
        return confirmationTokenRepository.updateVerifiedAt(token, LocalDateTime.now());
    }
}
