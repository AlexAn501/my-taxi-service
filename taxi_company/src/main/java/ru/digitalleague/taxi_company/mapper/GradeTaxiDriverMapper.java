package ru.digitalleague.taxi_company.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface GradeTaxiDriverMapper {
    /**
     * Записывает оценку за поездку
     * @param driverId Идентификатор водителя
     * @param grade Оценка
     * @param orderId Идентификатор поездки
     */
    @Insert("INSERT INTO grade_taxi_driver (order_id, driver_id, grade) VALUES (#{orderId}, #{driverId}, #{grade})")
    void saveGradeTrip(long orderId, long driverId, int grade);

    @Select("SELECT AVG(grade) FROM grade_taxi_driver")
    float findAverageGrade(long driverId);
}
