package cn.kosh.sysmgr.service;

import cn.kosh.framework.domain.Condition;
import cn.kosh.framework.domain.OperationType;
import cn.kosh.framework.web.security.AccountCredentials;
import cn.kosh.sysmgr.builder.SysoplogBuilder;
import cn.kosh.sysmgr.dao.SysroleRepository;
import cn.kosh.sysmgr.domain.Sysrole;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kosh on 2017/5/14.
 */
@Service
public class SysroleServiceImpl implements SysroleService {
    @Autowired
    private SysroleRepository sysroleRepository;

    @Autowired
    private SysoplogService sysoplogService;

    public Sysrole get(String id) {
        return sysroleRepository.findOne(id);
    }

    @Transactional
    public void delete(String... ids) {
        sysroleRepository.deleteByFlag(ids);
        sysoplogService.save(SysoplogBuilder.build(OperationType.DELETE, "删除角色:%s", ids.toString()));
    }

    @Transactional
    public Sysrole save(Sysrole sysrole, OperationType optype) {
        sysroleRepository.save(sysrole);
        if (sysrole.getId() == null) {
            sysoplogService.save(SysoplogBuilder.build(optype, "新增角色:%s", sysrole.getName()));
        } else {
            sysoplogService.save(SysoplogBuilder.build(optype, "修改角色:%s", sysrole.getName()));
        }
        return sysrole;
    }

    public Page<Sysrole> query(Condition condition, Pageable pageable) {
        return sysroleRepository.findAll(pageable);
    }

    @Override
    public List<Sysrole> findSysrolesByUser_id(String user_id) {
        return sysroleRepository.findSysrolesByUser_id(user_id);
    }
}
