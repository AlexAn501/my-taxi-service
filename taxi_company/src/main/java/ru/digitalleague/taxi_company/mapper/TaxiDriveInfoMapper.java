package ru.digitalleague.taxi_company.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import ru.digitalleague.taxi_company.model.TaxiDriverInfo;

@Repository
@Mapper
public interface TaxiDriveInfoMapper {

    @Results(id = "driver", value = {
            @Result(property = "driverId", column = "driver_id"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "level", column = "level"),
            @Result(property = "createDttm", column = "create_dttm"),
            @Result(property = "carId", column = "car_id"),
            @Result(property = "minuteCost", column = "minute_cost"),
            @Result(property = "rating", column = "rating"),
            @Result(property = "isBusy", column = "busy"),
            @Result(property = "cityId", column = "city_id")
    })
    @Select("SELECT driver_id, first_name, last_name, level, create_dttm, model as car_model," +
            " taxi_drive_info.city_id, minute_cost, rating, busy, car_id" +
            " FROM taxi_drive_info" +
            " INNER JOIN car c on c.id = taxi_drive_info.car_id" +
            " INNER JOIN city_queue cq on cq.city_id = taxi_drive_info.city_id" +
            " WHERE name = #{cityName} and model = #{carModel} and busy = false and level = #{level}" +
            " ORDER BY rating DESC limit 1")
    TaxiDriverInfo findDriverByCityAndCarModelAndLevel(String cityName, String carModel, int level);

    @Update("UPDATE taxi_drive_info SET busy = #{busy} WHERE driver_id = #{driverId}")
    void setBusy(long driverId, boolean busy);

    @Update("UPDATE taxi_drive_info SET rating = #{grade} WHERE driver_id = #{driverId}")
    void saveGradeTrip(long driverId, int grade);
}
