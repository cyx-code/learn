package cn.coding.boot.shiro.service;

import cn.coding.boot.shiro.pojo.User;

public interface UserService {
    User getUserByName(String name);
}
