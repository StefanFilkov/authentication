//package com.tinqinacademy.authentication.core.security.config;
//
//import com.tinqinacademy.authentication.core.security.CustomToken;
//import com.tinqinacademy.authentication.core.security.UserPrincipal;
//import com.tinqinacademy.authentication.core.security.filter.JwtService;
//import com.tinqinacademy.authentication.persistence.entities.User;
//import com.tinqinacademy.authentication.persistence.repository.UserRepository;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.http.HttpHeaders;
//import org.springframework.lang.NonNull;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import org.springframework.web.servlet.HandlerExceptionResolver;
//
//import java.io.IOException;
//import java.util.UUID;
//
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//    private final HandlerExceptionResolver handlerExceptionResolver;
//
//    private final JwtService jwtService;
//    private final UserRepository userRepository;
//
//    public JwtAuthenticationFilter(
//            JwtService jwtService,
//            HandlerExceptionResolver handlerExceptionResolver,
//            UserRepository userRepository) {
//        this.jwtService = jwtService;
//        this.handlerExceptionResolver = handlerExceptionResolver;
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    protected void doFilterInternal(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain filterChain
//    ) throws ServletException, IOException {
//
//
//
//        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//
//        if (authHeader != null && authHeader.startsWith("Bearer ")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//
//        try {
//            final String jwt = authHeader.substring(7);
//            final String userId = jwtService.extractUserId(jwt);
//
//            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//
//            if (authentication != null) {
//                User user = userRepository.findById(UUID.fromString(userId)).
//                        orElseThrow(() -> new UsernameNotFoundException(userId));
//
//                if (jwtService.isTokenValid(jwt, user) ) {
//
//                    UserPrincipal userPrincipal = UserPrincipal.builder()
//                            .role("USER")
//                            .userId(userId)
//                            .build();
//
//
//                    CustomToken userToken =new CustomToken(userPrincipal,jwt);
//
//                    SecurityContextHolder.getContext().setAuthentication(userToken);
//                }
//            }
//
//            filterChain.doFilter(request, response);
//        } catch (Exception exception) {
//            handlerExceptionResolver.resolveException(request, response, null, exception);
//        }
//    }
//}