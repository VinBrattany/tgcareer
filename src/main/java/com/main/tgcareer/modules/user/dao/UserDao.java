package com.main.tgcareer.modules.user.dao;

import com.main.tgcareer.modules.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface UserDao {
    /**
     * 根据openID返回用户
     * @param id
     * @return User
     */
    public User getUser(String id);

    /**
     * 新增用户
     * @param user
     */
    public void saveUser(User user);

    /**
     * 更新用户信息
     * @param user
     */
    public void updateUser(User user);

    /**
     * 删除用户
     * @param id
     */
    public void deleteUser(String id);

    public List<User> getAllUser(Map params);

    public void addLogin(String id);

    public void addLooks(String id);
}

