package cn.kosh.sysmgr.service;

import cn.kosh.framework.domain.Condition;
import cn.kosh.framework.domain.OperationType;
import cn.kosh.sysmgr.domain.Sysmenu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by kosh on 2017/5/14.
 */
public interface SysmenuService {
    Sysmenu get(String id);

    void delete(String... ids);

    Sysmenu save(Sysmenu item, OperationType optype);

    Page<Sysmenu> query(Condition condition, Pageable pageable);

}
