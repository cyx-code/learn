package cn.coding.boot.shiro.service.impl;

import cn.coding.boot.shiro.dao.UserDao;
import cn.coding.boot.shiro.pojo.User;
import cn.coding.boot.shiro.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }
}
