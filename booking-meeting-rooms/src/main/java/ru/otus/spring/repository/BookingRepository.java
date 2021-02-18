package ru.otus.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.Booking;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Репозиторий для работы с бронями переговорной комнаты.
 *
 * @author MTronina
 */
public interface BookingRepository extends JpaRepository<Booking, Long>, BookingCustomRepository {

    List<Booking> findAllByRoomIdAndDeleteDateIsNull(Long roomId);

    Optional<Booking> findByIdEqualsAndAndLoginEquals(Long id, String login);

    List<Booking> findAllByDeleteDateIsNullAndBeginDateBetween(LocalDateTime dateStart, LocalDateTime dateEnd);

}
