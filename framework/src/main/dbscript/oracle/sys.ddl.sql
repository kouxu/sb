create table SYS_USER
(
	ID VARCHAR2(32) not null
		constraint PK_SYS_USER
			primary key,
	ORG_ID VARCHAR2(32),
	NAME VARCHAR2(32),
	PASSWORD VARCHAR2(255),
	PHONE VARCHAR2(32),
	EMAIL VARCHAR2(128),
	DELETED NUMBER default 0,
	CREATE_TIME DATE default SYSDATE
)
/

comment on table SYS_USER is '系统用户'
/

comment on column SYS_USER.ID is '唯一标识'
/

comment on column SYS_USER.ORG_ID is '组织ID'
/

comment on column SYS_USER.NAME is '用户名称'
/

comment on column SYS_USER.PASSWORD is '用户密码'
/

comment on column SYS_USER.PHONE is '手机号码'
/

comment on column SYS_USER.EMAIL is '邮箱地址'
/

comment on column SYS_USER.DELETED is '是否删除：0-未删除；1-已删除'
/

comment on column SYS_USER.CREATE_TIME is '创建时间'
/

create table SYS_OPLOG
(
	ID VARCHAR2(32) not null
		constraint PK_SYS_OPLOG
			primary key,
	TYPE VARCHAR2(32),
	CONTENT VARCHAR2(1000),
	OPUSER VARCHAR2(32),
	CLIENT_IP VARCHAR2(32),
	SERVER_IP VARCHAR2(200) default NULL,
	CREATE_TIME DATE default SYSDATE
)
/

comment on table SYS_OPLOG is '系统操作日志'
/

comment on column SYS_OPLOG.ID is '唯一标识'
/

comment on column SYS_OPLOG.TYPE is '操作类型:ADD-新增；UPDATE-修改；REPLACE-替换；DELETE-删除'
/

comment on column SYS_OPLOG.CONTENT is '操作内容'
/

comment on column SYS_OPLOG.OPUSER is '操作用户'
/

comment on column SYS_OPLOG.CLIENT_IP is '客户端IP地址'
/

comment on column SYS_OPLOG.SERVER_IP is '服务器IP地址'
/

comment on column SYS_OPLOG.CREATE_TIME is '创建时间'
/

create table SYS_ORG
(
	ID VARCHAR2(32) not null
		constraint PK_SYS_ORG
			primary key,
	PARENT_ID VARCHAR2(32),
	NAME VARCHAR2(32),
	DESCRIPTION VARCHAR2(500),
	DELETED NUMBER default 0,
	CREATE_TIME DATE default SYSDATE
)
/

comment on table SYS_ORG is '系统组织'
/

comment on column SYS_ORG.ID is '唯一标识'
/

comment on column SYS_ORG.PARENT_ID is '上级组织ID'
/

comment on column SYS_ORG.NAME is '组织名称'
/

comment on column SYS_ORG.DESCRIPTION is '组织介绍'
/

comment on column SYS_ORG.DELETED is '是否删除：0-未删除；1-已删除'
/

comment on column SYS_ORG.CREATE_TIME is '创建时间'
/

create table SYS_ROLE
(
	ID VARCHAR2(32) not null
		constraint PK_SYS_ROLE
			primary key,
	NAME VARCHAR2(32),
	DESCRIPTION VARCHAR2(500),
	DELETED NUMBER default 0,
	CREATE_TIME DATE default SYSDATE
)
/

comment on table SYS_ROLE is '系统角色'
/

comment on column SYS_ROLE.ID is '唯一标识'
/

comment on column SYS_ROLE.NAME is '角色名称'
/

comment on column SYS_ROLE.DESCRIPTION is '角色描述'
/

comment on column SYS_ROLE.DELETED is '是否删除：0-未删除；1-已删除'
/

comment on column SYS_ROLE.CREATE_TIME is '创建时间'
/

create table SYS_MENU
(
	ID VARCHAR2(32) not null
		constraint PK_SYS_MENU
			primary key,
	PARENT_ID VARCHAR2(32),
	NAME VARCHAR2(32),
	URL VARCHAR2(200),
	TYPE VARCHAR2(32),
	STYLE VARCHAR2(32),
	ORDINAL NUMBER default 0,
	SELECTED NUMBER default 0,
	DELETED NUMBER default 0,
	CREATE_TIME DATE default SYSDATE
)
/

comment on table SYS_MENU is '系统菜单'
/

comment on column SYS_MENU.ID is '唯一标识'
/

comment on column SYS_MENU.PARENT_ID is '父级菜单ID'
/

comment on column SYS_MENU.NAME is '菜单名称'
/

comment on column SYS_MENU.URL is '菜单URL地址'
/

comment on column SYS_MENU.TYPE is '菜单类型：click-没有url的菜单；url-具有url的菜单'
/

comment on column SYS_MENU.STYLE is '菜单样式'
/

comment on column SYS_MENU.ORDINAL is '菜单显示顺序'
/

comment on column SYS_MENU.SELECTED is '默认选中的菜单'
/

comment on column SYS_MENU.DELETED is '是否删除：0-未删除；1-已删除'
/

comment on column SYS_MENU.CREATE_TIME is '创建时间'
/

create table SYS_PERMISSION
(
	ID VARCHAR2(32) not null
		constraint PK_SYS_PERMISSION
			primary key,
	RESOURCE_ID VARCHAR2(32),
	NAME VARCHAR2(32),
	CNAME VARCHAR2(100),
	DELETED NUMBER default 0,
	CREATE_TIME DATE default SYSDATE
)
/

comment on table SYS_PERMISSION is '系统权限'
/

comment on column SYS_PERMISSION.ID is '唯一标识'
/

comment on column SYS_PERMISSION.RESOURCE_ID is '资源ID'
/

comment on column SYS_PERMISSION.NAME is '权限英文名称，开发鉴权使用'
/

comment on column SYS_PERMISSION.CNAME is '权限中文名称，用户授权使用'
/

comment on column SYS_PERMISSION.DELETED is '是否删除：0-未删除；1-已删除'
/

comment on column SYS_PERMISSION.CREATE_TIME is '创建时间'
/

create table SYS_RESOURCE
(
	ID VARCHAR2(32) not null
		constraint PK_SYS_RESOURCE
			primary key,
	PARENT_ID VARCHAR2(32),
	NAME VARCHAR2(32),
	CNAME VARCHAR2(32),
	DELETED NUMBER default 0,
	CREATE_TIME DATE default SYSDATE
)
/

comment on column SYS_RESOURCE.ID is '唯一标识'
/

comment on column SYS_RESOURCE.PARENT_ID is '父级资源ID'
/

comment on column SYS_RESOURCE.NAME is '资源英文名称'
/

comment on column SYS_RESOURCE.CNAME is '资源中文名称'
/

comment on column SYS_RESOURCE.DELETED is '是否删除：0-未删除；1-已删除'
/

comment on column SYS_RESOURCE.CREATE_TIME is '创建时间'
/

create table SYS_ROLE_PERMISSION
(
	ID VARCHAR2(32) not null
		constraint PK_SYS_ROLE_PERMISSION
			primary key,
	ROLE_ID VARCHAR2(32),
	PERMISSION_ID VARCHAR2(32)
)
/

comment on table SYS_ROLE_PERMISSION is '系统角色权限'
/

comment on column SYS_ROLE_PERMISSION.ID is '唯一标识'
/

comment on column SYS_ROLE_PERMISSION.ROLE_ID is '角色ID'
/

comment on column SYS_ROLE_PERMISSION.PERMISSION_ID is '权限ID'
/

create table SYS_USER_ROLE
(
	ID VARCHAR2(32) not null
		constraint PK_SYS_USER_ROLE
			primary key,
	USER_ID VARCHAR2(32),
	ROLE_ID VARCHAR2(32)
)
/

comment on table SYS_USER_ROLE is '系统用户角色'
/

comment on column SYS_USER_ROLE.ID is '唯一标识'
/

comment on column SYS_USER_ROLE.USER_ID is '用户ID'
/

comment on column SYS_USER_ROLE.ROLE_ID is '角色ID'
/

