package ru.otus.spring.repository;

import ru.otus.spring.domain.Room;
import ru.otus.spring.dto.RoomFilter;

import java.util.List;

/**
 * Кастомный репозиторий для работы с переговорной комнатой.
 *
 * @author MTronina
 */
public interface RoomCustomRepository {

    List<Room> findAllByFilter(RoomFilter filter);
}
