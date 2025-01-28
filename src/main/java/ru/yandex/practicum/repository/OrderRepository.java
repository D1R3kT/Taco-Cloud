package ru.yandex.practicum.repository;

import ru.yandex.practicum.model.TacoOrder;

public interface OrderRepository {
    TacoOrder save(TacoOrder order);
}
