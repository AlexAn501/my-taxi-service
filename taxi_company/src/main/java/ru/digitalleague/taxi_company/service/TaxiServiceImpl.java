package ru.digitalleague.taxi_company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalleague.taxi_company.api.TaxiService;
import ru.digitalleague.taxi_company.mapper.TaxiDriveInfoMapper;
import ru.digitalleague.taxi_company.model.TaxiDriverInfo;

@Service
public class TaxiServiceImpl implements TaxiService {

    @Autowired
    TaxiDriveInfoMapper taxiDriveInfoMapper;

    @Override
    public TaxiDriverInfo findAvailableDriver(long cityId, String carModel) {
        TaxiDriverInfo driver = taxiDriveInfoMapper.findAvailableDriver(cityId, carModel);
        return driver;
    }
}
