package com.main.tgcareer.modules.user.dao;

import com.main.tgcareer.modules.user.entity.Admin;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface AdminDao {

    public Admin getAdmin(Admin admin);

    public void saveAdmin(Admin admin);

    public void updateAdmin(Admin admin);

    public void deleteAdmin(String id);
}
