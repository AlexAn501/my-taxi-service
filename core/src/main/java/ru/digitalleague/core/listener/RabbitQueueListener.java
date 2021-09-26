package ru.digitalleague.core.listener;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@EnableRabbit
@Component
public class RabbitQueueListener {
    /**
     * Слушает очередь order Bean myQueue1
     * @param message
     */
    @RabbitListener(queues = "order")
    public void orderListener(String message){
        System.out.println("message = " + message);
    }
}
