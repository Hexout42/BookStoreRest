package ru.lernup.bookstore.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.stereotype.Component;
import ru.lernup.bookstore.controller.request.AuthRequest;
import ru.lernup.bookstore.controller.request.RefreshAccessTokenRequest;
import ru.lernup.bookstore.controller.response.AuthResponse;
import ru.lernup.bookstore.controller.response.RefreshAccessTokenResponse;
import ru.lernup.bookstore.model.UserRole;
import ru.lernup.bookstore.security.exeption.JwtAuthenticationException;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class JwtTokenProvider {

    private Algorithm secret;

    private Long validateMSAccess;
    private Long validateMsRefresh;
    private final AuthService authService;
    private Map<String,String> mapRefreshToken = new HashMap<>();

    public JwtTokenProvider(AuthService authService,@Value("${jwt.secret}") String secret,@Value("${jwt.expired}") Long expired) {
        this.authService = authService;
       this.secret = Algorithm.HMAC256(secret);
       this.validateMsRefresh = expired + 10000L;
       this.validateMSAccess = expired;
    }
    public AuthResponse login(AuthRequest request){
      var user = authService.loadUserByUsername(request.getLogin());
      if (request.getPassword().equals(user.getPassword())){
         return AuthResponse.builder()
                  .accessToken(createAccessToken(user.getUsername(),user.getRoles()))
                  .refreshToken(createRefreshToken(user))
                  .userName(user.getUsername())
                  .build();
      }
      else {
          throw new JwtAuthenticationException("Неверный пароль");
      }
    }




    public String createAccessToken(String userName, List<UserRole> roles){
        List<String> role = roles.stream().map(rol->{
           return rol.getRole();
        }).collect(Collectors.toList());

        return JWT.create()
                .withSubject(userName)
                .withExpiresAt(new Date(System.currentTimeMillis() + validateMSAccess))
                .withClaim("roles",role)
                .sign(secret);

    }
    public RefreshAccessTokenResponse refreshAccessToken(RefreshAccessTokenRequest request){
        String userName = getUsernameFromRefreshToken(request.getRefreshToken());
        if (userName!=null){
           UserJwt user= authService.loadUserByUsername(userName);
           createAccessToken(user.getUsername(),user.getRoles());
            return RefreshAccessTokenResponse.builder()
                    .accessToken( createAccessToken(user.getUsername(),user.getRoles()))
                    .build();
        }
        else throw new JwtAuthenticationException("пользователь не найден");
    }
    public String createRefreshToken(UserJwt userJwt){

        String token = JWT.create()
                .withSubject(userJwt.getLogin())
                .withExpiresAt(new Date(System.currentTimeMillis() + validateMsRefresh))
                .sign(secret);
        mapRefreshToken.put(userJwt.getLogin(),token);
        return token;
    }
    public boolean verificationToken(String token){
        try {
            JWTVerifier verifier = JWT.require(secret).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            String username = decodedJWT.getSubject();
            String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
            final List<SimpleGrantedAuthority> authorities = Arrays.stream(roles)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            final UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return true;
        } catch (Exception err) {
            err.printStackTrace();
            return false;
        }
    }
    public String getUsernameFromRefreshToken(String refreshToken) {
        try {
            JWTVerifier verifier = JWT.require(secret).build();
            DecodedJWT decodedJWT = verifier.verify(refreshToken);
            final String subject = decodedJWT.getSubject();

            final String etalon = mapRefreshToken.get(subject);

            return (refreshToken.equals(etalon)) ? subject : null;
        } catch (Exception err) {
            return null;
        }

    }
    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer_")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
    public Authentication getAuthentication(String token) {
        UserJwt userDetails = this.authService.loadUserByUsername(getUserName(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
  public String getUserName(String token){
      JWTVerifier verifier = JWT.require(secret).build();
      DecodedJWT decodedJWT = verifier.verify(token);
      final String subject = decodedJWT.getSubject();
      return subject;
  }




    }


