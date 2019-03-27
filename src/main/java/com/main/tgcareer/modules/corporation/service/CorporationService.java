package com.main.tgcareer.modules.corporation.service;


import com.main.tgcareer.modules.corporation.dao.CorporationDao;
import com.main.tgcareer.modules.corporation.entity.Corporation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class CorporationService {
    @Autowired
    CorporationDao corporationDao;

    @Transactional
    public Corporation getCorporation(String id){ return corporationDao.getCorporation(id);}

    @Transactional
    public void saveCorporation(Corporation corporation){
        corporation.setId(UUID.randomUUID().toString().replaceAll("-",""));
        corporationDao.saveCorporation(corporation);
    }
    @Transactional
    public void updateCorporation(Corporation corporation){
        corporationDao.updateCorporation(corporation);
    }
    @Transactional
    public void deleteCorporation(String id){
        corporationDao.deleteCorporation(id);
    }

}
