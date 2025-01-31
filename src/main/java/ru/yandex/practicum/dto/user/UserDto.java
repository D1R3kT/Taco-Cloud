package ru.yandex.practicum.dto.user;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Getter
public class UserDto {
    private Long id;
    @NotNull(message = "name can not be empty")
    private String name;
    @Email(message = "email is incorrect")
    @NotNull(message = "email can not be empty")
    private String email;
    @NotNull(message = "password can not be empty")
    private String password;

}
