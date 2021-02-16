package ru.otus.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Профиль пользователя.
 *
 * @author MTronina
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {

    private String email;

    private String mobilePhone;

    /**
     * Отправлять уведомления по email.
     */
    private boolean isEmailNotify;

    /**
     * Отправлять уведомления по sms.
     */
    private boolean isPhoneNotify;

}
