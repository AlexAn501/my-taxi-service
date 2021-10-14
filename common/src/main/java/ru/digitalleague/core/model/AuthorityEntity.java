package ru.digitalleague.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import org.springframework.security.core.GrantedAuthority;

@Data
@ApiModel("Модель Роли")
public class AuthorityEntity implements GrantedAuthority {
    @ApiModelProperty("Идентификатор роли")
    private Long id;

    @ApiModelProperty("Идентификатор юзера")
    private Long userAccountId;

    @ApiModelProperty("Название роли")
    private String authority;
}
