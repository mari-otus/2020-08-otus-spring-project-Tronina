package ru.otus.spring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Напоминание о скором наступлении брони.
 *
 * @author MTronina
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingNotificationReminder {

    /**
     * Пользователь, которому направляется напоминание.
     */
    private Subscriber subscriber;

    /**
     * Название переговорки.
     */
    private String roomName;

    /**
     * Дата и время начала брони.
     */
    private LocalDateTime beginBookingDate;

    /**
     * Дата и время окончания брони.
     */
    private LocalDateTime endBookingDate;
}
