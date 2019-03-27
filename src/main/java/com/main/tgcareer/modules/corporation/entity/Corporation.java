package com.main.tgcareer.modules.corporation.entity;

import lombok.Getter;
import lombok.Setter;

public class Corporation {
    @Getter@Setter private String id;
    @Getter@Setter private String name;

    public String toString(){
        return "id:"+this.id+"  name:"+this.name;
    }
}
