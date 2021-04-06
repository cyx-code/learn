package cn.coding.boot.tm.mapper;

import cn.coding.boot.tm.entity.User;
import cn.coding.boot.tm.entity.UserWithRole;
import cn.coding.boot.tm.utils.MyMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface UserMapper extends MyMapper<User> {
    List<UserWithRole> getById(Long id);

    List<User> listUsers(User user);
}