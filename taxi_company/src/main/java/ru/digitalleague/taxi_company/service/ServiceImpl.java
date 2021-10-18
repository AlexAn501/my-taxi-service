package ru.digitalleague.taxi_company.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import ru.digitalleague.core.model.OrderDetails;
import ru.digitalleague.taxi_company.api.Service;
import ru.digitalleague.taxi_company.mapper.OrderMapper;
import ru.digitalleague.taxi_company.mapper.OrderTotalMapper;
import ru.digitalleague.taxi_company.mapper.GradeTaxiDriverMapper;
import ru.digitalleague.taxi_company.mapper.TaxiDriveInfoMapper;
import ru.digitalleague.taxi_company.model.Order;
import ru.digitalleague.taxi_company.model.TaxiDriverInfo;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
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

    @Autowired
    OrderTotalMapper orderTotalMapper;

    @Autowired
    GradeTaxiDriverMapper gradeTaxiDriverMapper;

    @Override
    public void save(Order order) {

        orderMapper.saveOrder(order);
        System.out.println("Order saved");
    }

    /**
     * Сохраняет время окончания поездки
     * @param orderId Время, Идентификатор поездки
     */
    @Override
    public void saveEndTimeTrip(OffsetDateTime time, long orderId) {
        orderMapper.saveEndTimeTrip(time, orderId);
        log.debug("Save End Time in OrderServiceImpl");
    }

    /**
     * Сохраняет время начала поездки
     * @param orderId Время, Идентификатор поездки
     */
    @Override
    public void saveStartTripTime(OffsetDateTime time, long orderId) {
        orderMapper.saveStartTripTime(time, orderId);
        log.debug("Save Start Time in OrderServiceImpl");
    }

    /**
     * Находит время начала поездки
     * @param orderId Идентификатор поездки
     * @return Время начала поездки
     */
    @Override
    public OffsetDateTime findStartTimeById(long orderId){
        OffsetDateTime date = orderMapper.findStartTimeById(orderId);
        return date;
    }

    /**
     * Находит время окончания поездки
     * @param orderId Идентификатор поездки
     * @return Время окончания поездки
     */
    @Override
    public OffsetDateTime findEndTimeById(long orderId){
        OffsetDateTime date = orderMapper.findEndTimeById(orderId);
        return date;
    }

    /**
     * Устанавливает флаг занятости
     * @param orderId Идентификатор поездки
     * @param busy флаг занятости
     */
    @Override
    public void setBusy(long orderId, boolean busy){
        long driverId = orderMapper.findDriverIdByOrderId(orderId);
        taxiDriveInfoMapper.setBusy(driverId, busy);
    }

    /**
     * Подсчет стоимости поездки
     * @param orderId Идентификатор поездки
     * @return общую стоимость поездки
     */
    @Override
    public int calculationTripCost(long orderId) {
        long timeTripInMinutes = calculationTimeTrip(orderId);
        int minuteCost = getMinuteCost(orderId);
        int costTrip = (int)timeTripInMinutes * minuteCost;
        log.debug("costTrip: " + costTrip);
        return costTrip;
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
     * Сохраняет стоимость поездки
     * @param orderId Идентификатор поездки
     * @param tripAmount Стоимость поездки
     */
    @Override
    public void saveTripAmount(long orderId, int tripAmount) {
        orderTotalMapper.saveSumOrderByOrderId(orderId, tripAmount);
    }

    /**
     * Сохраняет оценку поездки
     * @param orderId Идентификатор поездки
     * @param grade Оценка
     */
    @Override
    public void saveGradeTrip(long orderId, int grade) {
        long driverId = orderMapper.findDriverIdByOrderId(orderId);
        gradeTaxiDriverMapper.saveGradeTrip(orderId, driverId, grade);
        calculationAndUpdateAvgRatingDriver(driverId);
    }

    /**
     * Пересчитывает и сохраняет средний рейтинг водителя
     * @param driverId Идентификатор водителя
     */
    private void calculationAndUpdateAvgRatingDriver(long driverId){
        //Округление до целого
        int gradeAvg = Math.round(gradeTaxiDriverMapper.findAverageGrade(driverId));
        taxiDriveInfoMapper.saveAvgRating(driverId, gradeAvg);
    }

    /**
     * Получение стоимости одной минуты поездки
     * @param orderId Идентификатор поездки
     * @return  Стоимость поездки
     */
    private int getMinuteCost(long orderId){
        long driverId = orderMapper.findDriverIdByOrderId(orderId);
        int minuteCost = orderMapper.findMinuteCostByDriverId(driverId);
        log.debug("minuteCost: " + minuteCost);
        return minuteCost;
    }

    /**
     * Подсчет времени поездки
     * @param orderId Идентификатор поездки
     * @return Время поездки в минутах
     */
    private long calculationTimeTrip(long orderId){
        OffsetDateTime before = findStartTimeById(orderId);
        OffsetDateTime after = findEndTimeById(orderId);
        Duration duration = Duration.between(before,after);
        long timeTripInMinutes = duration.toMinutes();
        log.debug("timeTripInMinutes: " + timeTripInMinutes);
        return timeTripInMinutes;
    }

    /**
     * Выполняет работу по созданию заказа
     * @param message сообщение из listener
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
     * @param orderDetails Детали заказа
     * @return объект taxiDriverInfo
     */
    private TaxiDriverInfo findDriver(OrderDetails orderDetails) {
        TaxiDriverInfo driver = taxiDriveInfoMapper.
                findDriverByCityAndCarModelAndLevel(orderDetails.getCity(), orderDetails.getCarModel(), orderDetails.getLevel());
        return driver;
    }
}
