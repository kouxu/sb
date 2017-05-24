package cn.kosh.sysmgr.service;

import cn.kosh.framework.domain.Condition;
import cn.kosh.framework.domain.OperationType;
import cn.kosh.sysmgr.domain.Sysorg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by kosh on 2017/5/14.
 */
public interface SysorgService {
    Sysorg get(String id);

    void delete(String... ids);

    Sysorg save(Sysorg item, OperationType optype);

    Page<Sysorg> query(Condition condition, Pageable pageable);
}
