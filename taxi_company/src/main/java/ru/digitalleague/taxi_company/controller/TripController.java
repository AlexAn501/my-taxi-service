package ru.digitalleague.taxi_company.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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
import ru.digitalleague.taxi_company.config.ApplicationConfiguration;
import ru.digitalleague.taxi_company.model.Order;

import java.time.OffsetDateTime;

/**
 * Контроллер обрабатывающий начало и конец поездки
 */

@RestController
@Slf4j
public class TripController {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);

    @Autowired
    TaxiService taxiService;

    @Autowired
    OrderDetailsService orderDetailsService;

    @Autowired
    OrderService orderService;

    @Autowired
    CarService carService;


    @PostMapping("/start-trip")
    public ResponseEntity<String> startTrip(@RequestBody Order order) {
        OffsetDateTime currentTime = context.getBean("currentTime", OffsetDateTime.class);
        order.setStartTrip(currentTime);
        orderService.saveStartTripTime(order.getStartTrip(), order.getOrderId());
        log.info("Save start time " + order);
        return new ResponseEntity<>("Saved start time", HttpStatus.OK);
    }


    @PostMapping("/end-trip")
    public String endTrip(@RequestBody Order order) {
        OffsetDateTime currentTime = context.getBean("currentTime", OffsetDateTime.class);
        order.setEndTrip(currentTime);
        orderService.saveEndTimeTrip(order.getStartTrip(), order.getOrderId());
        log.info("Save end time " + order);
//        return new ResponseEntity<>("Saved end time", HttpStatus.OK);
        return "redirect:/trip-complete";
    }

//    /**
//     * Метод обрабатывающий начало поездки
//     * @param message
//     * @return
//     */

//    @PostMapping("/start-trip")
//    public ResponseEntity<String> startTrip (@RequestBody Message message){
////        log.info("TripController /start-trip " + orderDetails);
////      Получили id города из orderDetails (то что пришло от ЦОД)
////        long cityId = orderDetailsService.findCityByName(orderDetails.getCity());
////
////        String model = carService.findModelById(orderDetails.getCarModel());
////        List<TaxiDriverInfo> taxiInfo = taxiService.showAllAvailableDriver(cityId, orderDetails.getCarModel());
////
////        log.info("drivers " + Arrays.asList(taxiInfo.toArray()));
////
////        Order order = new Order();
////        order.setClientNumber(orderDetails.getClientNumber());
////        order.setDriverId(taxiInfo.get(0).getDriverId());
////        order.setStartTrip(OffsetDateTime.now());
////
////        orderService.save(order);
//
//        OrderDetails orderDetails = orderDetailsService.findOrderDetails(message);
//        log.info("orderDetails" + orderDetails);
//
//        return ResponseEntity.ok("Поездка началась");
//    }
//
//
//    @PostMapping("/end-trip")
//    public String endTrip(){
//        long orderId = orderService.getOrderId();
//
//        orderService.saveEndTimeTrip(OffsetDateTime.now(), orderId);
//        return "redirect:/trip-complete";
//    }
}
