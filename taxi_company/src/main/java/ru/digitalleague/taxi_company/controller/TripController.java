package ru.digitalleague.taxi_company.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.digitalleague.core.model.OrderDetails;
import ru.digitalleague.taxi_company.api.CarService;
import ru.digitalleague.taxi_company.api.OrderDetailsService;
import ru.digitalleague.taxi_company.api.OrderService;
import ru.digitalleague.taxi_company.api.TaxiService;
import ru.digitalleague.taxi_company.model.Order;
import ru.digitalleague.taxi_company.model.TaxiDriverInfo;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Контроллер обрабатывающий начало и конец поездки
 */

@RestController
@Slf4j
public class TripController {

    @Autowired
    TaxiService taxiService;

    @Autowired
    OrderDetailsService orderDetailsService;

    @Autowired
    OrderService orderService;

    @Autowired
    CarService carService;
    /**
     * Метод обрабатывающий начало поездки
     * @param orderDetails
     * @return
     */

    @PostMapping("/start-trip")
    public ResponseEntity<String> startTrip (@RequestBody OrderDetails orderDetails){
        log.info("TripController /start-trip " + orderDetails);
//      Получили id города из orderDetails (то что пришло от ЦОД)
//        long cityId = orderDetailsService.findCityByName(orderDetails.getCity());
//
//        String model = carService.findModelById(orderDetails.getCarModel());
//        List<TaxiDriverInfo> taxiInfo = taxiService.showAllAvailableDriver(cityId, orderDetails.getCarModel());
//
//        log.info("drivers " + Arrays.asList(taxiInfo.toArray()));
//
//        Order order = new Order();
//        order.setClientNumber(orderDetails.getClientNumber());
//        order.setDriverId(taxiInfo.get(0).getDriverId());
//        order.setStartTrip(OffsetDateTime.now());
//
//        orderService.save(order);


        return ResponseEntity.ok("Поездка началась");
    }


    @PostMapping("/end-trip")
    public String endTrip(){
        long orderId = orderService.getOrderId();

        orderService.saveEndTimeTrip(OffsetDateTime.now(), orderId);
        return "redirect:/trip-complete";
    }
}
