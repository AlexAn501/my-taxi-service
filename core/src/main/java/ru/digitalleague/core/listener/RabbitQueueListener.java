package ru.digitalleague.core.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
@Slf4j
public class RabbitQueueListener {

    /**
     * Получаем информацию о заказе.
     */
    @RabbitListener(queues = "order")
    public void processRabbitMessage(String message) {
        log.info("Received message from rabbitmq " + message);
    }
}
