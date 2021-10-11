package ru.digitalleague.taxi_company.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalleague.taxi_company.api.OrderDetailsService;
import ru.digitalleague.taxi_company.mapper.OrderDetailsMapper;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

    @Autowired
    OrderDetailsMapper orderDetailsMapper;

    @Override
    public long findCityByName(String name) {
        return orderDetailsMapper.findCityByName(name);
    }
}
