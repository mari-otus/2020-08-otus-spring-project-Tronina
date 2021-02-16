package ru.otus.spring.model;

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
public class Subscriber {

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
