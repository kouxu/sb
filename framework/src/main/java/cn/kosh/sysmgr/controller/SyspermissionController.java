package cn.kosh.sysmgr.controller;

import cn.kosh.framework.annotation.ConditionDefault;
import cn.kosh.framework.domain.Message;
import cn.kosh.framework.domain.OperationType;
import cn.kosh.sysmgr.condition.SyspermissionCondition;
import cn.kosh.sysmgr.domain.Syspermission;
import cn.kosh.sysmgr.service.SyspermissionService;
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
public class SyspermissionController {
    @Autowired
    private SyspermissionService syspermissionService;

    @PreAuthorize("hasAuthority('permission_list')")
    @GetMapping(value = "/syspermission", produces = "application/json")
    public Message list(@ConditionDefault SyspermissionCondition condition, @PageableDefault Pageable pageable) {
        Page<Syspermission> page = syspermissionService.query(condition, pageable);
        return new Message(page);
    }

    @PreAuthorize("hasAuthority('permission_detail')")
    @GetMapping(value = "/syspermission/{id}", produces = "application/json")
    public Message get(@PathVariable("id") String id) {
        Syspermission item = syspermissionService.get(id);
        return new Message(item);
    }

    @PreAuthorize("hasAuthority('permission_add')")
    @PostMapping("/syspermission")
    public Message add(@RequestBody Syspermission item) {
        item = syspermissionService.save(item, OperationType.ADD);
        return new Message(item);
    }

    @PreAuthorize("hasAuthority('permission_update')")
    @PatchMapping("/syspermission")
    public Message update(@RequestBody Syspermission item) {
        item = syspermissionService.save(item, OperationType.UPDATE);
        return new Message(item);
    }

    @PreAuthorize("hasAuthority('permission_replace')")
    @PutMapping("/syspermission")
    public Message replace(@RequestBody Syspermission item) {
        item = syspermissionService.save(item, OperationType.REPLACE);
        return new Message(item);
    }

    @PreAuthorize("hasAuthority('permission_delete')")
    @DeleteMapping(value = "/syspermission/{id}")
    public Message delete(@PathVariable("id") String id) {
        syspermissionService.delete(id);
        return new Message();
    }

}
