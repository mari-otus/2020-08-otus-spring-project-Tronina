package ru.otus.spring.repository.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.User;

import java.util.Optional;

/**
 * Репозиторий для работы с пользователями.
 *
 * @author MTronina
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

    Page<User> findAll(Pageable pageable);
}
