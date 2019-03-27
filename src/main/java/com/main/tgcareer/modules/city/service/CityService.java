package com.main.tgcareer.modules.city.service;


import com.main.tgcareer.modules.city.dao.CityDao;
import com.main.tgcareer.modules.city.entity.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CityService {
    @Autowired
    CityDao citydao;

    @Transactional
    public City getCity(String id){
        return citydao.getCity(id);
    }
    @Transactional
    public void saveCity(City city){
        city.setId(UUID.randomUUID().toString().replaceAll("-",""));
        citydao.saveCity(city);
    }
    @Transactional
    public void updateCity(City city){
        citydao.updateCity(city);
    }
    @Transactional
    public void deleteCity(String id){
        citydao.deleteCity(id);
    }
}
