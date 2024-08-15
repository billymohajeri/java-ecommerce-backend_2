    package com.backend.ecommerce.services;

    import com.backend.ecommerce.dtos.user.UserCreateDto;
    import com.backend.ecommerce.dtos.user.UserLoginDto;
    import com.backend.ecommerce.entities.User;
    import com.backend.ecommerce.repositories.UserJpaRepo;
    import com.backend.ecommerce.shared.utilities.Constants;
    import com.backend.ecommerce.shared.utilities.JwtUtil;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
    import org.springframework.security.core.Authentication;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;

    import java.time.LocalDate;
    import java.time.format.DateTimeFormatter;

    @Service
    public class AuthServiceImpl {

        @Autowired
        AuthenticationManager authenticationManager;
        @Autowired
        UserJpaRepo userJpaRepo;
        @Autowired
        PasswordEncoder passwordEncoder;
        @Autowired
        JwtUtil jwtUtil;

        public User register(UserCreateDto userCreateDto) {
            User user = new User();
            user.setFirstName(userCreateDto.firstName());
            user.setLastName(userCreateDto.lastName());
            user.setEmail(userCreateDto.email());
            user.setAddress(userCreateDto.address());
            user.setPhoneNumber(userCreateDto.phoneNumber());
            user.setRole(userCreateDto.role());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.SERVER_DATE_FORMAT);
            user.setBirthDate(LocalDate.parse(userCreateDto.birthDate(), formatter));
            user.setPassword(passwordEncoder.encode(userCreateDto.password()));
            return userJpaRepo.save(user);
        }

        public String authenticate(UserLoginDto user) {
            Authentication manager = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.email(), user.password()));
            if (manager.isAuthenticated()) {
                User find = userJpaRepo.findByEmail(user.email()).orElseThrow();
                return jwtUtil.generateToken(find);
            }
            return "NOT AUTHENTICATED!";
        }
    }
