package ru.digitalleague.taxi_company.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import ru.digitalleague.taxi_company.model.TaxiDriverInfo;

import java.util.List;

@Repository
@Mapper
public interface TaxiDriveInfoMapper {

    @Select("SELECT driver_id, last_name, first_name, level, car_id, create_dttm, minute_cost, city_id, rating, busy" +
            "FROM taxi_drive_info WHERE city_id = #{cityId} AND car_id = '#{carId}' AND busy = false ORDER BY rating DESC")
    List<TaxiDriverInfo> showAllAvailableDriver(long cityId, String carModel);
}
