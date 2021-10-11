package ru.digitalleague.taxi_company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalleague.taxi_company.api.TaxiService;
import ru.digitalleague.taxi_company.mapper.TaxiDriveInfoMapper;
import ru.digitalleague.taxi_company.model.TaxiDriverInfo;

import java.util.List;

@Service
public class TaxiServiceImpl implements TaxiService {

    @Autowired
    TaxiDriveInfoMapper taxiDriveInfoMapper;

    @Override
    public List<TaxiDriverInfo> showAllAvailableDriver(long cityId, String carModel) {
        List<TaxiDriverInfo> drivers = taxiDriveInfoMapper.showAllAvailableDriver(cityId, carModel);
        return drivers;
    }
}
