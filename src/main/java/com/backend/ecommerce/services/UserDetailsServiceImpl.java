package com.backend.ecommerce.services;

import com.backend.ecommerce.repositories.UserJpaRepo;
import com.backend.ecommerce.shared.exceptions.ErrorConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserJpaRepo userJpaRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userJpaRepo.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(ErrorConstants.ErrorMessage.USER_DOES_NOT_EXIST));
    }
}
