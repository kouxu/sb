package cn.kosh.sysmgr.dao;

import cn.kosh.framework.jpa.BaseRepository;
import cn.kosh.sysmgr.domain.Sysuser;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by kosh on 2017/5/14.
 */
@RepositoryRestResource(collectionResourceRel = "sysuser", path = "sysuser")
public interface SysuserRepository extends BaseRepository<Sysuser, String> {
    Sysuser findByName(String name);
}
