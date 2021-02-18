package ru.otus.spring.validators;

import org.hibernate.validator.internal.engine.constraintvalidation.CrossParameterConstraintValidatorContextImpl;
import org.springframework.util.CollectionUtils;
import ru.otus.spring.dto.BookingRequestDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;
import java.util.List;

/**
 * Проверяет даты бронирования на валидность.
 *
 * @author MTronina
 */
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class BookingValidator implements
        ConstraintValidator<BookingConstraint, Object[]> {

    @Override
    public void initialize(BookingConstraint constraintAnnotation) {
    }

    /**
     * Проверяет что в теле запроса booking даты начала и конца бронирования заданы правильно
     * (дата начала строго меньше даты конца бронирования).
     *
     * @param value   список параметров метода
     * @param context контекст проверки
     * @return true - проверка пройдена успешно, иначе false
     */
    @Override
    public boolean isValid(Object[] value, ConstraintValidatorContext context) {
        List<String> parameters = ((CrossParameterConstraintValidatorContextImpl) context).getMethodParameterNames();

        if (CollectionUtils.isEmpty(parameters)) {
            return true;
        }

        for (int i=0; i<value.length; i++) {
            if (value[i] instanceof BookingRequestDto) {
                BookingRequestDto bookingRequestDto = (BookingRequestDto) value[i];
                if (bookingRequestDto.getBeginDate() != null && bookingRequestDto.getEndDate() != null) {
                    if (bookingRequestDto.getBeginDate().compareTo(bookingRequestDto.getEndDate()) >= 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
