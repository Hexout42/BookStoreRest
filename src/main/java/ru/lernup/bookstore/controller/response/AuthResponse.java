package ru.lernup.bookstore.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private String userName;
    private String accessToken;
    private String refreshToken;
}
