package ru.digitalleague.taxi_company.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import ru.digitalleague.taxi_company.model.Order;

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
    void saveStartTripTime(OffsetDateTime time, long orderId);

    @Insert("INSERT INTO orders (order_id, client_number, driver_id) VALUES (#{orderId}, #{clientNumber}, #{driverId})")
    void createNewOrder(long orderId, long clientNumber, long driverId);

    @Select("SELECT nextval('order_seq');")
    long findNextId();

    @Select("SELECT start_trip FROM orders WHERE order_id = #{orderId}")
    OffsetDateTime findStartTimeById(long orderId);

    @Select("SELECT end_trip FROM orders WHERE order_id = #{orderId}")
    OffsetDateTime findEndTimeById(long orderId);

    @Select("SELECT driver_id FROM orders WHERE order_id = #{orderId}")
    long findDriverIdByOrderId(long orderId);

    @Select("SELECT minute_cost FROM taxi_drive_info WHERE driver_id = #{driverId}")
    int findMinuteCostByDriverId(long driverId);
}