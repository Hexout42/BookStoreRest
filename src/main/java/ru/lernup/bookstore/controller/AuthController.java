package ru.lernup.bookstore.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.lernup.bookstore.controller.request.AuthRequest;
import ru.lernup.bookstore.controller.request.RefreshAccessTokenRequest;
import ru.lernup.bookstore.controller.response.AuthResponse;
import ru.lernup.bookstore.controller.response.RefreshAccessTokenResponse;
import ru.lernup.bookstore.security.AuthService;
import ru.lernup.bookstore.security.JwtTokenProvider;
import ru.lernup.bookstore.security.UserJwt;

import java.io.IOException;




//@RestController
//@RequestMapping()
//public class AuthController {
//    private final JwtTokenProvider provider;
//
//
//    public AuthController(JwtTokenProvider provider) {
//        this.provider = provider;
//
//    }
//
//
//    @PostMapping("login")
//    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
//
//        return ResponseEntity.ok(provider.login(authRequest));
//    }
//    @PostMapping("/refreshAccessToken")
//    public ResponseEntity<RefreshAccessTokenResponse> refreshAccessToken(@RequestBody RefreshAccessTokenRequest request){
//        return ResponseEntity.ok(provider.refreshAccessToken(request));
//    }
//
//    }


