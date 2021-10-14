package ru.digitalleague.taxi_company.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@ApiModel("Модель Автомобиля")
public class Car {
    /**
     * Идентификатор машины.
     */
    @ApiModelProperty("Идентификатор машины")
    long id;

    /**
     * Модель машины.
     */
    @ApiModelProperty("Модель машины")
    String model;

    /**
     * Дата создания машины.
     */
    @ApiModelProperty("Дата создания машины")
    OffsetDateTime createDttm;
}
