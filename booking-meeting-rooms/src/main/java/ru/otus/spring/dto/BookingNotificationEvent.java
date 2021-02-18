package ru.otus.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Уведомление о действиях с переговорными комнатами (изменение, удаление, создание брони).
 *
 * @author MTronina
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingNotificationEvent {

    /**
     * Список подписчиков на переговорку.
     */
    private List<SubscriberDto> subscribers;

    /**
     * Название переговорки.
     */
    private String roomName;

    /**
     * ФИО пользователя, совершившего изменения с бронью этой переговоркки.
     */
    private String fioOfBooking;

    /**
     * Дата и время начала брони.
     */
    private LocalDateTime beginBookingDate;

    /**
     * Дата и время окончания брони.
     */
    private LocalDateTime endBookingDate;

    /**
     * Дата и время создания записи.
     */
    private LocalDateTime createBookingDate;

    /**
     * Дата и время изменения записи.
     */
    private LocalDateTime updateBookingDate;

    /**
     * Дата и время удаления записи (брони).
     */
    private LocalDateTime deleteBookingDate;
}
