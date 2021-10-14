package ru.digitalleague.taxi_company.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import ru.digitalleague.core.model.OrderDetails;
import ru.digitalleague.taxi_company.api.Service;
import ru.digitalleague.taxi_company.mapper.OrderMapper;
import ru.digitalleague.taxi_company.mapper.TaxiDriveInfoMapper;
import ru.digitalleague.taxi_company.model.Order;
import ru.digitalleague.taxi_company.model.TaxiDriverInfo;

import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;

/**
 * Сервис обработки заказов.
 */
@org.springframework.stereotype.Service
@Slf4j
public class ServiceImpl implements Service {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    TaxiDriveInfoMapper taxiDriveInfoMapper;

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void save(Order order) {

        orderMapper.saveOrder(order);
        System.out.println("Order saved");
    }


    @Override
    public void saveEndTimeTrip(OffsetDateTime time, long orderId) {
        orderMapper.saveEndTimeTrip(time, orderId);
        log.debug("Save End Time in OrderServiceImpl");
    }

    @Override
    public void saveStartTripTime(OffsetDateTime time, long orderId) {
        orderMapper.saveStartTripTime(time, orderId);
        log.debug("Save Start Time in OrderServiceImpl");
    }

    @Override
    public OffsetDateTime findStartTimeById(long orderId){
        OffsetDateTime date = orderMapper.findStartTimeById(orderId);
        return date;
    }

    @Override
    public OffsetDateTime findEndTimeById(long orderId){
        OffsetDateTime date = orderMapper.findEndTimeById(orderId);
        return date;
    }

    @Override
    public void setBusyFalse(long orderId){
        long driverId = orderMapper.findDriverIdByOrderId(orderId);
        taxiDriveInfoMapper.setBusyFalse(driverId);
    }

    /**
     * Принимает message от listener
     * @param message
     * @return orderId
     */
    @Override
    public long catchMessageFromController(Message message){
      return init(message);
    }

    /**
     * Выполняет работу по созданию заказа
     * @param message
     * @return orderId
     */
    private long init (Message message){
        OrderDetails orderDetails = getOrderDetailsFromJSON(message);

        TaxiDriverInfo driver = findDriver(orderDetails);
        log.debug("driver: " + driver);

        long orderId = createOrder(orderDetails.getClientNumber(),driver.getDriverId());
        log.debug(String.format("orderInfo: orderId = %d, clientNumber = %d, driverId = %d",
                orderId, orderDetails.getClientNumber(), driver.getDriverId()));

        return orderId;
    }

    /**
     * Создает ордер
     * @param clientNumber идентификатор клиента
     * @param driverId идентификатор водителя
     * @return идентификатор ордера
     */
//    @Transactional(isolation = Isolation.READ_COMMITTED)
    private long createOrder(long clientNumber, long driverId) {
        long orderId = orderMapper.findNextId();
        orderMapper.createNewOrder(orderId, clientNumber, driverId);
        setBusyTrue(driverId);
        return orderId;
    }

    /**
     * Парсит объект Message в OrderDetails
     * @param message JSON Message из Listener
     * @return OrderDetails
     */
    private OrderDetails getOrderDetailsFromJSON(Message message) {
        OrderDetails orderDetails = null;
        try {
            String mess = new String(message.getBody(), StandardCharsets.UTF_8);
            orderDetails = objectMapper.readValue(mess, OrderDetails.class);
        } catch (JsonProcessingException e) {
            log.error("Некорректный JSON объект " + message);
            e.printStackTrace();
        }
        return orderDetails;
    }

    /**
     * Ищет свободного водителя
     * @param orderDetails
     * @return объект taxiDriverInfo
     */
    private TaxiDriverInfo findDriver(OrderDetails orderDetails) {
        TaxiDriverInfo driver = taxiDriveInfoMapper.
                findDriverByCityAndCarModelAndLevel(orderDetails.getCity(), orderDetails.getCarModel(), orderDetails.getLevel());
        return driver;
    }

    private void setBusyTrue(long driverId) {
        taxiDriveInfoMapper.setBusyTrue(driverId);
    }
}
