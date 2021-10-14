package ru.digitalleague.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Модель поиска такси.
 */
@Data
@ApiModel("Модель поиска такси")
public class SearchDriverModel {

    /**
     * Уровень.
     */
    @ApiModelProperty("")
    private int level;

    /**
     * Модель авто.
     */
    @ApiModelProperty("")
    private String carModel;
}
