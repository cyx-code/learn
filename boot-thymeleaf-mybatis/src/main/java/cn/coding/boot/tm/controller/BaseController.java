package cn.coding.boot.tm.controller;

import cn.coding.boot.tm.dto.QueryPage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class BaseController {

    /**
     * Supplier是JDK8新特性
     * 特点：只有返回值，没有输入参数
     * 如：Supplier<User> user = User::new;
     * 此时并没有构造User对象，当调用`user.get()`方法才获取一个新的User构造对象
     *
     * QueryPage 是封装分页条件的entity，如果没有指定默认查询所有
     */
    public Map<String, Object> selectByPageNumSize(QueryPage page, Supplier<?> supplier) {
        PageHelper.startPage(page.getPageCode(), page.getPageSize());
        PageInfo<?> pageInfo = new PageInfo<>((List<?>) supplier.get());
        PageHelper.clearPage();
        return getData(pageInfo);
    }

    private Map<String, Object> getData(PageInfo<?> pageInfo) {
        Map<String, Object> data = new HashMap<>();
        data.put("rows", pageInfo.getList());
        data.put("total", pageInfo.getTotal());
        return data;
    }
}