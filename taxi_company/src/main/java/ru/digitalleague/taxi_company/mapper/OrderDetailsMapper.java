package ru.digitalleague.taxi_company.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrderDetailsMapper {

    @Select("SELECT city_id FROM city_queue WHERE name = #{city}")
    long findCityByName(String name);
}
