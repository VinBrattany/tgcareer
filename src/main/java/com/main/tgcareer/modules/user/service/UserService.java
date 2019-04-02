package com.main.tgcareer.modules.user.service;

import com.main.tgcareer.modules.user.dao.UserDao;
import com.main.tgcareer.modules.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    UserDao userDao;

    public User getUser(String id){
        return userDao.getUser(id);
    }

    @Transactional
    public void saveUser(User user){
         user.setId(UUID.randomUUID().toString().replaceAll("-",""));
         userDao.saveUser(user);
    }

    @Transactional
    public void updateUser(User user){
        userDao.updateUser(user);
    }

    @Transactional
    public void deleteUser(String id){
        userDao.deleteUser(id);
    }

    public List<User> getAllUser(Map params){
        return userDao.getAllUser(params);
    }

}
