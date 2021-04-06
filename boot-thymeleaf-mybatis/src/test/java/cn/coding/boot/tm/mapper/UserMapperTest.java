package cn.coding.boot.tm.mapper;

import cn.coding.boot.tm.entity.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class UserMapperTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private UserMapper userMapper;
    @Test
    public void testFinAll(){
        List<User> users = userMapper.selectAll();
        users.forEach(user -> {
            logger.info("user={}", user);
        });
    }
    @Test
    public void findById() {
        User user = userMapper.selectByPrimaryKey(1L);
        logger.info("user={}", user);
    }
    @Test
    public void save() {
        User user = new User();
        user.setAge(20);
        user.setUsername("小李");
        user.setPassword("123456");
        userMapper.insert(user);
        testFinAll();
    }
    @Test
    public void update(){
        User user = new User();
        user.setId(2L);
        user.setUsername("Tom");
        userMapper.updateByPrimaryKey(user);
        testFinAll();
    }
    @Test
    public void delete(){
        userMapper.deleteByPrimaryKey(2L);
        testFinAll();
    }
}
