package cn.kosh.sysmgr.dao;

import cn.kosh.framework.jpa.BaseRepository;
import cn.kosh.sysmgr.domain.Sysorg;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by kosh on 2017/5/14.
 */
@RepositoryRestResource(collectionResourceRel = "sysorg", path = "sysorg")
public interface SysorgRepository extends BaseRepository<Sysorg, String> {
    Sysorg findByName(String name);
}
