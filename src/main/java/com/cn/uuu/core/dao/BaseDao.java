package com.cn.uuu.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 数据库操作基类接口
 * @author 10539
 *
 */
public interface BaseDao<T> {
    T findById(@Param("id") Integer id);

    int save(T entity);

    int update(T entity);

    int delete(@Param("id") Integer id);

    List<Map<String, Object>> allList();

}
