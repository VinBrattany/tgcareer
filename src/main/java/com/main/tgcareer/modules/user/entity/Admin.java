package com.main.tgcareer.modules.user.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;

public class Admin {
    @Getter @Setter private String id;

    @NotBlank(message = "账号不能为空!")
    @Getter @Setter private String name;

    @NotBlank(message = "密码不能为空!")
    @Getter @Setter private String password;
}
