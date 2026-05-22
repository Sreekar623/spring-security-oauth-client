package com.amazon.Oauth.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public AuthenticationProvider authenticationProvider(
            CustomUserDetailsService customUserDetailsService,
            PasswordEncoder passwordEncoder) {

        return new AuthenticationProvider() {

            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {

                String email = authentication.getName();
                String password = authentication.getCredentials().toString();

                System.out.println("🔥 LOGIN ATTEMPT: " + email + " / " + password);

                // ✅ Load user from DB
                UserDetails user = customUserDetailsService.loadUserByUsername(email);

                System.out.println("DB PASSWORD: " + user.getPassword());

                // ✅ Correct password check (BCrypt)
                if (!passwordEncoder.matches(password, user.getPassword())) {
                    System.out.println("❌ PASSWORD MISMATCH");
                    throw new BadCredentialsException("Invalid credentials"); // 🔥 IMPORTANT
                }

                System.out.println("✅ PASSWORD MATCH");

                // ✅ Successful authentication
                return new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities()
                );
            }

            @Override
            public boolean supports(Class<?> authentication) {
                return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
            }
        };
    }
}