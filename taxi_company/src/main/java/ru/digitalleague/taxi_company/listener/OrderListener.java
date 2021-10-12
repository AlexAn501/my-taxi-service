package ru.digitalleague.taxi_company.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.digitalleague.core.model.OrderDetails;
import ru.digitalleague.taxi_company.api.OrderService;
import ru.digitalleague.taxi_company.model.TaxiDriverInfo;
import ru.digitalleague.taxi_company.service.OrderServiceImpl;


@Slf4j
@Component
public class OrderListener implements MessageListener {
//    @Autowired
//    ObjectMapper objectMapper = new ObjectMapper();
//
//    @Autowired
//    OrderService orderService = new OrderServiceImpl();
//
//    private Message message = null;
    @Override
    public void onMessage(Message message) {
        log.info("Received message from rabbitmq " + message);
//        this.message = message;
//        woo();
    }

//    public Message getMessage(){
//        return message;
//    }

//    @SneakyThrows
//    public String woo(){
//        Message message = this.message;
//        String bodyMessage = new String(message.getBody(),"UTF-8");
//
//        OrderDetails orderDetails = objectMapper.readValue(bodyMessage, OrderDetails.class);
//        log.info("orderDetails: " + orderDetails);
//
//        TaxiDriverInfo driver = orderService.findDriver(orderDetails);
//        log.info("driver: " + driver);
//
//        long clientNumber = orderDetails.getClientNumber();
//        long driverId = driver.getDriverId();
//
//        log.info("clientNumber and driverId: " + clientNumber + "  " + driverId);
//        orderService.createOrder(clientNumber, driverId);
//
//        long orderId = orderService.findOrderIdByClientNumberAndDriverId(clientNumber,driverId);
//
//        orderService.setBusy(driverId);
//
//        return String.format("Order id: %d \nClientNumber: %d\nDriver id: %d",orderId, clientNumber, driverId);
//    }
}
