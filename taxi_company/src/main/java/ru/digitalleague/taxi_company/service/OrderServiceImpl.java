package ru.digitalleague.taxi_company.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalleague.taxi_company.api.OrderService;
import ru.digitalleague.taxi_company.mapper.OrderMapper;
import ru.digitalleague.taxi_company.model.Order;

import java.time.OffsetDateTime;

/**
 * Сервис обработки заказов.
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void save(Order order) {

        orderMapper.saveOrder(order);
        System.out.println("Order saved");
    }

    @Override
    public long getOrderId() {
        return orderMapper.getOrderId();
    }

    @Override
    public void saveEndTimeTrip(OffsetDateTime time, long orderId) {
        orderMapper.saveEndTimeTrip(time, orderId);
        System.out.println("Order update");
    }


}
