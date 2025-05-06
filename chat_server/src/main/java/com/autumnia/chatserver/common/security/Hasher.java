package com.autumnia.chatserver.common.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Slf4j
@Service
public class Hasher {
    public String get_hashing_value(String value) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(value.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);

        } catch ( NoSuchAlgorithmException e) {
            log.error("Hashing failed: {}", e.getMessage());
            throw new RuntimeException("Hashing failed", e);
        }
    }
}
