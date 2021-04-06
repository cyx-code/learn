package cn.coding.boot.tm.service;

import cn.coding.boot.tm.entity.User;

import java.util.List;

public interface UserRoleService {
    void deleteUserRolesByUserId(List<Long> keys);
}
