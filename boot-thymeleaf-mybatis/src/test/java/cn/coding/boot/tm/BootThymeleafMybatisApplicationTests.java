package cn.coding.boot.tm;

import cn.coding.boot.tm.entity.User;
import cn.coding.boot.tm.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


class BootThymeleafMybatisApplicationTests {
    @Resource
    private UserMapper userMapper;

    @Test
    public void test() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUsername("name" + i);
            user.setId((long) i);
            user.setPassword("pwd" + i);
            user.setAge(i + 10);
            users.add(user);
        }
        List<Long> list = users.stream().map(User::getId).collect(Collectors.toList());
        for (Long aLong : list) {
            System.out.println(aLong);
        }
    }
}
