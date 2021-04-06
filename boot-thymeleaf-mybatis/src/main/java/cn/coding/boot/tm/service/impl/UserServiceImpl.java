package cn.coding.boot.tm.service.impl;

import cn.coding.boot.tm.entity.User;
import cn.coding.boot.tm.entity.UserRole;
import cn.coding.boot.tm.entity.UserWithRole;
import cn.coding.boot.tm.mapper.UserMapper;
import cn.coding.boot.tm.mapper.UserRoleMapper;
import cn.coding.boot.tm.service.UserRoleService;
import cn.coding.boot.tm.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private UserRoleService userRoleService;
    @Override
    public User getByName(String name) {
        Example example = new Example(User.class);
        example.createCriteria().andCondition("username=", name);
        List<User> users = this.getByExample(example);
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public UserWithRole getById(Long id) {
        List<UserWithRole> users = userMapper.getById(id);
        if (users.isEmpty()) {
            return null;
        }
        List<Long> roleIds = users.stream().map(UserWithRole::getRoleId).collect(Collectors.toList());
        UserWithRole userWithRole = users.get(0);
        userWithRole.setRoleIds(roleIds);
        return userWithRole;
    }

    @Override
    public List<User> listUsers(User user) {
        try {
            return userMapper.listUsers(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public void add(UserWithRole user) {
        this.save(user);
        saveUserRole(user);
    }

    private void saveUserRole(UserWithRole user) {
        user.getRoleIds().forEach(roleId -> {
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(roleId);
            userRoleMapper.insert(userRole);
        });
    }

    @Override
    public boolean checkName(String name, String id) {
        if (name.isEmpty()) {
            return false;
        }
        Example example = new Example(User.class);
        if (!id.isEmpty()) {
            example.createCriteria().andCondition("lower(username)=", name.toLowerCase()).andNotEqualTo("id", id);
        } else {
            example.createCriteria().andCondition("lower(username)=", name.toLowerCase());
        }
        List<User> users = this.getByExample(example);
        if (users.size() > 0) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public void update(UserWithRole user) {
        user.setPassword(null);
        this.updateNotNull(user);
        Example example = new Example(UserRole.class);
        example.createCriteria().andCondition("user_id=", user.getId());
        userMapper.deleteByExample(example);
        saveUserRole(user);
    }

    @Override
    public void delete(List<Long> keys) {
        this.batchDelete(keys, "id", User.class);
        userRoleService.deleteUserRolesByUserId(keys);
    }
}
