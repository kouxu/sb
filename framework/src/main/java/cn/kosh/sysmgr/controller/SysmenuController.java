package cn.kosh.sysmgr.controller;

import cn.kosh.framework.annotation.ConditionDefault;
import cn.kosh.framework.domain.Message;
import cn.kosh.framework.domain.OperationType;
import cn.kosh.sysmgr.condition.SysmenuCondition;
import cn.kosh.sysmgr.domain.Sysmenu;
import cn.kosh.sysmgr.service.SysmenuService;
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
public class SysmenuController {
    @Autowired
    private SysmenuService sysmenuService;

    @PreAuthorize("hasAuthority('menu_list')")
    @GetMapping(value = "/sysmenu", produces = "application/json")
    public Message list(@ConditionDefault SysmenuCondition condition, @PageableDefault Pageable pageable) {
        Page<Sysmenu> page = sysmenuService.query(condition, pageable);
        return new Message(page);
    }

    @PreAuthorize("hasAuthority('menu_detail')")
    @GetMapping(value = "/sysmenu/{id}", produces = "application/json")
    public Message get(@PathVariable("id") String id) {
        Sysmenu item = sysmenuService.get(id);
        return new Message(item);
    }

    @PreAuthorize("hasAuthority('menu_add')")
    @PostMapping("/sysmenu")
    public Message add(@RequestBody Sysmenu item) {
        item = sysmenuService.save(item, OperationType.ADD);
        return new Message(item);
    }

    @PreAuthorize("hasAuthority('menu_update')")
    @PatchMapping("/sysmenu")
    public Message update(@RequestBody Sysmenu item) {
        item = sysmenuService.save(item, OperationType.UPDATE);
        return new Message(item);
    }

    @PreAuthorize("hasAuthority('menu_replace')")
    @PutMapping("/sysmenu")
    public Message replace(@RequestBody Sysmenu item) {
        item = sysmenuService.save(item, OperationType.REPLACE);
        return new Message(item);
    }

    @PreAuthorize("hasAuthority('menu_delete')")
    @DeleteMapping(value = "/sysmenu/{id}")
    public Message delete(@PathVariable("id") String id) {
        sysmenuService.delete(id);
        return new Message();
    }

}
