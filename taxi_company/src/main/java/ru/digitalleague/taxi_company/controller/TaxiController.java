package ru.digitalleague.taxi_company.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.digitalleague.taxi_company.api.Service;
import ru.digitalleague.taxi_company.model.Order;

import java.time.OffsetDateTime;

/**
 * Контроллер получающий информацию о поездке.
 */
@RestController
@Slf4j
public class TaxiController {

    @Autowired
    private Service service;

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
        service.saveStartTripTime(currentTime,order.getOrderId());
        //  Ищет Id водителя по Id заказа
        service.setBusy(order.getOrderId(), true);
        log.debug("Save start time " + service.findStartTimeById(order.getOrderId()));
        return ResponseEntity.ok("Start trip time: " + service.findStartTimeById(order.getOrderId()));
    }

    /**
     * Метод устанавливает время окончания поездки
     * @param order
     * */
    @PostMapping("/trip-complete")
    @ApiOperation(value = "Контроллер для установки времени окончания поездки")
    public ResponseEntity<String> endTrip(@RequestBody Order order){
        OffsetDateTime currentTime = context.getBean("currentTime", OffsetDateTime.class);
        service.saveEndTimeTrip(currentTime,order.getOrderId());
        log.debug("Save end time " + service.findEndTimeById(order.getOrderId()));
        //  Ищет Id водителя по Id заказа
        service.setBusy(order.getOrderId(),false);

        int costTrip = service.calculationTripCost(order.getOrderId());
        service.saveTripAmount(order.getOrderId(), costTrip);

        amqpTemplate.convertAndSend("trip-result", "Поездка завершена. " +
                "Номер поездки: = " + order.getOrderId() +
                " Цена поездки: = " + costTrip);

        return ResponseEntity.ok("Услуга оказана");
    }

    /**
     * Метод реализует оценку поездки
     * @param orderId Идентификатор поездки
     * @param grade оценка от 1 до 5
     * @return
     */

    @GetMapping("/grade")
    @ApiOperation(value = "Контроллер оценки поездки")
    public ResponseEntity<String> gradeTrip(@RequestHeader("order_Id") long orderId,
                                            @RequestHeader("grade") int grade){
        if(grade < 1 || grade > 5) {
            return new ResponseEntity<>("Некорректная оценка. От 1 до 5.", HttpStatus.BAD_REQUEST);
        }
        service.saveGradeTrip(orderId, grade);
        return new ResponseEntity<>("Спасибо за отзыв", HttpStatus.OK);
    }

    /**
     * Делегирует создание заказа в сервис
     * Передает в ЦОЗ Идентификатор заказа
     * @param message сообщение из Listener
     */

    public void sendMessageToService(Message message){
        long orderId = service.catchMessageFromController(message);
//        amqpTemplate.convertAndSend("trip-result","Идентификатор заказа: " + orderId);
        log.debug("В ЦОЗ отправлен идентификатор поездки");
    }
}
