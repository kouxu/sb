package cn.kosh.sysmgr.service;

import cn.kosh.framework.domain.Condition;
import cn.kosh.framework.domain.OperationType;
import cn.kosh.framework.web.security.AccountCredentials;
import cn.kosh.sysmgr.builder.SysoplogBuilder;
import cn.kosh.sysmgr.dao.SyspermissionRepository;
import cn.kosh.sysmgr.dao.SysroleRepository;
import cn.kosh.sysmgr.dao.SysresourceRepository;
import cn.kosh.sysmgr.domain.Syspermission;
import cn.kosh.sysmgr.domain.Sysrole;
import cn.kosh.sysmgr.domain.Sysresource;
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
public class SysresourceServiceImpl implements SysresourceService {
    @Autowired
    private SysresourceRepository sysresourceRepository;

    @Autowired
    private SysoplogService sysoplogService;

    public Sysresource get(String id) {
        return sysresourceRepository.findOne(id);
    }

    @Transactional
    public void delete(String... ids) {
        sysresourceRepository.deleteByFlag(ids);
        sysoplogService.save(SysoplogBuilder.build(OperationType.DELETE, "删除资源:%s", ids.toString()));
    }

    @Transactional
    public Sysresource save(Sysresource sysresource, OperationType optype) {
        boolean isadd = sysresource.getId() == null;
        sysresourceRepository.save(sysresource);
        if (isadd) {
            sysoplogService.save(SysoplogBuilder.build(optype, "新增资源:%s", sysresource.getName()));
        } else {
            sysoplogService.save(SysoplogBuilder.build(optype, "修改资源:%s", sysresource.getName()));
        }
        return sysresource;
    }

    public Page<Sysresource> query(Condition condition, Pageable pageable) {
        return sysresourceRepository.findAll(pageable);
    }

}
