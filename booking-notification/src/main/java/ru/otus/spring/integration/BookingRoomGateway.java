package ru.otus.spring.integration;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Payload;
import ru.otus.spring.model.BookingNotificationReminder;
import ru.otus.spring.model.BookingNotificationEvent;

import java.util.List;

@MessagingGateway
public interface BookingRoomGateway {

    @Gateway(requestChannel = "bookingRoomInEmailChannel")
    void processEmailNotify(@Payload BookingNotificationEvent booking);

    @Gateway(requestChannel = "bookingRoomInSmsChannel")
    void processSmsNotify(@Payload BookingNotificationEvent booking);

    @Gateway(requestChannel = "bookingRoomReminderInEmailChannel")
    void processEmailNotifyReminder(@Payload List<BookingNotificationReminder> booking);

    @Gateway(requestChannel = "bookingRoomReminderInSmsChannel")
    void processSmsNotifyReminder(@Payload List<BookingNotificationReminder> booking);
}
