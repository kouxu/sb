package cn.kosh.sysmgr.dao;

import cn.kosh.framework.jpa.BaseRepository;
import cn.kosh.sysmgr.domain.Syspermission;
import cn.kosh.sysmgr.domain.Sysrole;
import cn.kosh.sysmgr.domain.Sysuser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by kosh on 2017/5/14.
 */
@RepositoryRestResource(collectionResourceRel = "syspermission", path = "syspermission")
public interface SyspermissionRepository extends BaseRepository<Syspermission, String> {
    @Query("select p from Syspermission p where p.id in (select permission_id from SysRolePermission rp where rp.role_id = ?1) and p.deleted = false")
    List<Syspermission> findSyspermissionsByRole_id(String role_id);
}
