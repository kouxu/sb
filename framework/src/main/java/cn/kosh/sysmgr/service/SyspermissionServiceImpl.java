package cn.kosh.sysmgr.service;

import cn.kosh.framework.domain.Condition;
import cn.kosh.framework.domain.OperationType;
import cn.kosh.sysmgr.builder.SysoplogBuilder;
import cn.kosh.sysmgr.dao.SyspermissionRepository;
import cn.kosh.sysmgr.domain.Syspermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by kosh on 2017/5/14.
 */
@Service
public class SyspermissionServiceImpl implements SyspermissionService {
    @Autowired
    private SyspermissionRepository syspermissionRepository;

    @Autowired
    private SysoplogService sysoplogService;

    public Syspermission get(String id) {
        return syspermissionRepository.findOne(id);
    }

    @Transactional
    public void delete(String... ids) {
        syspermissionRepository.deleteByFlag(ids);
        sysoplogService.save(SysoplogBuilder.build(OperationType.DELETE, "删除权限:%s", ids.toString()));
    }

    @Transactional
    public Syspermission save(Syspermission syspermission, OperationType optype) {
        syspermissionRepository.save(syspermission);
        if (syspermission.getId() == null) {
            sysoplogService.save(SysoplogBuilder.build(optype, "新增权限:%s", syspermission.getName()));
        } else {
            sysoplogService.save(SysoplogBuilder.build(optype, "修改权限:%s", syspermission.getName()));
        }
        return syspermission;
    }

    public Page<Syspermission> query(Condition condition, Pageable pageable) {
        return syspermissionRepository.findAll(pageable);
    }

    @Override
    public List<Syspermission> findSyspermissionsByRole_id(String role_id) {
        return syspermissionRepository.findSyspermissionsByRole_id(role_id);
    }

}
