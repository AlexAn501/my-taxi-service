package ru.digitalleague.taxi_company.api;

import ru.digitalleague.core.model.OrderDetails;
import ru.digitalleague.taxi_company.model.TaxiDriverInfo;

import java.util.List;

public interface TaxiService {
    List<TaxiDriverInfo> showAllAvailableDriver(long cityId, String carModel);
//List<TaxiDriverInfo> showAllAvailableDriver(OrderDetails orderDetails);

}
