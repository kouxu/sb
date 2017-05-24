package cn.kosh.sysmgr.controller;

import cn.kosh.framework.annotation.ConditionDefault;
import cn.kosh.framework.domain.Message;
import cn.kosh.framework.domain.OperationType;
import cn.kosh.sysmgr.condition.SysresourceCondition;
import cn.kosh.sysmgr.domain.Sysresource;
import cn.kosh.sysmgr.service.SysresourceService;
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
public class SysresourceController {
    @Autowired
    private SysresourceService sysresourceService;

    @PreAuthorize("hasAuthority('resource_list')")
    @GetMapping(value = "/sysresource", produces = "application/json")
    public Message list(@ConditionDefault SysresourceCondition condition, @PageableDefault Pageable pageable) {
        Page<Sysresource> page = sysresourceService.query(condition, pageable);
        return new Message(page);
    }

    @PreAuthorize("hasAuthority('resource_detail')")
    @GetMapping(value = "/sysresource/{id}", produces = "application/json")
    public Message get(@PathVariable("id") String id) {
        Sysresource item = sysresourceService.get(id);
        return new Message(item);
    }

    @PreAuthorize("hasAuthority('resource_add')")
    @PostMapping("/sysresource")
    public Message add(@RequestBody Sysresource item) {
        item = sysresourceService.save(item, OperationType.ADD);
        return new Message(item);
    }

    @PreAuthorize("hasAuthority('resource_update')")
    @PatchMapping("/sysresource")
    public Message update(@RequestBody Sysresource item) {
        item = sysresourceService.save(item, OperationType.UPDATE);
        return new Message(item);
    }

    @PreAuthorize("hasAuthority('resource_replace')")
    @PutMapping("/sysresource")
    public Message replace(@RequestBody Sysresource item) {
        item = sysresourceService.save(item, OperationType.REPLACE);
        return new Message(item);
    }

    @PreAuthorize("hasAuthority('resource_delete')")
    @DeleteMapping(value = "/sysresource/{id}")
    public Message delete(@PathVariable("id") String id) {
        sysresourceService.delete(id);
        return new Message();
    }

}
