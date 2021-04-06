package cn.coding.boot.tm.controller;

import cn.coding.boot.tm.dto.QueryPage;
import cn.coding.boot.tm.dto.ResponseCode;
import cn.coding.boot.tm.entity.Role;
import cn.coding.boot.tm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    @PostMapping("list")
    public ResponseCode queryList(QueryPage queryPage, Role role) {
        return ResponseCode.success(super.selectByPageNumSize(queryPage, () -> roleService.listRoles(role)));
    }
}
