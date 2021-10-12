package ru.digitalleague.taxi_company.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.digitalleague.core.model.OrderDetails;
import ru.digitalleague.taxi_company.api.OrderService;
import ru.digitalleague.taxi_company.model.TaxiDriverInfo;
import ru.digitalleague.taxi_company.service.OrderServiceImpl;

@Slf4j
@Component
public class AnotherListener {
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    OrderService orderService;


    @SneakyThrows
    @RabbitListener(queues = "${application.broker.receive-queue}")
    public String woo(String message){

        OrderDetails orderDetails = objectMapper.readValue(message, OrderDetails.class);
        log.info("orderDetails: " + orderDetails);

        TaxiDriverInfo driver = orderService.findDriver(orderDetails);
        log.info("driver: " + driver);

        long clientNumber = orderDetails.getClientNumber();
        long driverId = driver.getDriverId();

        log.info("clientNumber and driverId: " + clientNumber + "  " + driverId);
        orderService.createOrder(clientNumber, driverId);

        long orderId = orderService.findOrderIdByClientNumberAndDriverId(clientNumber,driverId);

        orderService.setBusy(driverId);

        return String.format("Order id: %d \nClientNumber: %d\nDriver id: %d",orderId, clientNumber, driverId);
    }
}
