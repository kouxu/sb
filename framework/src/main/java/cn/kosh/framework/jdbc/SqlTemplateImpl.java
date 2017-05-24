package cn.kosh.framework.jdbc;

import cn.kosh.framework.SpringHolder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by kosh on 2017/4/30.
 */
@Repository
@Transactional(readOnly = true)
public class SqlTemplateImpl implements SqlTemplate {
    @PersistenceContext
    private EntityManager em;


    public <T> T query(String sql, Class<T> clazz, Object... params) {
        Query query = getQuery(sql, clazz, params);
        return (T) query.getSingleResult();
    }

    public <T> List<T> queryList(String sql, Class<T> clazz, Object... params) {
        Query query = getQuery(sql, clazz, params);
        return (List<T>) query.getResultList();
    }

    public <T> Page<T> queryListByPage(String sql, Class<T> clazz, Pageable pageable, Object... params) {
        Query query = getQuery(getPageSql(sql, pageable), clazz, params);
        PageImpl<T> page = new PageImpl<T>(query.getResultList(), pageable, count(sql, params));
        return page;
    }

    @Transactional
    public int update(String sql, Object... params) {
        Query query = getQuery(sql, null, params);
        return query.executeUpdate();
    }

    public long count(String sql, Object... params) {
        Query query = getQuery(String.format("select count(*) from (%s) x", sql), null, params);
        Number total = (Number) query.getSingleResult();
        return total == null ? 0L : total.longValue();
    }

    private <T> Query getQuery(String sql, Class<T> clazz, Object... params) {
        Query query;
        if (clazz == null) {
            query = em.createNativeQuery(sql);
        } else {
            query = em.createNativeQuery(sql, clazz);
        }

        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                query.setParameter(i + 1, params[i]);
            }
        }
        return query;
    }

    private String getPageSql(String sql, Pageable pageable) {
        String driver = SpringHolder.getProperty("spring.datasource.driver-class-name");
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        if (StringUtils.containsIgnoreCase(driver, "mysql")) {
            return getMysqlPageSql(sql, offset, pageable.getPageSize());
        } else {
            return getOraclePageSql(sql, offset, pageable.getPageSize());
        }
    }

    private String getOraclePageSql(String sql, int offset, int limit) {
        if (offset > 0) {
            return String.format("select * from ( select row_.*, rownum rownum_ from ( %s ) row_  where rownum <= %d ) where rownum_ > %d", sql, offset + limit, offset);
        } else {
            return String.format("select * from ( %s ) where rownum <= %d", sql, limit);
        }
    }

    private String getMysqlPageSql(String sql, int offset, int limit) {
        return String.format("%s limit %d,%d", sql, offset, limit);
    }
}
