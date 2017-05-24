package cn.kosh.sysmgr.service;

import cn.kosh.framework.domain.Condition;
import cn.kosh.framework.domain.OperationType;
import cn.kosh.framework.web.security.AccountCredentials;
import cn.kosh.sysmgr.builder.SysoplogBuilder;
import cn.kosh.sysmgr.dao.SyspermissionRepository;
import cn.kosh.sysmgr.dao.SysroleRepository;
import cn.kosh.sysmgr.dao.SysuserRepository;
import cn.kosh.sysmgr.domain.Syspermission;
import cn.kosh.sysmgr.domain.Sysrole;
import cn.kosh.sysmgr.domain.Sysuser;
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
public class SysuserServiceImpl implements SysuserService, UserDetailsService {
    @Autowired
    private SysuserRepository sysuserRepository;

    @Autowired
    private SysroleRepository sysroleRepository;

    @Autowired
    private SyspermissionRepository syspermissionRepository;

    @Autowired
    private SysoplogService sysoplogService;

    public Sysuser get(String id) {
        return sysuserRepository.findOne(id);
    }

    @Transactional
    public void delete(String... ids) {
        sysuserRepository.deleteByFlag(ids);
        sysoplogService.save(SysoplogBuilder.build(OperationType.DELETE, "删除用户:%s", ids.toString()));
    }

    @Transactional
    public Sysuser save(Sysuser sysuser, OperationType optype) {
        boolean isadd = sysuser.getId() == null;
        sysuserRepository.save(sysuser);
        if (isadd) {
            sysoplogService.save(SysoplogBuilder.build(optype, "新增用户:%s", sysuser.getName()));
        } else {
            sysoplogService.save(SysoplogBuilder.build(optype, "修改用户:%s", sysuser.getName()));
        }
        return sysuser;
    }

    public Page<Sysuser> query(Condition condition, Pageable pageable) {
        return sysuserRepository.findAll(pageable);
    }

    public Sysuser getByName(String name) {
        return sysuserRepository.findByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        Sysuser user = getByName(username);
        if (user == null || user.isDeleted()) {
            return null;
        }

        String role_id;
        List<Sysrole> roles = sysroleRepository.findSysrolesByUser_id(user.getId());
        if (CollectionUtils.isEmpty(roles)) {
            return null;
        }
        role_id = roles.get(0).getId();
        AccountCredentials accountCredentials = new AccountCredentials(user.getName(), user.getPassword(), getGrantedAuthorities(role_id));
        accountCredentials.setUserid(user.getId());
        accountCredentials.setRoleid(role_id);
        accountCredentials.setOrgid(user.getOrg_id());
        return accountCredentials;
    }

    private List<GrantedAuthority> getGrantedAuthorities(String role_id) {
        List<GrantedAuthority> list = new ArrayList<>();
        List<Syspermission> permissions = syspermissionRepository.findSyspermissionsByRole_id(role_id);
        if (permissions == null) {
            return list;
        }

        for (Syspermission permission : permissions) {
            list.add(new SimpleGrantedAuthority(permission.getName()));
        }

        return list;
    }

}
