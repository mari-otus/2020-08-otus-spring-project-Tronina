package ru.otus.spring.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.NotificationProperties;
import ru.otus.spring.model.BookingNotificationEvent;
import ru.otus.spring.model.BookingNotificationReminder;
import ru.otus.spring.model.Subscriber;

/**
 * Сервис трансформации данных в sms-сообщение.
 *
 * @author MTronina
 */
@Service
@RequiredArgsConstructor
public class TransformSmsMessageService implements TransformMessageService<MessageCreator> {

    private final NotificationProperties notificationProperties;

    @Override
    public MessageCreator[] transformEvent(BookingNotificationEvent bookingNotificationEvent) {
        Twilio.init(notificationProperties.getSms().getTwilioAccountSid(), notificationProperties.getSms().getTwilioAuthToken());

        return bookingNotificationEvent.getSubscribers().stream()
                .filter(Subscriber::isPhoneNotify)
                .map(profileUserDto -> {
                    MessageCreator message = new MessageCreator(new PhoneNumber(profileUserDto.getMobilePhone()),
                            new PhoneNumber(notificationProperties.getSms().getTwilioPhoneNumber()),
                            "");
                    if (bookingNotificationEvent.getDeleteBookingDate() != null) {
                        message.setBody(MessageUtils.textDelete(bookingNotificationEvent, profileUserDto));
                    } else if (bookingNotificationEvent.getUpdateBookingDate() != null) {
                        message.setBody(MessageUtils.textUpdate(bookingNotificationEvent, profileUserDto));
                    } else {
                        message.setBody(MessageUtils.textCreate(bookingNotificationEvent, profileUserDto));
                    }
                    return message;
                })
                .toArray(MessageCreator[]::new);
    }

    @Override
    public MessageCreator transformReminder(BookingNotificationReminder message) {
        MessageCreator messageCreator = new MessageCreator(new PhoneNumber(message.getSubscriber().getMobilePhone()),
                new PhoneNumber(notificationProperties.getSms().getTwilioPhoneNumber()),
                "");

        messageCreator.setBody(MessageUtils.textReminder(message));
        return messageCreator;
    }

}
