package ru.yandex.practicum.mapper;


import ru.yandex.practicum.dto.user.UserDto;
import ru.yandex.practicum.dto.user.UserResponse;
import ru.yandex.practicum.model.User;

public class UserMapper {

    UserResponse toUserResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getEmail());
    }

    User toUser(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
    }


}
