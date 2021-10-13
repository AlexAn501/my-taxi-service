package ru.digitalleague.taxi_company.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.digitalleague.core.model.OrderDetails;
import ru.digitalleague.taxi_company.api.OrderService;
import ru.digitalleague.taxi_company.mapper.CarMapper;
import ru.digitalleague.taxi_company.mapper.OrderMapper;
import ru.digitalleague.taxi_company.mapper.TaxiDriveInfoMapper;
import ru.digitalleague.taxi_company.model.Order;
import ru.digitalleague.taxi_company.model.TaxiDriverInfo;

import java.time.OffsetDateTime;

/**
 * Сервис обработки заказов.
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;
//
//    @Autowired
//    CarMapper carMapper;

    @Autowired
    TaxiDriveInfoMapper taxiDriveInfoMapper;

    @Override
    public void save(Order order) {

        orderMapper.saveOrder(order);
        System.out.println("Order saved");
    }

    @Override
    public void saveEndTimeTrip(OffsetDateTime time, long orderId) {
        orderMapper.saveEndTimeTrip(time, orderId);
        log.info("Save End Time in OrderServiceImpl");
    }

    @Override
    public void saveStartTripTime(OffsetDateTime time, long id) {
        orderMapper.saveStartTripTime(time, id);
        log.info("Save Start Time in OrderServiceImpl");
    }

    @Override
    public TaxiDriverInfo findDriver(OrderDetails orderDetails) {
        TaxiDriverInfo driver = taxiDriveInfoMapper.
                findDriverByCityAndCarModelAndLevel(orderDetails.getCity(),orderDetails.getCarModel(),orderDetails.getLevel());
        return driver;
    }

    @Override
//    @Transactional(isolation = Isolation.READ_COMMITTED)
    public long createOrder(long clientNumber, long driverId) {
        long orderId = orderMapper.findNextId();
        orderMapper.createNewOrder(orderId,clientNumber,driverId);
        return orderId;
    }

//    @Override
//    public long findOrderIdByClientNumberAndDriverId(long clientNumber, long driverId) {
//        return orderMapper.findOrderIdByClientNumberAndDriverId(clientNumber, driverId);
//    }

    @Override
    public void setBusy(long driverId) {
        orderMapper.setBusy(driverId);
    }


}
