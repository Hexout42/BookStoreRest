package ru.lernup.bookstore.controller.request;

import lombok.Builder;
import lombok.Data;

@Data

public class RefreshAccessTokenRequest {
    private String refreshToken;
}
