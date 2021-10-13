package ru.digitalleague.taxi_company.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalleague.core.model.OrderDetails;
import ru.digitalleague.taxi_company.api.OrderDetailsService;
import ru.digitalleague.taxi_company.mapper.OrderDetailsMapper;

@Deprecated
@Service
@Slf4j
public class OrderDetailsServiceImpl implements OrderDetailsService {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    OrderDetailsMapper orderDetailsMapper;

    @Override
    public long findCityByName(String name) {
        return orderDetailsMapper.findCityByName(name);
    }

    public OrderDetails parseJsonToOrderDetails(Message message){
        OrderDetails orderDetails = null;
        try {
            String messString = new String(message.getBody(),"UTF-8");
            orderDetails = objectMapper.readValue(messString, OrderDetails.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("orderDetails = " + orderDetails);

        return orderDetails;

    }
}
