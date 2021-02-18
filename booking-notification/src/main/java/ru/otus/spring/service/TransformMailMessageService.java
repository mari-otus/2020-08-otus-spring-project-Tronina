package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.NotificationProperties;
import ru.otus.spring.model.BookingNotificationEvent;
import ru.otus.spring.model.BookingNotificationReminder;
import ru.otus.spring.model.Subscriber;

import java.text.MessageFormat;

/**
 * Сервис трансформации данных в email-сообщение.
 *
 * @author MTronina
 */
@Service
@RequiredArgsConstructor
public class TransformMailMessageService implements TransformMessageService<SimpleMailMessage> {

    private final NotificationProperties notificationProperties;

    @Override
    public SimpleMailMessage[] transformEvent(BookingNotificationEvent bookingNotificationEvent) {
        return bookingNotificationEvent.getSubscribers().stream()
                .filter(Subscriber::isEmailNotify)
                .map(profileUserDto -> {
                    final SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
                    simpleMailMessage.setTo(profileUserDto.getEmail());
                    simpleMailMessage.setFrom(notificationProperties.getEmail().getAdminEmail());
                    if (bookingNotificationEvent.getDeleteBookingDate() != null) {
                        simpleMailMessage.setSubject(MessageFormat.format("Уведомление. Удалена бронь на переговорку \"{0}\"", bookingNotificationEvent
                                .getRoomName()));
                        simpleMailMessage.setText(MessageUtils.textDelete(bookingNotificationEvent, profileUserDto));
                    } else if (bookingNotificationEvent.getUpdateBookingDate() != null) {
                        simpleMailMessage.setSubject(MessageFormat.format("Уведомление. Изменена бронь на переговорку \"{0}\"", bookingNotificationEvent
                                .getRoomName()));
                        simpleMailMessage.setText(MessageUtils.textUpdate(bookingNotificationEvent, profileUserDto));
                    } else {
                        simpleMailMessage.setSubject(MessageFormat.format("Уведомление. Создана бронь на переговорку \"{0}\"", bookingNotificationEvent
                                .getRoomName()));
                        simpleMailMessage.setText(MessageUtils.textCreate(bookingNotificationEvent, profileUserDto));
                    }
                    return simpleMailMessage;
                })
                .toArray(SimpleMailMessage[]::new);
    }

    @Override
    public SimpleMailMessage transformReminder(BookingNotificationReminder message) {
        final SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(message.getSubscriber().getEmail());
        simpleMailMessage.setFrom(notificationProperties.getEmail().getAdminEmail());
        simpleMailMessage.setSubject(MessageFormat.format("Напоминание. Скоро начнется Ваша бронь на переговорку \"{0}\"", message.getRoomName()));
        simpleMailMessage.setText(MessageUtils.textReminder(message));
        return simpleMailMessage;
    }

}
