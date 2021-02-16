package ru.otus.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Данные подписчика.
 *
 * @author MTronina
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriberDto {

    private String fio;

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
