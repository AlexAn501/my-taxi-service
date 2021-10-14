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
@ApiModel("Модель Автомобиля")
public class CarModel {

    @ApiModelProperty("Идентификатор машины")
    private Long id;

    /**
     * Модель авто (должна быть ENUM).
     */
    @ApiModelProperty("Модель машины")
    private String carModel;

    /**
     * Дата создания.
     */
    @ApiModelProperty("Дата создания машины")
    private OffsetDateTime createDttm;
}
