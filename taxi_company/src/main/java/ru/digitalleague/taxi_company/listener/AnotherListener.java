package ru.digitalleague.taxi_company.listener;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.digitalleague.taxi_company.controller.TaxiController;

@Slf4j
@Component
public class AnotherListener {

    @Autowired
    TaxiController taxiController;

    @SneakyThrows
    @RabbitListener(queues = "${application.broker.receive-queue}")
    public void throwsMessage(Message message){
       taxiController.sendMessageToService(message);
    }
}
