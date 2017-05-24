package cn.kosh.sysmgr.service;

import cn.kosh.framework.domain.Condition;
import cn.kosh.framework.domain.OperationType;
import cn.kosh.framework.web.security.AccountCredentials;
import cn.kosh.sysmgr.builder.SysoplogBuilder;
import cn.kosh.sysmgr.dao.SyspermissionRepository;
import cn.kosh.sysmgr.dao.SysroleRepository;
import cn.kosh.sysmgr.dao.SysmenuRepository;
import cn.kosh.sysmgr.domain.Syspermission;
import cn.kosh.sysmgr.domain.Sysrole;
import cn.kosh.sysmgr.domain.Sysmenu;
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
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kosh on 2017/5/14.
 */
@Service
public class SysmenuServiceImpl implements SysmenuService {
    @Autowired
    private SysmenuRepository sysmenuRepository;

    @Autowired
    private SysoplogService sysoplogService;

    public Sysmenu get(String id) {
        return sysmenuRepository.findOne(id);
    }

    @Transactional
    public void delete(String... ids) {
        sysmenuRepository.deleteByFlag(ids);
        sysoplogService.save(SysoplogBuilder.build(OperationType.DELETE, "删除菜单:%s", ids.toString()));
    }

    @Transactional
    public Sysmenu save(Sysmenu sysmenu, OperationType optype) {
        boolean isadd = sysmenu.getId() == null;
        sysmenuRepository.save(sysmenu);
        if (isadd) {
            sysoplogService.save(SysoplogBuilder.build(optype, "新增菜单:%s", sysmenu.getName()));
        } else {
            sysoplogService.save(SysoplogBuilder.build(optype, "修改菜单:%s", sysmenu.getName()));
        }
        return sysmenu;
    }

    public Page<Sysmenu> query(Condition condition, Pageable pageable) {
        return sysmenuRepository.findAll(pageable);
    }

}
