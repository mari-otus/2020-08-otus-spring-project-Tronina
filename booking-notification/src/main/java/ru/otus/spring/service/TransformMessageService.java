package ru.otus.spring.service;

import ru.otus.spring.model.BookingNotificationEvent;
import ru.otus.spring.model.BookingNotificationReminder;

/**
 * @author MTronina
 */
public interface TransformMessageService<T> {

    T[] transformEvent(BookingNotificationEvent message);

    T transformReminder(BookingNotificationReminder message);
}
