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

    /**
     * Обновляет время окончания поездки
     * @param time время окончания поездки
     * @param orderId Идентификатор поездки
     */
    @Update("UPDATE orders SET end_trip = #{time} WHERE order_id = #{orderId}")
    void saveEndTimeTrip(OffsetDateTime time, long orderId);

    /**
     * Обновляет время начала поездки
     * @param time время начала поездки
     * @param orderId Идентификатор поездки
     */
    @Update("UPDATE orders SET start_trip = #{time} WHERE order_id = #{orderId}")
    void saveStartTripTime(OffsetDateTime time, long orderId);

    /**
     * Создает новую строку в таблице Order
     * @param orderId Идентификатор поездки
     * @param clientNumber Идентификатор поездки
     * @param driverId Идентификатор водителя
     */
    @Insert("INSERT INTO orders (order_id, client_number, driver_id) VALUES (#{orderId}, #{clientNumber}, #{driverId})")
    void createNewOrder(long orderId, long clientNumber, long driverId);

    /**
     * Находит следующий номер идентификатора для поездки
     * @return следующий номер идентификатора
     */
    @Select("SELECT nextval('order_seq');")
    long findNextId();

    /**
     * Находит время начала поездки
     * @param orderId Идентификатор поездки
     * @return Время начала поездки
     */
    @Select("SELECT start_trip FROM orders WHERE order_id = #{orderId}")
    OffsetDateTime findStartTimeById(long orderId);

    /**
     * Находит время окончания поездки
     * @param orderId Идентификатор поездки
     * @return Время окончания поездки
     */
    @Select("SELECT end_trip FROM orders WHERE order_id = #{orderId}")
    OffsetDateTime findEndTimeById(long orderId);

    /**
     * Находит идентификатор водителя
     * @param orderId Идентификатор поездки
     * @return Идентификатор водителя
     */
    @Select("SELECT driver_id FROM orders WHERE order_id = #{orderId}")
    long findDriverIdByOrderId(long orderId);

    /**
     * Находит стоимость одной минуты поездки водителя
     * @param driverId Идентификатор водителя
     * @return стоимость одной минуты
     */
    @Select("SELECT minute_cost FROM taxi_drive_info WHERE driver_id = #{driverId}")
    int findMinuteCostByDriverId(long driverId);
}