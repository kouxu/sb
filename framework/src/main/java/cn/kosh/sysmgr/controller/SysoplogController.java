package cn.kosh.sysmgr.controller;

import cn.kosh.framework.domain.OperationType;
import cn.kosh.framework.annotation.ConditionDefault;
import cn.kosh.framework.domain.Condition;
import cn.kosh.framework.domain.Message;
import cn.kosh.sysmgr.condition.SysoplogCondition;
import cn.kosh.sysmgr.domain.Sysoplog;
import cn.kosh.sysmgr.service.SysoplogService;
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
public class SysoplogController {
    @Autowired
    private SysoplogService sysoplogService;

    @PreAuthorize("hasAuthority('oplog_list')")
    @GetMapping(value = "/sysoplog", produces = "application/json")
    public Message list(@ConditionDefault SysoplogCondition condition, @PageableDefault Pageable pageable) {
        Page<Sysoplog> page = sysoplogService.query(condition, pageable);
        return new Message(page);
    }

    @PreAuthorize("hasAuthority('oplog_detail')")
    @GetMapping(value = "/sysoplog/{id}", produces = "application/json")
    public Message get(@PathVariable("id") String id) {
        Sysoplog item = sysoplogService.get(id);
        return new Message(item);
    }

}
