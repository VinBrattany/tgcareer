package com.main.tgcareer.modules.job.entity;

import lombok.Getter;
import lombok.Setter;

public class Job {
    @Getter@Setter private String id;
    @Getter@Setter private String name;

    @Override
    public String toString(){
        return "id:"+this.id+"  name:"+this.name;
    }
}
