package cn.kosh.sysmgr.service;

import cn.kosh.framework.domain.OperationType;
import cn.kosh.framework.domain.Condition;
import cn.kosh.sysmgr.domain.Sysuser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by kosh on 2017/5/14.
 */
public interface SysuserService {
    Sysuser get(String id);

    void delete(String... ids);

    Sysuser save(Sysuser item, OperationType optype);

    Page<Sysuser> query(Condition condition, Pageable pageable);

    Sysuser getByName(String name);
}
