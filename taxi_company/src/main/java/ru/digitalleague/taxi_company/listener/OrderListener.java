package ru.digitalleague.taxi_company.listener;

import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Deprecated
@Slf4j
@Component
public class OrderListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        log.info("Received message from rabbitmq " + message);
    }
}
