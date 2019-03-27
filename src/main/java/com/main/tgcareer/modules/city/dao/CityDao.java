package com.main.tgcareer.modules.city.dao;

import com.main.tgcareer.modules.city.entity.City;
import org.springframework.stereotype.Component;
@Component
public interface CityDao {
    /*
     * 根据openID返回城市
     * @param id
     * @return City
     */
    public City getCity(String id);

    /*
     *  新增城市
     *  @param college
     */
    public void saveCity(City city);

    /**
     * 更新城市信息
     * @param city
     */
    public void updateCity(City city);

    /**
     * 删除城市信息
     * @param id
     */
    public void deleteCity(String id);
}
