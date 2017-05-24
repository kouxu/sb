package cn.kosh.framework.jdbc;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kosh on 2017/5/1.
 */
public class SqlBuilder {
    private StringBuilder sql = new StringBuilder();
    private List<Object> params = new ArrayList<Object>();

    public SqlBuilder() {
    }

    public SqlBuilder(String sql) {
        this.sql.append(sql);
    }


    public String getSql() {
        return sql.toString();
    }

    public Object[] getParams() {
        return params.toArray();
    }

    public SqlBuilder append(String sql) {
        if (StringUtils.isNotEmpty(sql)) {
            this.sql.append(sql);
        }
        return this;
    }

    public SqlBuilder append(String sql, Object conditionValue) {
        if (conditionValue != null) {
            if (String.class.isInstance(conditionValue)) {
                String val = (String) conditionValue;
                if (StringUtils.contains(sql, "like")) {
                    conditionValue = "%" + val + "%";
                }
            }
            this.sql.append(sql);
            params.add(conditionValue);
        }
        return this;
    }

    public SqlBuilder append(Sort sort) {
        return append(sort, null);
    }

    public SqlBuilder append(Sort sort, String alias) {
        if (sort != null) {
            Iterator<Sort.Order> sortItr = sort.iterator();
            if (sortItr.hasNext()) {
                sql.append(" order by ");
            }
            while (sortItr.hasNext()) {
                Sort.Order next = sortItr.next();
                if (StringUtils.isNotEmpty(alias)) {
                    sql.append(alias).append('.');
                }
                sql.append(next.getProperty()).append(' ').append(next.getDirection());
                if (sortItr.hasNext()) {
                    sql.append(",");
                }
            }
        }
        return this;
    }
}
