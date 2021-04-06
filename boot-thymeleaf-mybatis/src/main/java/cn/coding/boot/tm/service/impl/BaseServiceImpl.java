package cn.coding.boot.tm.service.impl;

import cn.coding.boot.tm.service.BaseService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import java.util.List;

/**
 * 基础service类，将一些通常方法抽象出来
 * @param <T>
 */
public class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    @Getter
    private Mapper<T> mapper;


    @Override
    public List<T> listAll() {
        return mapper.selectAll();
    }

    @Override
    public T getByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    @Override
    public int save(T entity) {
        return mapper.insert(entity);
    }

    @Override
    public int deleteByKey(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    @Override
    public int batchDelete(List<Long> ids, String property, Class<T> clazz) {
        Example example = new Example(clazz);
        example.createCriteria().andIn(property, ids);
        return mapper.deleteByExample(example);
    }

    @Override
    public int updateAll(T entity) {
        return mapper.updateByPrimaryKey(entity);
    }
    /**
     * updateByPrimaryKeySelective()和updateByPrimaryKey()区别是前者会进行字段校验再更新，如果字段值为null就不更新
     *
     * @param entity
     */
    @Override
    public int updateNotNull(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public List<T> getByExample(Object example) {
        return mapper.selectByExample(example);
    }
}
