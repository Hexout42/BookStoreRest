package ru.lernup.bookstore.security.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import ru.lernup.bookstore.security.JwtTokenProvider;

import java.io.IOException;
@Slf4j
public class JwtTokenFilter extends GenericFilterBean {
    private final JwtTokenProvider provider;

    public JwtTokenFilter(JwtTokenProvider provider) {
        this.provider = provider;
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

               String url = ((HttpServletRequest) servletRequest).getRequestURI();
              log.info(url);
              if (!url.equals("/login") ){
                  String token =provider.resolveToken((HttpServletRequest) servletRequest);
        if (token != null && provider.verificationToken(token)) {
            Authentication auth = provider.getAuthentication(token);

            if (auth != null) {
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
              }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
