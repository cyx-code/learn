package cn.coding.boot.tm.service;

import cn.coding.boot.tm.entity.User;
import cn.coding.boot.tm.entity.UserWithRole;

import java.util.List;

public interface UserService {
    User getByName(String name);
    UserWithRole getById(Long id);
    List<User> listUsers(User user);
    void add(UserWithRole user);
    boolean checkName(String name, String id);
    void update(UserWithRole user);
    void delete(List<Long> keys);
}
