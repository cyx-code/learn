package cn.coding.boot.tm.service.impl;

import cn.coding.boot.tm.entity.Role;
import cn.coding.boot.tm.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;
@Service
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {
    @Override
    public List<Role> listRoles(Role role) {
        try {
            Example example = new Example(Role.class);
            if (StringUtils.isNotBlank(role.getName())) {
                example.createCriteria().andCondition("name=", role.getName());
            }
            return this.getByExample(example);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
