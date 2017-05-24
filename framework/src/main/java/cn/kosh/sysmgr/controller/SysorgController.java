package cn.kosh.sysmgr.controller;

import cn.kosh.framework.annotation.ConditionDefault;
import cn.kosh.framework.domain.Message;
import cn.kosh.framework.domain.OperationType;
import cn.kosh.sysmgr.condition.SysorgCondition;
import cn.kosh.sysmgr.domain.Sysorg;
import cn.kosh.sysmgr.service.SysorgService;
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
public class SysorgController {
    @Autowired
    private SysorgService sysorgService;

    @PreAuthorize("hasAuthority('org_list')")
    @GetMapping(value = "/sysorg", produces = "application/json")
    public Message list(@ConditionDefault SysorgCondition condition, @PageableDefault Pageable pageable) {
        Page<Sysorg> page = sysorgService.query(condition, pageable);
        return new Message(page);
    }

    @PreAuthorize("hasAuthority('org_detail')")
    @GetMapping(value = "/sysorg/{id}", produces = "application/json")
    public Message get(@PathVariable("id") String id) {
        Sysorg item = sysorgService.get(id);
        return new Message(item);
    }

    @PreAuthorize("hasAuthority('org_add')")
    @PostMapping("/sysorg")
    public Message add(@RequestBody Sysorg item) {
        item = sysorgService.save(item, OperationType.ADD);
        return new Message(item);
    }

    @PreAuthorize("hasAuthority('org_update')")
    @PatchMapping("/sysorg")
    public Message update(@RequestBody Sysorg item) {
        item = sysorgService.save(item, OperationType.UPDATE);
        return new Message(item);
    }

    @PreAuthorize("hasAuthority('org_replace')")
    @PutMapping("/sysorg")
    public Message replace(@RequestBody Sysorg item) {
        item = sysorgService.save(item, OperationType.REPLACE);
        return new Message(item);
    }

    @PreAuthorize("hasAuthority('org_delete')")
    @DeleteMapping(value = "/sysorg/{id}")
    public Message delete(@PathVariable("id") String id) {
        sysorgService.delete(id);
        return new Message();
    }

}
