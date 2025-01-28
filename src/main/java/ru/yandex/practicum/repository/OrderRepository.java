package ru.yandex.practicum.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.yandex.practicum.model.TacoOrder;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
    TacoOrder save(TacoOrder order);

    List<TacoOrder> findByDeliveryZip(String deliveryZip);

    List<TacoOrder> readOrdersByDeliveryZipAndPlacedBetween(String deliveryZip, Date from, Date to);

    @Query("Order o where o.deliveryCity='Seqttle'")
    List<TacoOrder> readOrdersDeliveredInSeattle();

}
