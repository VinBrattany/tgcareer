package com.main.tgcareer.modules.salary.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Salary {

    @Getter @Setter private String id;

    @Getter @Setter private String city;

    @Getter @Setter private String job;

    @Getter @Setter private String corporation;

    @Getter @Setter private String contact;

    @Getter @Setter private Date publicTime;

    @Getter @Setter private String createTime;

    @Getter @Setter private String updateTime;

    public Salary(){};
}
