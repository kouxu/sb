package cn.kosh.sysmgr.dao;

import cn.kosh.framework.jpa.BaseRepository;
import cn.kosh.sysmgr.domain.Sysrole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by kosh on 2017/5/14.
 */
@RepositoryRestResource(collectionResourceRel = "sysrole", path = "sysrole")
public interface SysroleRepository extends BaseRepository<Sysrole, String> {

    @Query("select r from Sysrole r where r.id in (select role_id from SysUserRole ur where ur.user_id = ?1) and r.deleted = false")
    List<Sysrole> findSysrolesByUser_id(String user_id);
}
