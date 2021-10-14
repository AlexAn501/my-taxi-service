package ru.digitalleague.taxi_company.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.digitalleague.core.model.OrderDetails;
import ru.digitalleague.taxi_company.api.OrderService;
import ru.digitalleague.taxi_company.model.Order;

import java.time.OffsetDateTime;

/**
 * Контроллер получающий информацию о поездке.
 */
@RestController
@Slf4j
public class TaxiController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private AmqpTemplate amqpTemplate;

    /**
     * Метод устанавливает время начала поездки
     * @param order
     * */
    @PostMapping("/trip-start")
    @ApiOperation(value = "Контроллер для установки времени начала поездки")
    public ResponseEntity<String> startTrip(@RequestBody Order order){
        OffsetDateTime currentTime = context.getBean("currentTime", OffsetDateTime.class);
        orderService.saveStartTripTime(currentTime,order.getOrderId());
        log.warn("Save start time " + orderService.findStartTimeById(order.getOrderId()));
        return ResponseEntity.ok("Start trip time: " + orderService.findStartTimeById(order.getOrderId()));
    }

    /**
     * Метод устанавливает время окончания поездки
     * @param order
     * */
    @PostMapping("/trip-complete")
    @ApiOperation(value = "Контроллер для установки времени окончания поездки")
    public ResponseEntity<String> endTrip(@RequestBody Order order){
        OffsetDateTime currentTime = context.getBean("currentTime", OffsetDateTime.class);
        orderService.saveEndTimeTrip(currentTime,order.getOrderId());
        log.warn("Save end time " + orderService.findEndTimeById(order.getOrderId()));

        amqpTemplate.convertAndSend("trip-result", "Поездка завершена. Номер поездки: = " + order.getOrderId());

        return ResponseEntity.ok("Услуга оказана");
    }

    public void sendMessageToService(Message message){
        orderService.catchMessageFromController(message);
//        OrderDetails orderDetails = orderService.getOrderDetailsFromJSON(message);
//
//        TaxiDriverInfo driver = orderService.findDriver(orderDetails);
//        log.info("driver: " + driver);
//
//        long clientNumber = orderDetails.getClientNumber();
//        long driverId = driver.getDriverId();
//
//        log.info("clientNumber and driverId: " + clientNumber + "  " + driverId);
//        long orderId = orderService.createOrder(clientNumber, driverId);
//        log.info("orderId = " + orderId);

//        return String.format("Order id: %d \nClientNumber: %d\nDriver id: %d",orderId, clientNumber, driverId);
    }
}


//    @Autowired
//    private AmqpTemplate amqpTemplate;
//
//    /**
//     * Метод получает инфо о завершении поездки.
//     * @param message
//     * */
//    @PostMapping("/trip-complete")
//    public ResponseEntity<String> completeTrip(@RequestBody String message) {
//        System.out.println("Trip is finished");
//
//        amqpTemplate.convertAndSend("trip-result", message);
//
//        return ResponseEntity.ok("Услуга оказана");
//    }
