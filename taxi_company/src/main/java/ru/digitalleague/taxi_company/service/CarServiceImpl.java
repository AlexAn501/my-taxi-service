package ru.digitalleague.taxi_company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalleague.taxi_company.api.CarService;
import ru.digitalleague.taxi_company.mapper.CarMapper;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    CarMapper carMapper;

    @Override
    public long findIdByModel(String model) {
        return carMapper.findIdByModel(model);
    }
}
