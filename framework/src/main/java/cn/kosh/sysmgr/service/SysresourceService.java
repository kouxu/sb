package cn.kosh.sysmgr.service;

import cn.kosh.framework.domain.Condition;
import cn.kosh.framework.domain.OperationType;
import cn.kosh.sysmgr.domain.Sysresource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by kosh on 2017/5/14.
 */
public interface SysresourceService {
    Sysresource get(String id);

    void delete(String... ids);

    Sysresource save(Sysresource item, OperationType optype);

    Page<Sysresource> query(Condition condition, Pageable pageable);

}
