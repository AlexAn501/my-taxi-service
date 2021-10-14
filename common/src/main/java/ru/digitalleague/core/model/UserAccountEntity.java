package ru.digitalleague.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

@Data
@ApiModel("Модель пользователя")
public class UserAccountEntity implements UserDetails {
    @ApiModelProperty("Идентификатор пользователя")
    private Long id;

    @ApiModelProperty("Логин пользователя")
    private String login;

    @ApiModelProperty("Пароль пользователя")
    private String password;

    @ApiModelProperty("Список ролей пользователя")
    private List<AuthorityEntity> authorities;

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
