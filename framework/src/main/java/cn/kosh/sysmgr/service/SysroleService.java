package cn.kosh.sysmgr.service;

import cn.kosh.framework.domain.Condition;
import cn.kosh.framework.domain.OperationType;
import cn.kosh.sysmgr.domain.Sysrole;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by kosh on 2017/5/14.
 */
public interface SysroleService {
    Sysrole get(String id);

    void delete(String... ids);

    Sysrole save(Sysrole item, OperationType optype);

    Page<Sysrole> query(Condition condition, Pageable pageable);

    List<Sysrole> findSysrolesByUser_id(String user_id);
}
