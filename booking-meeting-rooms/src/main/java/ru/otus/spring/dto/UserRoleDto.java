package ru.otus.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * Роли пользователя.
 *
 * @author MTronina
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleDto implements GrantedAuthority {

    /**
     * Наименование роли.
     */
    private String role;

    @Override
    public String getAuthority() {
        return role;
    }
}
