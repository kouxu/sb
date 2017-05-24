package cn.kosh.sysmgr.dao;

import cn.kosh.framework.jpa.BaseRepository;
import cn.kosh.sysmgr.domain.Sysresource;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by kosh on 2017/5/14.
 */
@RepositoryRestResource(collectionResourceRel = "sysresource", path = "sysresource")
public interface SysresourceRepository extends BaseRepository<Sysresource, String> {
    Sysresource findByName(String name);
}
