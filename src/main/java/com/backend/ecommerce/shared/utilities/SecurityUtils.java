package com.backend.ecommerce.shared.utilities;

import com.backend.ecommerce.entities.User;
import com.backend.ecommerce.repositories.UserJpaRepo;
import com.backend.ecommerce.shared.exceptions.ErrorConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.UUID;

@Component
public class SecurityUtils {

    @Autowired
    private UserJpaRepo userJpaRepo;

    public static String getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        String userName = null;
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof UserDetails springSecurityUser) {
                userName = springSecurityUser.getUsername();
            } else if (authentication.getPrincipal() instanceof String) {
                userName = (String) authentication.getPrincipal();
            }
        }
        return userName;
    }

    public static boolean isAuthenticated() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return securityContext.getAuthentication().isAuthenticated();
    }

    public boolean isOwner(UUID userId) {
        String authenticatedUserEmail = getCurrentUserLogin();

        String userEmail = userJpaRepo.findById(userId)
                .map(User::getEmail)
                .orElseThrow(() -> new NoSuchElementException(ErrorConstants.ErrorMessage.USER_DOES_NOT_EXIST));

        return authenticatedUserEmail.equals(userEmail);
    }
}
