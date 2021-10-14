package ru.digitalleague.taxi_company.api;

import org.springframework.amqp.core.Message;

import ru.digitalleague.taxi_company.model.Order;

import java.time.OffsetDateTime;

/**
 * Сервис обработки заказов.
 */
public interface OrderService {

    void save(Order order);

    long catchMessageFromController(Message message);

    void saveEndTimeTrip(OffsetDateTime time, long orderId);

    void saveStartTripTime(OffsetDateTime offsetDateTime, long id);

    OffsetDateTime findStartTimeById(long orderId);

    OffsetDateTime findEndTimeById(long orderId);
}
