package ru.yandex.practicum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.practicum.model.TacoOrder;

public interface OrderRepository extends JpaRepository<TacoOrder, Long> {
    TacoOrder save(TacoOrder order);
}
