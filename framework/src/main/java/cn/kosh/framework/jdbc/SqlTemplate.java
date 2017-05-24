package cn.kosh.framework.jdbc;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by kosh on 2017/4/30.
 */
public interface SqlTemplate {
    <T> T query(String sql, Class<T> clazz, Object... params);

    <T> List<T> queryList(String sql, Class<T> clazz, Object... params);

    <T> Page<T> queryListByPage(String sql, Class<T> clazz, Pageable pageable, Object... params);

    int update(String sql, Object... params);

    long count(String sql, Object... params);
}
