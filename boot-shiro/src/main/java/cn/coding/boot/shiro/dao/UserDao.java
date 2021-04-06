package cn.coding.boot.shiro.dao;

import cn.coding.boot.shiro.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

public interface UserDao {
    @Select("SELECT ID,USERNAME,PASSWORD FROM USER WHERE USERNAME=#{name}")
    User getUserByName(String name);
}
