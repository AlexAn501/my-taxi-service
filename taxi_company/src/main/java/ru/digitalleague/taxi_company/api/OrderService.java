package ru.digitalleague.taxi_company.api;

import ru.digitalleague.taxi_company.model.Order;

import java.time.OffsetDateTime;

/**
 * Сервис обработки заказов.
 * */
public interface OrderService {

    void save(Order order);

    long getOrderId();

    void saveEndTimeTrip(OffsetDateTime time, long orderId);
}
