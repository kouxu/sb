package cn.kosh.sysmgr.controller;

import cn.kosh.framework.annotation.ConditionDefault;
import cn.kosh.framework.domain.Message;
import cn.kosh.framework.domain.OperationType;
import cn.kosh.sysmgr.condition.SysuserCondition;
import cn.kosh.sysmgr.domain.Sysuser;
import cn.kosh.sysmgr.service.SysuserService;
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
public class SysuserController {
    @Autowired
    private SysuserService sysuserService;

    @PreAuthorize("hasAuthority('user_list')")
    @GetMapping(value = "/sysuser", produces = "application/json")
    public Message list(@ConditionDefault SysuserCondition condition, @PageableDefault Pageable pageable) {
        Page<Sysuser> page = sysuserService.query(condition, pageable);
        return new Message(page);
    }

    @PreAuthorize("hasAuthority('user_detail')")
    @GetMapping(value = "/sysuser/{id}", produces = "application/json")
    public Message get(@PathVariable("id") String id) {
        Sysuser item = sysuserService.get(id);
        return new Message(item);
    }

    @PreAuthorize("hasAuthority('user_add')")
    @PostMapping("/sysuser")
    public Message add(@RequestBody Sysuser item) {
        item = sysuserService.save(item, OperationType.ADD);
        return new Message(item);
    }

    @PreAuthorize("hasAuthority('user_update')")
    @PatchMapping("/sysuser")
    public Message update(@RequestBody Sysuser item) {
        item = sysuserService.save(item, OperationType.UPDATE);
        return new Message(item);
    }

    @PreAuthorize("hasAuthority('user_replace')")
    @PutMapping("/sysuser")
    public Message replace(@RequestBody Sysuser item) {
        item = sysuserService.save(item, OperationType.REPLACE);
        return new Message(item);
    }

    @PreAuthorize("hasAuthority('user_delete')")
    @DeleteMapping(value = "/sysuser/{id}")
    public Message delete(@PathVariable("id") String id) {
        sysuserService.delete(id);
        return new Message();
    }

}
