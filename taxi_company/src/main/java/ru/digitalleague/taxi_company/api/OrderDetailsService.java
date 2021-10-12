package ru.digitalleague.taxi_company.api;

import org.springframework.amqp.core.Message;
import ru.digitalleague.core.model.OrderDetails;

public interface OrderDetailsService {

    long findCityByName(String name);

    ru.digitalleague.core.model.OrderDetails parseJsonToOrderDetails(Message message);
}
