package com.main.tgcareer.modules.user.entity;

import lombok.Getter;
import lombok.Setter;

public class User {
//    id
    @Getter @Setter private String id;
//    openid
    @Getter @Setter private String openid;
//    nikeName
    @Getter @Setter private String petName;
//    name
    @Getter @Setter private String name;
//    city
    @Getter @Setter private String city;

    @Getter @Setter private boolean sex;

    @Getter @Setter private String photoPath;

    @Getter@Setter private byte age;

    @Getter@Setter private String phone;

    @Getter@Setter private String college;

    @Getter@Setter private byte eduction;

    @Getter@Setter private boolean push;

    @Getter@Setter private double expectedAnnualSalary;

    @Getter@Setter private double annualSalary;

    @Getter@Setter private double monthlySalary;

    @Override
    public String toString(){
        return "id:"+this.id+"  openid:"+this.openid+"  petName:"+this.petName
                +"  name:"+this.name+"  city:"+this.city+"  photoPath:"+this.photoPath;
    }

}
