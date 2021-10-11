package ru.digitalleague.core.api;

import ru.digitalleague.core.model.TaxiDriverInfoModel;

public interface TaxiInfoService {

    int insert(TaxiDriverInfoModel record);

    TaxiDriverInfoModel selectByPrimaryKey(Long driverId);

    int updateByPrimaryKey(TaxiDriverInfoModel record);

    int getByIdAndUpdateLevel(Long driverId);

    boolean twoSelectByIdWithSleep();
}
