package ru.digitalleague.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("Модель информации о водителе")
public class TaxiDriverInfoModel {

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
//
//    /**
//     * Отчество.
//     */
//    @ApiModelProperty("")
//    private String middleName;

    /**
     * Уровень.
     */
    @ApiModelProperty("Уровень водителя")
    private int level;

    /**
     * Модель авто (должна быть ENUM).
     */
    @ApiModelProperty("Идентификатор машины")
    private String carID;

    /**
     * Дата создания.
     */
    @ApiModelProperty("Дата регистрации водителя")
    private OffsetDateTime createDttm;
}
