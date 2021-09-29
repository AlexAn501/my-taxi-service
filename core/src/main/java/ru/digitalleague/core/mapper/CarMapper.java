package ru.digitalleague.core.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import ru.digitalleague.core.model.CarModel;

import java.util.List;

@Repository
@Mapper
public interface CarMapper {

    @Select("select count(1) from car")
    int getCount();

    @Results(id = "car_id", value = {
            @Result(property = "id",column = "id"),
            @Result(property = "carModel", column = "model"),
            @Result(property = "createDttm", column = "createdtmm")
    })
    @Select("SELECT id, model, createdtmm FROM  car")
    List<CarModel> getAllCar();

    int insert(CarModel record);

    CarModel selectByPrimaryKey(Long id);

    int updateByPrimaryKey(CarModel record);

    List<CarModel> selectByModel(String carModel);

    void deleteById(Long id);
}
//    int insert(TaxiDriverInfoModel record);
//
//    TaxiDriverInfoModel selectByPrimaryKey(Long driverId);
//
//    int updateByPrimaryKey(TaxiDriverInfoModel record);
//
//    List<TaxiDriverInfoModel> selectByLastName(String lastName);