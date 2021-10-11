package ru.digitalleague.taxi_company.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import ru.digitalleague.taxi_company.model.TaxiDriverInfo;

import java.util.List;

@Repository
@Mapper
public interface TaxiDriveInfoMapper {

    @Results(id = "drivers", value = {
            @Result(property = "driverId", column = "driver_id"),
            @Result(property = "lastName", column = "last_name"),
            @Result(property = "firstName", column = "first_name"),
            @Result(property = "level", column = "level"),
            @Result(property = "carId", column = "car_id"),
            @Result(property = "createDttm", column = "create_dttm"),
            @Result(property = "minuteCost", column = "minute_cost"),
            @Result(property = "cityId", column = "city_id"),
            @Result(property = "rating", column = "rating"),
            @Result(property = "isBusy", column = "busy")
    })

    @Select("SELECT driver_id, last_name, first_name, level, car_id, create_dttm, minute_cost, city_id, rating, busy" +
            "FROM taxi_drive_info WHERE city_id = #{cityId} AND car_id = '#{carId}' AND busy = false ORDER BY rating DESC")
    List<TaxiDriverInfo> showAllAvailableDriver(long cityId, String carModel);
}
