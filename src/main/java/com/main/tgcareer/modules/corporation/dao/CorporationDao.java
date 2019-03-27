package com.main.tgcareer.modules.corporation.dao;

import com.main.tgcareer.modules.corporation.entity.Corporation;
import org.springframework.stereotype.Component;
@Component
public interface CorporationDao {
    /*
     * 根据openID返回公司
     * @param id
     * @return Corporation
     */
    public Corporation getCorporation(String id);

    /*
     *  新增公司
     *  @param corporation
     */
    public void saveCorporation(Corporation corporation);

    /**
     * 更新公司信息
     * @param corporation
     */
    public void updateCorporation(Corporation corporation);

    /**
     * 删除公司信息
     * @param id
     */
    public void deleteCorporation(String id);
}
