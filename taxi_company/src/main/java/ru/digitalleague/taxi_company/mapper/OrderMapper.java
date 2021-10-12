package ru.digitalleague.taxi_company.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import ru.digitalleague.taxi_company.model.Order;
import ru.digitalleague.taxi_company.model.TaxiDriverInfo;

import java.time.OffsetDateTime;

@Repository
@Mapper
public interface OrderMapper {

    /**
     * Сохранить заказ.
     *
     * @param order информация о заказе.
     */
    @Insert(" insert into orders (order_id, client_number, driver_id, start_trip, end_trip)" +
            " values(#{orderId}, #{clientNumber}, #{driverId}, #{startTrip}, #{endTrip})")
    void saveOrder(Order order);

    @Update("UPDATE orders SET end_trip = #{time} WHERE order_id = #{orderId}")
    void saveEndTimeTrip(OffsetDateTime time, long orderId);

    @Update("UPDATE orders SET start_trip = #{time} WHERE order_id = #{orderId}")
    void saveStartTripTime(OffsetDateTime offsetDateTime, long orderId);

    @Select("SELECT driver_id, last_name, first_name, level, car_id, create_dttm, " +
            "minute_cost, city_id, rating, busy FROM orders WHERE car_id = #{carId} ORDER BY rating LIMIT 1")
    TaxiDriverInfo findDriverByCarId(long carId);

    @Insert("INSERT INTO orders (client_number, driver_id) values (#{clientNumber}, #{driverId})")
    void createNewOrder(long clientNumber, long driverId);

    @Select("SELECT order_id FROM orders WHERE client_number = #{clientNumber} AND driver_id = #{driverId}")
    long findOrderIdByClientNumberAndDriverId(long clientNumber, long driverId);

    @Update("UPDATE INTO taxi_drive_info SET busy = true WHERE driver_id = #{driverId}")
    void setBusy(long driverId);

        @Select("SELECT driver_id, first_name, last_name, level, create_dttm, model as car_model," +
                " taxi_drive_info.city_id, minute_cost, rating, busy" +
                " FROM taxi_drive_info" +
                " INNER JOIN car c on c.id = taxi_drive_info.car_id" +
            " INNER JOIN city_queue cq on cq.city_id = taxi_drive_info.city_id" +
            " WHERE name = #{cityName} and model = #{carModel} and busy = false and level = #{level}" +
            " limit 1")
    TaxiDriverInfo findDriver(String cityName, String carModel, int level);
}
//@Results(id = "driver", value = {
//            @Result(property = "driverId", column = "driver_id"),
//            @Result(property = "lastName", column = "last_name"),
//            @Result(property = "firstName", column = "first_name"),
//            @Result(property = "level", column = "level"),
//            @Result(property = "createDttm", column = "create_dttm"),
//            @Result(property = "carModel", column = "car_model")
//    })
//    @Select("select driver_id, first_name, last_name, level, create_dttm, model as car_model from taxi_drive_info" +
//            "inner join car c on c.id = taxi_drive_info.car_id" +
//            "inner join city_queue cq on cq.city_id = taxi_drive_info.city_id" +
//            "where name = #{cityName} and model = #{carModel} and busy = false and level = #{level}" +
//            "limit 1")
//    TaxiDriverInfoModel getAvailableDriverByCityCarAndLvl(String cityName, CarModel carModel, int level);