package ru.yandex.practicum.service;

import ru.yandex.practicum.dto.user.UserDto;
import ru.yandex.practicum.dto.user.UserResponse;

public interface UserService {
    UserResponse get(Long id);
    UserResponse create(UserDto request);
    UserResponse patch(Long id, UserDto request);
    UserResponse delete(Long id);
}
