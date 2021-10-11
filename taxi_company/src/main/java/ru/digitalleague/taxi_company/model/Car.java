package ru.digitalleague.taxi_company.model;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class Car {
    /**
     * Идентификатор машины.
     */
    long id;

    /**
     * Модель машины.
     */
    String model;

    /**
     * Дата создания машины.
     */
    OffsetDateTime createDttm;
}
