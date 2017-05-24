package cn.kosh.sysmgr.service;

import cn.kosh.framework.domain.OperationType;
import cn.kosh.framework.domain.Condition;
import cn.kosh.sysmgr.dao.SysoplogRepository;
import cn.kosh.sysmgr.domain.Sysoplog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by kosh on 2017/5/14.
 */
@Service
public class SysoplogServiceImpl implements SysoplogService {
    @Autowired
    private SysoplogRepository sysoplogRepository;

    public Sysoplog get(String id) {
        return sysoplogRepository.findOne(id);
    }

    @Transactional
    public Sysoplog save(Sysoplog item) {
        sysoplogRepository.save(item);
        return item;
    }

    public Page<Sysoplog> query(Condition condition, Pageable pageable) {
        return sysoplogRepository.findAll(pageable);
    }
}
