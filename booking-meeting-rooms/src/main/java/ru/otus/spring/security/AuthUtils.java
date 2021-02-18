package ru.otus.spring.security;

import lombok.experimental.UtilityClass;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.security.Principal;

/**
 * @author MTronina
 */
@UtilityClass
public class AuthUtils {

    public AuthUserDetails getAuthUserDetails(Principal principal) {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
        return (AuthUserDetails) authenticationToken.getPrincipal();
    }
}
