package cn.kosh.sysmgr.dao;

import cn.kosh.framework.jpa.BaseRepository;
import cn.kosh.sysmgr.domain.Sysmenu;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by kosh on 2017/5/14.
 */
@RepositoryRestResource(collectionResourceRel = "sysmenu", path = "sysmenu")
public interface SysmenuRepository extends BaseRepository<Sysmenu, String> {
    Sysmenu findByName(String name);
}
