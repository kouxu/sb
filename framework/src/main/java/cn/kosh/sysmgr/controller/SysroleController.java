package cn.kosh.sysmgr.controller;

import cn.kosh.framework.annotation.ConditionDefault;
import cn.kosh.framework.domain.Message;
import cn.kosh.framework.domain.OperationType;
import cn.kosh.sysmgr.condition.SysroleCondition;
import cn.kosh.sysmgr.domain.Sysrole;
import cn.kosh.sysmgr.service.SysroleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 用户
 * Created by kosh on 2017/5/14.
 */
@RestController
public class SysroleController {
    @Autowired
    private SysroleService sysroleService;

    @PreAuthorize("hasAuthority('user_list')")
    @GetMapping(value = "/sysrole", produces = "application/json")
    public Message list(@ConditionDefault SysroleCondition condition, @PageableDefault Pageable pageable) {
        Page<Sysrole> page = sysroleService.query(condition, pageable);
        return new Message(page);
    }

    @PreAuthorize("hasAuthority('role_detail')")
    @GetMapping(value = "/sysrole/{id}", produces = "application/json")
    public Message get(@PathVariable("id") String id) {
        Sysrole item = sysroleService.get(id);
        return new Message(item);
    }

    @PreAuthorize("hasAuthority('role_add')")
    @PostMapping("/sysrole")
    public Message add(@RequestBody Sysrole item) {
        item = sysroleService.save(item, OperationType.ADD);
        return new Message(item);
    }

    @PreAuthorize("hasAuthority('role_update')")
    @PatchMapping("/sysrole")
    public Message update(@RequestBody Sysrole item) {
        item = sysroleService.save(item, OperationType.UPDATE);
        return new Message(item);
    }

    @PreAuthorize("hasAuthority('role_replace')")
    @PutMapping("/sysrole")
    public Message replace(@RequestBody Sysrole item) {
        item = sysroleService.save(item, OperationType.REPLACE);
        return new Message(item);
    }

    @PreAuthorize("hasAuthority('role_delete')")
    @DeleteMapping(value = "/sysrole/{id}")
    public Message delete(@PathVariable("id") String id) {
        sysroleService.delete(id);
        return new Message();
    }

}
