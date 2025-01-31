package ru.yandex.practicum.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import ru.yandex.practicum.model.enums.Type;

@Data

public class Ingredient {
    @Id
    private final String id;
    private final String name;
    private final Type type;


}
