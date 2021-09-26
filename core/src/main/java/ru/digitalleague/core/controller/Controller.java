package ru.digitalleague.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.digitalleague.core.model.OrderDetails;

@RestController
public class Controller {

    @Autowired
    RabbitTemplate template;

    /**
     * Принимает post-запрос, добавляет объект orderDetails в очередь order (myQueue1)
     * @param orderDetails
     */
    @PostMapping("/orders")
    public void addOrder(@RequestBody OrderDetails orderDetails) {

        System.out.println("orderDetails.getCarModel() = " + orderDetails.getCarModel());
        System.out.println("orderDetails.getLevel() = " + orderDetails.getLevel());
        System.out.println("orderDetails.getClientNumber() = " + orderDetails.getClientNumber());

        ObjectMapper objectMapper = new ObjectMapper();
        String order = null;
        try {
            order = objectMapper.writeValueAsString(orderDetails);
            template.convertAndSend("order",order);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println("order = " + order);

    }
}

//В проект добавлены:
//1. Несколько моделей.
//2. Конфигурация для работы с RabbitMQ.
//3. Маппер для работы с базой данных.
//4. Миграция
//Задание:
//1. Создать контроллер, который будет получать
// запрос на оказание услуги перевозки.
//2. В контроллер должен передаваться JSON обьект,
// данные из которого будут вставлены в обьект OrderDetails.
//3. Информация о заказе должна быть передана в брокер сообщений.
//4. Должен быть реализован слушатель очереди "order",
// который получит информацию о заказе из брокера сообщений.