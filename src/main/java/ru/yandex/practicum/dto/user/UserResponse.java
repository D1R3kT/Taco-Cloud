package ru.yandex.practicum.dto.user;

public record UserResponse(
        Long id,
        String name,
        String email
) {
}
