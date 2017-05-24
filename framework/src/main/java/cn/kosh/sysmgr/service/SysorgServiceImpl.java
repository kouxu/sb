package cn.kosh.sysmgr.service;

import cn.kosh.framework.domain.Condition;
import cn.kosh.framework.domain.OperationType;
import cn.kosh.framework.web.security.AccountCredentials;
import cn.kosh.sysmgr.builder.SysoplogBuilder;
import cn.kosh.sysmgr.dao.SyspermissionRepository;
import cn.kosh.sysmgr.dao.SysroleRepository;
import cn.kosh.sysmgr.dao.SysorgRepository;
import cn.kosh.sysmgr.domain.Syspermission;
import cn.kosh.sysmgr.domain.Sysrole;
import cn.kosh.sysmgr.domain.Sysorg;
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
public class SysorgServiceImpl implements SysorgService {
    @Autowired
    private SysorgRepository sysorgRepository;

    @Autowired
    private SysoplogService sysoplogService;

    public Sysorg get(String id) {
        return sysorgRepository.findOne(id);
    }

    @Transactional
    public void delete(String... ids) {
        sysorgRepository.deleteByFlag(ids);
        sysoplogService.save(SysoplogBuilder.build(OperationType.DELETE, "删除组织:%s", ids.toString()));
    }

    @Transactional
    public Sysorg save(Sysorg sysorg, OperationType optype) {
        boolean isadd = sysorg.getId() == null;
        sysorgRepository.save(sysorg);
        if (isadd) {
            sysoplogService.save(SysoplogBuilder.build(optype, "新增组织:%s", sysorg.getName()));
        } else {
            sysoplogService.save(SysoplogBuilder.build(optype, "修改组织:%s", sysorg.getName()));
        }
        return sysorg;
    }

    public Page<Sysorg> query(Condition condition, Pageable pageable) {
        return sysorgRepository.findAll(pageable);
    }

}
