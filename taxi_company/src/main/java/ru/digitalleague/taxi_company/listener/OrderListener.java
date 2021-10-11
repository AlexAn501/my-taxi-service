package ru.digitalleague.taxi_company.listener;

import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import ru.digitalleague.taxi_company.service.TaxiServiceImpl;

@Slf4j
public class OrderListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        log.info("Received message from rabbitmq " + message);
    }
}
