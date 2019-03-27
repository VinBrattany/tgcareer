package com.main.tgcareer.modules.city.entity;

import lombok.Getter;
import lombok.Setter;

public class City {
    @Getter@Setter private String id;
    @Getter@Setter private String parentid;
    @Getter@Setter private String name;

    @Override
    public String toString(){
        return "id:"+this.id+"  parentid:" +this.parentid+"  name:"+this.name;
    }
}
