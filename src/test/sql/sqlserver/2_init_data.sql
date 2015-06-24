------------初始化系统超级账号--------------------------------------------------
SET IDENTITY_INSERT CS_ROLE ON;
insert into CS_ROLE (ID,NAME,REMOVED,TYPE) values (1,'Administrator',0,'S');
insert into CS_ROLE (ID,NAME,REMOVED,TYPE) values (2,'DBA',0,'S');
insert into CS_ROLE (ID,NAME,REMOVED,TYPE) values (3,'Monitor',0,'S');
SET IDENTITY_INSERT CS_ROLE OFF;
SET IDENTITY_INSERT CS_USER ON;
insert into CS_USER (ID,LOGIN_NAME,NAME,PASSWORD,REMOVED,STATUS,USERTYPE,AUTHENTIC_TYPE,ACCOUNTTYPE) 
values (1,'admin','admin','0ddb5877c896f43e8734e10b001e7f1eb92889cd',0,1,0,0,1);
SET IDENTITY_INSERT CS_USER OFF;
insert into CS_USER_ROLE (SECURITY_USER_ID,SECURITY_ROLE_ID) values (1,1);
------------初始化系统内置字典--------------------------------------------------
SET IDENTITY_INSERT CF_CODE_TYPE ON;
insert into CF_CODE_TYPE (ID,SYSTEM_TYPE,DISP_ORDER,REMOVED,CODE,NAME,LEVE) values(1,1,0,0,'DyanGroupClass','动态组访问类',5);
insert into CF_CODE_TYPE (ID,SYSTEM_TYPE,DISP_ORDER,REMOVED,CODE,NAME,LEVE) values(2,1,0,0,'image','图片',5);
insert into CF_CODE_TYPE (ID,SYSTEM_TYPE,DISP_ORDER,REMOVED,CODE,NAME,LEVE) values(3,1,0,0,'menu_para','菜单参数',5);
SET IDENTITY_INSERT CF_CODE_TYPE OFF;

SET IDENTITY_INSERT CF_CODE_INFO ON;
insert into CF_CODE_INFO (ID,TYPE_ID,DISP_ORDER,REMOVED,CODE,NAME,DESCRIPTION) values (5,3,0,0,'menu_host','主机路径','....'); 
insert into CF_CODE_INFO (ID,TYPE_ID,DISP_ORDER,REMOVED,CODE,NAME,DESCRIPTION) values (6,3,0,0,'menu_type','菜单类型','....'); 
insert into CF_CODE_INFO (ID,TYPE_ID,DISP_ORDER,REMOVED,CODE,NAME,DESCRIPTION) values (4,1,0,0,'CODE4','默认动态群组实现','defaultDynaGroup');

insert into CF_CODE_INFO (ID,TYPE_ID,DISP_ORDER,REMOVED,CODE,NAME,DESCRIPTION) values (1,2,0,0,'CODE1','组织','images/components_icon/security/org.gif');
insert into CF_CODE_INFO (ID,TYPE_ID,DISP_ORDER,REMOVED,CODE,NAME,DESCRIPTION) values (2,2,0,0,'CODE2','人员','images/components_icon/security/people.gif');
insert into CF_CODE_INFO (ID,TYPE_ID,DISP_ORDER,REMOVED,CODE,NAME,DESCRIPTION) values (3,2,0,0,'CODE3','普通人员','images/components_icon/security/commonPeople.gif');
insert into CF_CODE_INFO (ID,TYPE_ID,DISP_ORDER,REMOVED,CODE,NAME,DESCRIPTION) values (10,2,0,0,'CODE10','行政区划','images/components_icon/security/area.gif');

insert into CF_CODE_INFO (ID,TYPE_ID,CODE_INFO_ID,DISP_ORDER,REMOVED,CODE,NAME,DESCRIPTION) values (7,3,5,0,0,'CODE7','一号机','....'); 
insert into CF_CODE_INFO (ID,TYPE_ID,CODE_INFO_ID,DISP_ORDER,REMOVED,CODE,NAME,DESCRIPTION) values (8,3,6,0,0,'CODE8','BIEE','....'); 
SET IDENTITY_INSERT CF_CODE_INFO OFF;

SET IDENTITY_INSERT CF_EXTEND_PROPERTY_CONFIG ON;
insert into CF_EXTEND_PROPERTY_CONFIG (ID,CLASS_NAME,EXT1) values (1,'com.wondersgroup.framework.security.bo.SecurityUser','MSN');
insert into CF_EXTEND_PROPERTY_CONFIG (ID,CLASS_NAME,EXT1) values (2,'com.wondersgroup.framework.organization.bo.OrganNode','function');
SET IDENTITY_INSERT CF_EXTEND_PROPERTY_CONFIG OFF;

-----------初始化资源类型和操作-------------------------------------------
SET IDENTITY_INSERT CS_ACL_RES_TYPE ON;
insert into CS_ACL_RES_TYPE (ID,NAME,CLASS_NAME,CATALOG,CODE,REMOVED,INHERITTYPE) values (1,'系统菜单','menuService:S',1,'Menu',0,1);
insert into CS_ACL_RES_TYPE (ID,NAME,CLASS_NAME,CATALOG,CODE,REMOVED,INHERITTYPE) values (2,'系统功能','frameWorkService:S',1,'FrameWork',0,2);
insert into CS_ACL_RES_TYPE (ID,NAME,CLASS_NAME,CATALOG,CODE,REMOVED,INHERITTYPE) values (4,'自定义资源','customResourceService:S',3,'CustomFile',0,0);
insert into CS_ACL_RES_TYPE (ID,NAME,CLASS_NAME,CATALOG,CODE,REMOVED,INHERITTYPE) values (5,'页面资源','urlResourceService:S',1,'urlAddr',0,1);
SET IDENTITY_INSERT CS_ACL_RES_TYPE OFF;
SET IDENTITY_INSERT CS_ACL_OPERATION ON;
insert into CS_ACL_OPERATION (ID,NAME,ACL_RES_TYPE_ID,CODE,REMOVED) values (1,'显示',1,'Read1',0);
insert into CS_ACL_OPERATION (ID,NAME,ACL_RES_TYPE_ID,CODE,REMOVED) values (2,'操作',2,'Action',0);
insert into CS_ACL_OPERATION (ID,NAME,ACL_RES_TYPE_ID,CODE,REMOVED) values (4,'操作',4,'operation',0);
insert into CS_ACL_OPERATION (ID,NAME,ACL_RES_TYPE_ID,CODE,REMOVED) values (5,'允许访问',5,'UrlAccess',0);
SET IDENTITY_INSERT CS_ACL_OPERATION OFF;
---------初始化应用节点------------
SET IDENTITY_INSERT CS_WEBAPP_NODE ON;
insert into CS_WEBAPP_NODE (ID,CODE,IS_CUTE_SERIES,NODE_TYPE,REMOVED,CENTRAL_NODE_ID,COMPATABLE,NAME,MAX_INACTIVE_SECS,LOGOUT_STYLE,INDEX_URL,ROOT_MENU_CODE) values(1,'cfconsole',1,0,0,null,1,'系统管理控制台',1800,1,'http://rdserver1:8080/cfconsole','9999');
insert into CS_WEBAPP_NODE (ID,CODE,IS_CUTE_SERIES,NODE_TYPE,REMOVED,CENTRAL_NODE_ID,COMPATABLE,NAME,MAX_INACTIVE_SECS,LOGOUT_STYLE,INDEX_URL,ROOT_MENU_CODE) values(2,'cfcms',1,1,0,null,1,'内容管理系统',1800,1,'http://rdserver1:8080/cfcms','8888');
insert into CS_WEBAPP_NODE (ID,CODE,IS_CUTE_SERIES,NODE_TYPE,REMOVED,CENTRAL_NODE_ID,COMPATABLE,NAME,MAX_INACTIVE_SECS,LOGOUT_STYLE,INDEX_URL,ROOT_MENU_CODE) values(3,'xquery',1,1,0,null,1,'即席查询系统',1800,1,'http://rdserver1:8080/xquery','7777');
SET IDENTITY_INSERT CS_WEBAPP_NODE OFF;
---------初始化菜单项信息------------------------------------------------------------------
SET IDENTITY_INSERT CP_MENU_RESOURCE ON;
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1000, '9999', '系统管理控制台', 1, null, null, 0,  null, null, null);
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1001, '10000000', '统一用户管理', 1, null, null, 0, null, 1000,  null);
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1002, '20000000', '统一权限管理', 1, null, null, 1, null, 1000,  null);
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1003, '30000000', '应用系统管理', 1, null, null, 2, null, 1000,  null);
--insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1004, '40000000', '应用监控管理', 1, null, null, 3, null, 1000,  null);
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1005, '7777', '即席查询系统', 1, null, null, 1,  null, null, null);
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1006, '8888', '内容管理系统', 1, null, null, 2,  null, null, null);
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1007, '10010000', '用户帐号管理', 1, null, null, 0, null, 1001,  null);
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1008, '10020000', '用户组管理', 1, null, null, 1, null, 1001,  null);
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1009, '10030000', '组织机构管理', 1, null, null, 2, null, 1001,  null);
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1010, '20010000', '按用户授权', 1, null, null, 0, null, 1002,  null);
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1011, '20020000', '按群组授权', 1, null, null, 1, null, 1002,  null);
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1081, '20030000', '按组织授权', 1, null, null, 1, null, 1002,  null);
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1012, '20040000', '权限资源管理', 1, null, null, 2, null, 1002,  null);
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1013, '20050000', '身份认证管理', 1, null, null, 3, null, 1002,  null);
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1014, '30010000', '应用节点管理', 1, null, null, 0, null, 1003,  null);
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1015, '30020000', '基础数据管理', 1, null, null, 1, null, 1003,  null);
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1016, '30030000', '门户资源管理', 1, null, null, 2, null, 1003,  null);
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1021, '40050000', '应用监控管理', 1, null, null, 4, null, 1003,  null);
--insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1017, '40010000', '构件运行监控', 1, null, null, 0, null, 1004,  null);
--insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1018, '40020000', '业务流程监控', 1, null, null, 1, null, 1004,  null);
--insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1019, '40030000', '数据访问监控', 1, null, null, 2, null, 1004,  null);
--insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1020, '40040000', '缓存效率监控', 1, null, null, 3, null, 1004,  null);
--insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1021, '40050000', '运行信息查看', 1, null, null, 4, null, 1004,  null);
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1022, '10010100', '维护帐号', 1, null, 'security.enterMaintenanceUserPage', 0, null, 1007,  '../images/nav_icon/user_manager/account_maintenance.gif');
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1023, '10010200', '审核帐号', 1, null, 'security.enterAuditUserPage', 1, null, 1007,  '../images/nav_icon/user_manager/account_check.gif');
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1024, '10010300', '封锁帐号', 1, null, 'security.enterLockUserPage', 2, null, 1007,  '../images/nav_icon/user_manager/account_lock.gif');
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1025, '10010400', '解封帐号', 1, null, 'security.enterUnlockUserPage', 3, null, 1007,  '../images/nav_icon/user_manager/account_unlock.gif');
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1026, '10010500', '注销帐号', 1, null, 'security.enterlogoutUserPage', 4, null, 1007,  '../images/nav_icon/user_manager/account_logout.gif');
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1027, '10020100', '普通群组管理', 1, null, 'security.enterGroupIndex', 0, null, 1008,  '../images/nav_icon/user_manager/common_group.gif');
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1028, '10020200', '动态群组管理', 1, null, 'security.enterDymicGroupIndex', 1, null, 1008,  '../images/nav_icon/user_manager/dyna_group.gif');
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1029, '10030100', '组织机构类型管理', 1, null, 'organTreeType.list', 0, null, 1009,  '../images/nav_icon/user_manager/organ_type.gif');
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1030, '10030200', '组织节点类型管理', 1, null, 'organNodeType.list', 1, null, 1009,  '../images/nav_icon/user_manager/organ_relation.gif');
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1031, '10030300', '组织关系类型管理', 1, null, 'organRelationType.list', 2, null, 1009,  '../images/nav_icon/user_manager/organ_type.gif');
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1077, '10030400', '组织树管理', 1, null, 'organTree.list', 3, null, 1009,  '../images/nav_icon/user_manager/organ_tree.gif');
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1032, '20010100', '授予权限', 1, null, 'security.userAuthList&state=1', 0, null, 1010,  '../images/nav_icon/auth/auth.gif');
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1033, '20010200', '禁止权限', 1, null, 'security.userAuthList&state=2', 1, null, 1010,  '../images/nav_icon/auth/revoke.gif');
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1035, '20010300', '临时授权', 1, null, 'security.userAuthList&state=3', 3, null, 1010,  '../images/nav_icon/auth/temp_auth.gif');
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1036, '20020100', '授予权限', 1, null, 'security.groupAuthList&state=1', 0, null, 1011,  '../images/nav_icon/auth/auth.gif');
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1037, '20020200', '禁止权限', 1, null, 'security.groupAuthList&state=2', 1, null, 1011,  '../images/nav_icon/auth/revoke.gif');
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1078, '20030100', '授予权限', 1, null, 'security.organAuthList&state=1', 0, null, 1081,  '../images/nav_icon/auth/auth.gif');
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1079, '20030200', '禁止权限', 1, null, 'security.organAuthList&state=2', 1, null, 1081,  '../images/nav_icon/auth/revoke.gif');
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1080, '20030300', '委托授权', 1, null, 'security.organAuthList&state=3', 2, null, 1081,  '../images/nav_icon/auth/publish.gif');
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1039, '20040100', '资源类型管理', 1, null, 'resourceType', 0, null, 1012,  '../images/nav_icon/auth/resource_type.gif');
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1040, '20040200', '自定义资源维护', 1, null, 'resource.CustomResourceTypeList', 1, null, 1012,  '../images/nav_icon/auth/custom_resource.gif');
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1083, '20040300', '查看权限分配', 1, null, 'resource.viewAuthDistrib', 2, null, 1012,  '../images/nav_icon/auth/aclDistrib.gif');
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1041, '20050100', '用户代理设置', 1, null, 'security.userProxy', 0, null, 1013,  '../images/nav_icon/auth/proxy.gif');
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1042, '30010100', '系统节点维护', 1, null, 'appNode.list', 0, null, 1014,  '../images/nav_icon/system/sys_node.gif');
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1043, '30010200', '页面访问控制', 1, null, 'url.tree', 1, null, 1014,  '../images/nav_icon/system/url.gif');
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1044, '30020100', '数据字典管理', 1, null, 'dict.maintain', 0, null, 1015,  '../images/nav_icon/system/dict.gif');
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1045, '30020200', '系统参数设置', 1, null, 'syspara.maintain', 1, null, 1015,  '../images/nav_icon/system/sys_params.png');
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1046, '30020300', '扩展属性设置', 1, null, 'extendPropertyConfig', 1, null, 1015,  '../images/nav_icon/system/propertConfig.gif');

insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1049, '30030200', '系统功能设置', 1, null, 'framework.tree', 1, null, 1016,  '../images/nav_icon/system/sys_function.gif');
--insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1050, '40010100', '数据层构件', 1, null, 'manager.dataCompt', 0, null, 1017,  '../images/nav_icon/manager/dataCompt.gif');
--insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1051, '40010200', '业务层构件', 1, null, 'manager.serviceCompt', 1, null, 1017,  '../images/nav_icon/manager/serviceCompt.gif');
--insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1052, '40010300', '展现层构件', 1, null, 'manager.webCompt', 2, null, 1017,  '../images/nav_icon/manager/webCompt.gif');
--insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1053, '40020100', '流程模型监控', 1, null, 'workflow.processModelQuery', 0, null, 1018,  '../images/nav_icon/manager/processmodel_audit.gif');
--insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1054, '40020200', '流程运行监控', 1, null, 'workflow.processRunningQuery', 1, null, 1018,  '../images/nav_icon/manager/processrunning_audit.gif');
--insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1055, '40020300', '工作项监控', 1, null, 'workflow.workitemQuery', 2, null, 1018,  '../images/nav_icon/manager/workitem_audit.gif');
--insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1056, '40020400', '监控所有流程', 1, null, 'workflow.allAudit', 3, null, 1018,  '../images/nav_icon/manager/all_audit.gif');
--insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1057, '40030100', '数据访问配置', 1, null, 'manager.hibernateCfg', 0, null, 1019,  '../images/nav_icon/manager/hibernateCfg.png');
--insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1058, '40030200', '数据实体监控', 1, null, 'manager.entity', 1, null, 1019,  '../images/nav_icon/manager/entity.gif');
--insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1059, '40030300', '查询语句监控', 1, null, 'manager.query', 2, null, 1019,  '../images/nav_icon/manager/query.gif');
--insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1060, '40030400', '实体关联监控', 1, null, 'manager.collection', 3, null, 1019,  '../images/nav_icon/manager/collection.gif');
--insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1061, '40040100', '缓存参数配置', 1, null, 'manager.cacheParams', 0, null, 1020,  '../images/nav_icon/manager/cacheParams.gif');
--insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1062, '40040200', '缓存总体效率', 1, null, 'manager.cacheMonitor', 1, null, 1020,  '../images/nav_icon/manager/cacheMonitor.gif');
--insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1063, '40040300', '构件缓存监控', 1, null, 'manager.compCacheStats', 2, null, 1020,  '../images/nav_icon/manager/compCacheStats.gif');
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1064, '40050100', '系统信息', 1, null, 'manager.systemInfo', 0, null, 1021,  '../images/nav_icon/manager/systemInfo.gif');
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1065, '40050200', '数据源配置', 1, null, 'manager.datasource', 1, null, 1021,  '../images/nav_icon/manager/datasource.gif');
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1066, '40050300', '查询登录日志', 1, null, 'logging.userLoginQuery', 2, null, 1021,  '../images/nav_icon/system/sys_log.gif');
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1067, '40050400', '查看在线用户', 1, null, 'onlineUser.main', 3, null, 1021,  '../images/nav_icon/personal_config/view.gif');

insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1100, '80050000', '权限管理', 1, null, 'cms/uams/user_index.jsp', 4, null, 1006,  null);
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1101, '80010000', '站点管理', 1, null, 'cms/site/sitemanager.jsp', 0, null, 1006,  null);
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1102, '80020000', '文档管理', 1, null, 'cms/doc/sitemanager.jsp', 1, null, 1006,  null);
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1103, '80030000', '模版管理', 1, null, 'cms/template/templatemanager.jsp', 2, null, 1006,  null);
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1104, '80040000', '我的工作', 1, null, null, 3, null, 1006,  null);
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1105, '80040100', '工作列表', 1, null, 'cms/personal/mywork_list.jsp', 0, null, 1104,  null);
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1106, '80040200', '个人文档', 1, null, 'cms/personal/mydoc_list.jsp', 1, null, 1104,  null);
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1107, '80040300', '已呈文档', 1, null, 'cms/personal/mysubmit_list.jsp', 2, null, 1104,  null);
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1108, '80040400', '返工文档', 1, null, 'cms/personal/myback_list.jsp', 3, null, 1104,  null);
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1109, '80040500', '草稿箱', 1, null, 'cms/personal/mydraft_list.jsp', 4, null, 1104,  null);
insert into CP_MENU_RESOURCE (ID, CODE, RESOURCE_NAME, VALID, DESCRIPTION, LINKPATH, DISP_ORDER, BIG_IMAGE, PARENT_RESOURCE_ID, ICON)values (1110, '80040600', '垃圾箱', 1, null, 'cms/personal/mygarbage_list.jsp', 5, null, 1104,  null);
SET IDENTITY_INSERT CP_MENU_RESOURCE OFF;
SET IDENTITY_INSERT CP_CONSOLE_OPERS ON;
insert into CP_CONSOLE_OPERS (ID,DESCRIPTION,PARENT_RESOURCE_ID,RESOURCE_NAME,CODE) 
values (1,'CUTEFRAMEWORK操作资源',null,'CUTEFRAMEWORK操作资源','FrameWork');
SET IDENTITY_INSERT CP_CONSOLE_OPERS OFF;

SET IDENTITY_INSERT CS_ORGAN_NODE_TYPE ON;
insert into CS_ORGAN_NODE_TYPE (ID,DOWN,PEOPLE,IS_TOP,REMOVED,CODE,NAME) values(1,0,1,0,0,'POSITION','职位');
insert into CS_ORGAN_NODE_TYPE (ID,DOWN,PEOPLE,IS_TOP,REMOVED,CODE,NAME) values(2,1,1,0,0,'DEPARTMENT','部门');
SET IDENTITY_INSERT CS_ORGAN_NODE_TYPE OFF;