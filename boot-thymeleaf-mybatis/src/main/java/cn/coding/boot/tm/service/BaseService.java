package cn.coding.boot.tm.service;

import java.util.List;

public interface BaseService<T> {
    List<T> listAll();
    T getByKey(Object key);
    int save(T entity);
    int deleteByKey(Object key);
    int batchDelete(List<Long> ids, String property, Class<T> clazz);
    int updateAll(T entity);
    int updateNotNull(T entity);
    List<T> getByExample(Object example);
}
