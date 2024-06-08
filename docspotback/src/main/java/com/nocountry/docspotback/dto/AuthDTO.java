package com.nocountry.docspotback.dto;

import java.util.Optional;

import com.nocountry.docspotback.models.User;

public class AuthDTO {
    public record LoginRequest(String username, String password) {
    }

    public record Response(String message, String token, Optional<User> user) {
    }
}