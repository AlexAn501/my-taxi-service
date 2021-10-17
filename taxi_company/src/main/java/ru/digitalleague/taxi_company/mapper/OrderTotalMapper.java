package ru.digitalleague.taxi_company.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OrderTotalMapper {
    /**
     * Записывает номер поездки и счет в таблицу order_total
     * @param orderId Идентификатор поездки
     * @param tripAmount Цена поездки
     */
    @Insert("INSERT INTO order_total (order_id, sum) VALUES (#{orderId},#{tripAmount})")
        void saveSumOrderByOrderId(long orderId, int tripAmount);
}
