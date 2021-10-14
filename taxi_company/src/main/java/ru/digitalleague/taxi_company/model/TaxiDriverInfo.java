package ru.digitalleague.taxi_company.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("Модель информации о водителе")
public class TaxiDriverInfo {

    /**
     * Идентификатор водителя.
     */
    @ApiModelProperty("Идентификатор водителя")
    private Long driverId;

    /**
     * Фамилия.
     */
    @ApiModelProperty("Фамилия водителя")
    private String lastName;

    /**
     * Имя.
     */
    @ApiModelProperty("Имя водителя")
    private String firstName;

    /**
     * Уровень.
     */
    @ApiModelProperty("Уровень водителя")
    private int level;

    /**
     * Модель авто (должна быть ENUM).
     * Идентификатор а/м
     */
    @ApiModelProperty("Идентификатор машины")
    private String carId;

    /**
     * Дата создания.
     */
    @ApiModelProperty("Дата регистрации водителя")
    private Date createDttm;

    /**
     * Стоимость одной минуты
     */
    @ApiModelProperty("Стоимость одной минуты")
    private int minuteCost;

    /**
     * К какому таксопарку(Городу) относится водитель
     */
    @ApiModelProperty("К какому таксопарку(Городу) относится водитель")
    private long cityId;

    /**
     * Личный рейтинг водителя
     */
    @ApiModelProperty("Личный рейтинг водителя")
    private int rating;

    /**
     * Занят ли водитель
     */
    @ApiModelProperty("Занят ли водитель")
    private boolean isBusy;
}
