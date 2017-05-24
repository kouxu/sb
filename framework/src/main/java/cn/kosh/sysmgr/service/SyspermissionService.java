package cn.kosh.sysmgr.service;

import cn.kosh.framework.domain.Condition;
import cn.kosh.framework.domain.OperationType;
import cn.kosh.sysmgr.domain.Syspermission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by kosh on 2017/5/14.
 */
public interface SyspermissionService {
    Syspermission get(String id);

    void delete(String... ids);

    Syspermission save(Syspermission item, OperationType optype);

    Page<Syspermission> query(Condition condition, Pageable pageable);

    List<Syspermission> findSyspermissionsByRole_id(String role_id);
}
