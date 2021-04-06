package cn.coding.boot.tm.controller;

import cn.coding.boot.tm.dto.QueryPage;
import cn.coding.boot.tm.dto.ResponseCode;
import cn.coding.boot.tm.entity.User;
import cn.coding.boot.tm.entity.UserWithRole;
import cn.coding.boot.tm.enums.ResponseEnums;
import cn.coding.boot.tm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/list")
    public ResponseCode listUsers(QueryPage queryPage, User user) {
        return ResponseCode.success(super.selectByPageNumSize(queryPage, () -> userService.listUsers(user)));
    }

    @GetMapping("/findById")
    public ResponseCode findById(Long id) {
        return ResponseCode.success(userService.getById(id));
    }

    @PostMapping("/add")
    public ResponseCode add(@RequestBody UserWithRole user) {
        try {
            userService.add(user);
            return ResponseCode.success();
        } catch (Exception e) {
            logger.error("添加用户出错!", e);
            return ResponseCode.error();
        }
    }

    @GetMapping("/checkName")
    public ResponseCode checkName(String name, String id) {
        if (name.isEmpty()) {
            return new ResponseCode(ResponseEnums.PARM_ERROR);
        }
        if (!userService.checkName(name, id)) {
            return new ResponseCode(ResponseEnums.PARM_REPEAT);
        }
        return ResponseCode.success();
    }

    @PostMapping("/update")
    public ResponseCode update(@RequestBody UserWithRole user) {
        try {
            userService.update(user);
            return ResponseCode.success();
        } catch (Exception e) {
            logger.error("更新用户出错!", e);
            return ResponseCode.error();
        }
    }

    @DeleteMapping("delete")
    public ResponseCode delete(@RequestBody List<Long> ids) {
        try {
            userService.delete(ids);
            return ResponseCode.success();
        } catch (Exception e) {
            logger.error("删除用户出错！", e);
            return ResponseCode.error();
        }
    }
}
