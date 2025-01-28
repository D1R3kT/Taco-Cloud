package ru.yandex.practicum.repository;

import org.springframework.data.repository.CrudRepository;
import ru.yandex.practicum.model.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
    TacoOrder save(TacoOrder order);
}
