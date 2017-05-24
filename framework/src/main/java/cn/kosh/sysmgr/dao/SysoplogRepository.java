package cn.kosh.sysmgr.dao;

import cn.kosh.framework.jpa.BaseRepository;
import cn.kosh.sysmgr.domain.Sysoplog;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by kosh on 2017/5/20.
 */
@RepositoryRestResource(collectionResourceRel = "sysoplog", path = "sysoplog")
public interface SysoplogRepository extends BaseRepository<Sysoplog, String> {
}
