package ru.otus.spring.listener.audit;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.otus.spring.domain.Booking;
import ru.otus.spring.domain.audit.BookingAudit;
import ru.otus.spring.dto.UserDto;
import ru.otus.spring.dto.UserRoleDto;
import ru.otus.spring.repository.audit.BookingAuditRepository;
import ru.otus.spring.security.AuthUserDetails;

import java.security.Principal;
import java.util.stream.Collectors;

/**
 * Управление аудитом бронирований.
 *
 * @author MTronina
 */
@Slf4j
@Component
@RequiredArgsConstructor
@ConditionalOnProperty(name = "app.audit.enabled", havingValue = "true")
public class AuditManager {

    private final BookingAuditRepository bookingAuditRepository;

    /**
     * Добавляет запись аудита при создании, изменении или удалении брони.
     *
     * @param booking информация о брони.
     */
    public void insertAuditEntry(Booking booking) {
        try {
            Principal principal = SecurityContextHolder.getContext().getAuthentication();
            UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) principal;
            AuthUserDetails userDetails = (AuthUserDetails) authenticationToken.getPrincipal();

            BookingAudit bookingAudit = BookingAudit.builder()
                    .bookingId(booking.getId())
                    .room(booking.getRoom())
                    .beginDate(booking.getBeginDate())
                    .endDate(booking.getEndDate())
                    .createDate(booking.getCreateDate())
                    .updateDate(booking.getUpdateDate())
                    .deleteDate(booking.getDeleteDate())
                    .user(UserDto.builder()
                            .fio(userDetails.getFio())
                            .login(userDetails.getUsername())
                            .roles(userDetails.getAuthorities().stream()
                                    .map(authority -> UserRoleDto.builder()
                                            .role(authority.getAuthority())
                                            .build())
                                    .collect(Collectors.toSet()))
                            .build())
                    .build();
            bookingAuditRepository.save(bookingAudit);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
