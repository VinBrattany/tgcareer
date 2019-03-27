package com.main.tgcareer.modules.college.entity;

import lombok.Getter;
import lombok.Setter;

public class College {
    @Getter@Setter private String id;
    @Getter@Setter private String name;

    @Override
    public String toString(){
        return "id:"+this.id+"  name:"+this.name;
    }
}
