package ru.digitalleague.taxi_company.api;

import ru.digitalleague.taxi_company.model.TaxiDriverInfo;

@Deprecated
public interface TaxiService {
    TaxiDriverInfo findAvailableDriver(long cityId, String carModel);
}
