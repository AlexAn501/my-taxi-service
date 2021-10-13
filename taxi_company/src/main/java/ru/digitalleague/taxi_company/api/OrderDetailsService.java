package ru.digitalleague.taxi_company.api;

import org.springframework.amqp.core.Message;
import ru.digitalleague.core.model.OrderDetails;

@Deprecated
public interface OrderDetailsService {

    long findCityByName(String name);

    OrderDetails parseJsonToOrderDetails(Message message);
}
