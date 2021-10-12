package ru.digitalleague.taxi_company.api;

import ru.digitalleague.core.model.OrderDetails;

import ru.digitalleague.taxi_company.model.Order;
import ru.digitalleague.taxi_company.model.TaxiDriverInfo;

import java.time.OffsetDateTime;

/**
 * Сервис обработки заказов.
 * */
public interface OrderService {

    void save(Order order);

    void saveEndTimeTrip(OffsetDateTime time, long orderId);

    void saveStartTripTime(OffsetDateTime offsetDateTime, long id);

    TaxiDriverInfo findDriver(OrderDetails orderDetails);

    void createOrder(long clientNumber, long driverId);

    long findOrderIdByClientNumberAndDriverId(long clientNumber, long driverId);

    void setBusy(long driverId);
}
