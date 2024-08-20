//package com.tinqinacademy.authentication.core.security.config;
//
//
//import com.tinqinacademy.authentication.core.errorexceptionhandling.exceptions.UserNotFoundException;
//import com.tinqinacademy.authentication.persistence.repository.UserRepository;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//public class ApplicationConfig {
//    private final UserRepository userRepository;
//
//    public ApplicationConfig(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
////    @Bean
////    public UserDetailsService getUserDetailsByUsername() {
////        return username -> userRepository.findByName(username)
////                .orElseThrow(()-> new UserNotFoundException(username));
////    }
////
////    @Bean
////    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
////        return configuration.getAuthenticationManager();
////    }
////
////    @Bean
////    public AuthenticationProvider authenticationProvider(){
////        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
////        authProvider.setPasswordEncoder(passwordEncoder());
////        return authProvider;
////    }
//
//
//}
