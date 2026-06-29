package fr.fae.project.charona.shared;

import org.springframework.security.core.Authentication;
import java.util.UUID;

public class AuthUtils {

    private AuthUtils() {}

    public static UUID extractOwnerId(Authentication authentication) {
        return UUID.fromString(authentication.getName());
    }

    public static boolean isAdmin(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }
}