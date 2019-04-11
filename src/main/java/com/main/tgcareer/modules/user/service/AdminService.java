package com.main.tgcareer.modules.user.service;

import com.main.tgcareer.modules.user.dao.AdminDao;
import com.main.tgcareer.modules.user.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminDao adminDao;

    public Admin getAdmin(Admin admin){
        return adminDao.getAdmin(admin);
    }

    public void saveAdmin(Admin admin){
        adminDao.saveAdmin(admin);
    }

    public void updateAdmin(Admin admin){
        adminDao.updateAdmin(admin);
    }

    public void deleteAdmin(String id){
        adminDao.deleteAdmin(id);
    }

    public Admin getAdminById(String id){
        return adminDao.getAdminById(id);
    }
}
