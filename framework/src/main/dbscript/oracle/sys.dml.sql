-- 组织[root]
INSERT INTO KF.SYS_ORG (ID, PARENT_ID, NAME) VALUES ('root', null, '根组织');
-- 用户[admin]
INSERT INTO KF.SYS_USER (ID, ORG_ID, NAME, PASSWORD, PHONE, EMAIL) VALUES ('admin', 'root', 'admin', 'NJJ4U+qnT2oj03WIM6gxDA==', '18628260919', '7238456@qq.com');
-- 角色[admin]
INSERT INTO KF.SYS_ROLE (ID, NAME, DESCRIPTION) VALUES ('admin', '系统管理员', '系统管理员，不能删除');
-- 角色[admin]给用户[admin]
INSERT INTO KF.SYS_USER_ROLE (ID, USER_ID, ROLE_ID) VALUES ('admin_admin', 'admin', 'admin');

-- 资源[user、role、org、resource、permission、menu、oplog]
INSERT INTO KF.SYS_RESOURCE (ID, PARENT_ID, NAME, CNAME) VALUES ('user', null, 'user', '系统用户');
INSERT INTO KF.SYS_RESOURCE (ID, PARENT_ID, NAME, CNAME) VALUES ('role', null, 'role', '系统角色');
INSERT INTO KF.SYS_RESOURCE (ID, PARENT_ID, NAME, CNAME) VALUES ('org', null, 'org', '系统组织');
INSERT INTO KF.SYS_RESOURCE (ID, PARENT_ID, NAME, CNAME) VALUES ('resource', null, 'resource', '系统资源');
INSERT INTO KF.SYS_RESOURCE (ID, PARENT_ID, NAME, CNAME) VALUES ('permission', null, 'permission', '系统权限');
INSERT INTO KF.SYS_RESOURCE (ID, PARENT_ID, NAME, CNAME) VALUES ('menu', null, 'menu', '系统菜单');
INSERT INTO KF.SYS_RESOURCE (ID, PARENT_ID, NAME, CNAME) VALUES ('oplog', null, 'oplog', '系统操作日志');

-- 资源[user]权限
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('user_list', null, 'user_list', '查询用户');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('user_add', null, 'user_add', '添加用户');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('user_update', null, 'user_update', '修改用户');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('user_replace', null, 'user_replace', '替换用户');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('user_delete', null, 'user_delete', '删除用户');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('user_detail', null, 'user_detail', '用户详情');

-- 资源[user]权限给角色[admin]
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_user_list', 'admin', 'user_list');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_user_add', 'admin', 'user_add');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_user_update', 'admin', 'user_update');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_user_replace', 'admin', 'user_replace');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_user_delete', 'admin', 'user_delete');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_user_detail', 'admin', 'user_detail');

-- 资源[role]权限
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('role_list', null, 'role_list', '查询角色');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('role_add', null, 'role_add', '添加角色');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('role_update', null, 'role_update', '修改角色');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('role_replace', null, 'role_replace', '替换角色');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('role_delete', null, 'role_delete', '删除角色');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('role_detail', null, 'role_detail', '角色详情');

-- 资源[role]权限给角色[admin]
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_role_list', 'admin', 'role_list');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_role_add', 'admin', 'role_add');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_role_update', 'admin', 'role_update');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_role_replace', 'admin', 'role_replace');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_role_delete', 'admin', 'role_delete');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_role_detail', 'admin', 'role_detail');

-- 资源[org]权限
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('org_list', null, 'org_list', '查询组织');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('org_add', null, 'org_add', '添加组织');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('org_update', null, 'org_update', '修改组织');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('org_replace', null, 'org_replace', '替换组织');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('org_delete', null, 'org_delete', '删除组织');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('org_detail', null, 'org_detail', '组织详情');

-- 资源[org]权限给角色[admin]
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_org_list', 'admin', 'org_list');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_org_add', 'admin', 'org_add');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_org_update', 'admin', 'org_update');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_org_replace', 'admin', 'org_replace');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_org_delete', 'admin', 'org_delete');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_org_detail', 'admin', 'org_detail');

-- 资源[resource]权限
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('resource_list', null, 'resource_list', '查询资源');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('resource_add', null, 'resource_add', '添加资源');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('resource_update', null, 'resource_update', '修改资源');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('resource_replace', null, 'resource_replace', '替换资源');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('resource_delete', null, 'resource_delete', '删除资源');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('resource_detail', null, 'resource_detail', '资源详情');

-- 资源[resource]权限给角色[admin]
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_resource_list', 'admin', 'resource_list');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_resource_add', 'admin', 'resource_add');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_resource_update', 'admin', 'resource_update');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_resource_replace', 'admin', 'resource_replace');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_resource_delete', 'admin', 'resource_delete');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_resource_detail', 'admin', 'resource_detail');

-- 资源[permission]权限
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('permission_list', null, 'permission_list', '查询权限');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('permission_add', null, 'permission_add', '添加权限');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('permission_update', null, 'permission_update', '修改权限');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('permission_replace', null, 'permission_replace', '替换权限');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('permission_delete', null, 'permission_delete', '删除权限');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('permission_detail', null, 'permission_detail', '权限详情');

-- 资源[permission]权限给角色[admin]
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_permission_list', 'admin', 'permission_list');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_permission_add', 'admin', 'permission_add');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_permission_update', 'admin', 'permission_update');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_permission_replace', 'admin', 'permission_replace');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_permission_delete', 'admin', 'permission_delete');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_permission_detail', 'admin', 'permission_detail');

-- 资源[menu]权限
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('menu_list', null, 'menu_list', '查询菜单');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('menu_add', null, 'menu_add', '添加菜单');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('menu_update', null, 'menu_update', '修改菜单');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('menu_replace', null, 'menu_replace', '替换菜单');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('menu_delete', null, 'menu_delete', '删除菜单');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('menu_detail', null, 'menu_detail', '菜单详情');

-- 资源[menu]权限给角色[admin]
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_menu_list', 'admin', 'menu_list');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_menu_add', 'admin', 'menu_add');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_menu_update', 'admin', 'menu_update');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_menu_replace', 'admin', 'menu_replace');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_menu_delete', 'admin', 'menu_delete');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_menu_detail', 'admin', 'menu_detail');

-- 资源[oplog]权限
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('oplog_list', null, 'oplog_list', '查询操作日志');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('oplog_add', null, 'oplog_add', '添加操作日志');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('oplog_update', null, 'oplog_update', '修改操作日志');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('oplog_replace', null, 'oplog_replace', '替换操作日志');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('oplog_delete', null, 'oplog_delete', '删除操作日志');
INSERT INTO KF.SYS_PERMISSION (ID, RESOURCE_ID, NAME, CNAME) VALUES ('oplog_detail', null, 'oplog_detail', '操作日志详情');

-- 资源[oplog]权限给角色[admin]
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_oplog_list', 'admin', 'oplog_list');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_oplog_add', 'admin', 'oplog_add');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_oplog_update', 'admin', 'oplog_update');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_oplog_replace', 'admin', 'oplog_replace');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_oplog_delete', 'admin', 'oplog_delete');
INSERT INTO KF.SYS_ROLE_PERMISSION (ID, ROLE_ID, PERMISSION_ID) VALUES ('admin_oplog_detail', 'admin', 'oplog_detail');

COMMIT;