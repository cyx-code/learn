package cn.coding.boot.tm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("cn.coding.boot.tm.mapper")
public class BootThymeleafMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootThymeleafMybatisApplication.class, args);
    }

}
