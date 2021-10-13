package ru.digitalleague.taxi_company.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Deprecated
@Mapper
@Repository
public interface CarMapper {

    @Select("SELECT id FROM car WHERE model = #{model}")
    long findIdByModel(String model);
}
