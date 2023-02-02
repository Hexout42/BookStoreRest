package ru.lernup.bookstore.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefreshAccessTokenResponse {
    private String accessToken;
}
