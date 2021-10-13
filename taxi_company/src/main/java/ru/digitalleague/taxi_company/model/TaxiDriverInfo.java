package ru.digitalleague.taxi_company.model;

import lombok.Data;

import java.time.OffsetDateTime;
import java.util.Date;

@Data
public class TaxiDriverInfo {

    /**
     * Идентификатор водителя.
     */
    private Long driverId;

    /**
     * Фамилия.
     */
    private String lastName;

    /**
     * Имя.
     */
    private String firstName;

    /**
     * Уровень.
     */
    private int level;

    /**
     * Модель авто (должна быть ENUM).
     * Идентификатор а/м
     */
    private String carId;

    /**
     * Дата создания.
     */
    private Date createDttm;

    /**
     * Стоимость одной минуты
     */
    private int minuteCost;

    /**
     * К какому таксопарку(Городу) относится водитель
     */
    private long cityId;

    /**
     * Личный рейтинг водителя
     */
    private int rating;

    /**
     * Занят ли водитель
     */
    private boolean isBusy;
}
