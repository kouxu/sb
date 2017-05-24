package cn.kosh.sysmgr.service;

import cn.kosh.framework.domain.OperationType;
import cn.kosh.framework.domain.Condition;
import cn.kosh.sysmgr.domain.Sysoplog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by kosh on 2017/5/14.
 */
public interface SysoplogService {
    Sysoplog get(String id);

    Sysoplog save(Sysoplog item);

    Page<Sysoplog> query(Condition condition, Pageable pageable);
}
