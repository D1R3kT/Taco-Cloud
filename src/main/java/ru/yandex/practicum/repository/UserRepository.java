package ru.yandex.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.dto.user.UserResponse;
import ru.yandex.practicum.model.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
