package com.backend.ecommerce.shared.configs;

import com.backend.ecommerce.entities.enums.AuthenticationRole;
import com.backend.ecommerce.services.UserDetailsServiceImpl;
import com.backend.ecommerce.shared.filters.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  @Autowired
  private UserDetailsServiceImpl userDetailsService;
  @Autowired
  private AuthFilter authFilter;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
            .cors(cors -> cors.configurationSource(request -> {
              CorsConfiguration config = new CorsConfiguration();
              config.setAllowedOrigins(List.of("http://localhost:3000"));
              config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
              config.setAllowedHeaders(List.of("Authorization", "Content-Type"));
              return config;
            }))

            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(req -> req
                    .requestMatchers(HttpMethod.GET, "/api/v1/products", "/api/v1/products/*").permitAll()
                    .requestMatchers(
                            "/api/v1/users/login",
                            "/api/v1/users/register",
                            "/api/v1/carts", "/api/v1/orders",
                            "/api/v1/reviews/product/*"
                    )
                    .permitAll()

                    .requestMatchers(HttpMethod.POST, "/api/v1/products").hasAuthority(AuthenticationRole.ADMIN.name())
                    .requestMatchers(HttpMethod.PUT, "/api/v1/products").hasAuthority(AuthenticationRole.ADMIN.name())
                    .requestMatchers(HttpMethod.DELETE, "/api/v1/products").hasAuthority(AuthenticationRole.ADMIN.name())
                    .requestMatchers(HttpMethod.PATCH, "/api/v1/products").hasAuthority(AuthenticationRole.ADMIN.name())
                    .requestMatchers("/api/v1/users/profile").hasAnyAuthority(AuthenticationRole.ADMIN.name(), AuthenticationRole.USER.name())
                    .requestMatchers(
                            "/api/v1/users/**",
                            "/api/v1/reviews/**",
                            "/api/v1/products"
                    ).hasAuthority(AuthenticationRole.ADMIN.name())
                    .requestMatchers(
                            "/api/v1/orders",
                            "/api/v1/orders/**",
                            "/api/v1/payments",
                            "/api/v1/payments/**").hasAuthority(AuthenticationRole.USER.name())
                    .anyRequest()
                    .authenticated())
            .userDetailsService(userDetailsService)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }
}
