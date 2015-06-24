/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     2011-3-15 10:25:30                           */
/*==============================================================*/

alter table CD_ATTRIBUTE_PRIVILEGE
   drop constraint PK_CF_334;

alter table CF_CODE_INFO
   drop constraint PK_CF_500;

alter table CF_CODE_INFO
   drop constraint PK_CF_300;

alter table CF_DEMO_COAUDIT
   drop constraint PK_CF_333;

alter table CF_DEMO_DRAFT
   drop constraint PK_CF_335;

alter table CF_DEMO_DRAFT
   drop constraint PK_CF_336;

alter table CF_DEMO_DRAFT
   drop constraint PK_CF_337;

alter table CF_SESSION_RECORD
   drop constraint PK_CF_20;

alter table CP_CONSOLE_OPERS
   drop constraint PK_CF_21;

alter table CP_INDIVIDUAL_VIEW
   drop constraint PK_CF_22;

alter table CP_INDIVIDUAL_VIEW
   drop constraint PK_CF_23;

alter table CP_INDIVIDUAL_VIEW
   drop constraint PK_CF_24;

alter table CP_MENU_RESOURCE
   drop constraint PK_CF_122;

alter table CP_MENU_RESOURCE
   drop constraint PK_CF_11;

alter table CP_MENU_RESOURCE
   drop constraint PK_CF_12;

alter table CP_MENU_RESOURCE
   drop constraint PK_CF_9;

alter table CP_MENU_RESOURCE
   drop constraint PK_CF_10;

alter table CP_URL_RESOURCE
   drop constraint PK_CF_14;

alter table CR_PROXY_MENU
   drop constraint PK_CF_16;

alter table CR_PROXY_MENU
   drop constraint PK_CF_17;

alter table CS_ACC_PERMISSION
   drop constraint PK_CF_121;

alter table CS_ACC_PERMISSION
   drop constraint PK_CF_25;

alter table CS_ACL_OPERATION
   drop constraint PK_CF_27;

alter table CS_ACL_PERMISSION
   drop constraint PK_CF_29;

alter table CS_ACL_RESOURCE
   drop constraint PK_CF_34;

alter table CS_ACL_RES_TYPE
   drop constraint PK_CF_32;

alter table CS_ACTION_RESOURCE
   drop constraint PK_CF_37;

alter table CS_CUSTOM_RESOURCE
   drop constraint PK_CF_39;

alter table CS_CUSTOM_RESOURCE
   drop constraint PK_CF_40;

alter table CS_GROUP_ROLE
   drop constraint PK_CF_43;

alter table CS_GROUP_ROLE
   drop constraint PK_CF_44;

alter table CS_ORGAN_EXTEND_PROPERTY
   drop constraint PK_CF_339;

alter table CS_ORGAN_MODEL
   drop constraint PK_CF_46;

alter table CS_ORGAN_MODEL
   drop constraint PK_CF_47;

alter table CS_ORGAN_MODEL
   drop constraint PK_CF_48;

alter table CS_ORGAN_NODE
   drop constraint PK_CF_50;

alter table CS_ORGAN_NODE
   drop constraint PK_CF_51;

alter table CS_ORGAN_NODE
   drop constraint PK_CF_52;

alter table CS_ORGAN_RELATION
   drop constraint PK_CF_55;

alter table CS_ORGAN_RELATION
   drop constraint PK_CF_56;

alter table CS_ORGAN_RELATION
   drop constraint PK_CF_57;

alter table CS_ORGAN_ROLE
   drop constraint PK_CF_60;

alter table CS_ORGAN_ROLE
   drop constraint PK_CF_61;

alter table CS_ORGAN_RULE
   drop constraint PK_CF_63;

alter table CS_ORGAN_RULE
   drop constraint PK_CF_64;

alter table CS_ORGAN_TREE
   drop constraint PK_CF_66;

alter table CS_PROXY_USER
   drop constraint PK_CF_69;

alter table CS_PUB_PERMISSION
   drop constraint PK_CF_71;

alter table CS_PUB_PERMISSION
   drop constraint PK_CF_72;

alter table CS_RESOURCE_GROUP
   drop constraint PK_CF_74;

alter table CS_REV_PERMISSION
   drop constraint PK_CF_76;

alter table CS_REV_PERMISSION
   drop constraint PK_CF_77;

alter table CS_ROLE
   drop constraint PK_CF_80;

alter table CS_USER_APPNODE
   drop constraint PK_CF_83;

alter table CS_USER_APPNODE
   drop constraint PK_CF_84;

alter table CS_USER_EXTEND_PROPERTY
   drop constraint PK_CF_338;

alter table CS_USER_GROUP
   drop constraint PK_CF_86;

alter table CS_USER_GROUP
   drop constraint PK_CF_123;

alter table CS_USER_LOGIN
   drop constraint PK_CF_340;

alter table CS_USER_ORGANNODE
   drop constraint PK_CF_88;

alter table CS_USER_ORGANNODE
   drop constraint PK_CF_89;

alter table CS_USER_ROLE
   drop constraint PK_CF_91;

alter table CS_USER_ROLE
   drop constraint PK_CF_92;

alter table CW_ACT_INSTANCE
   drop constraint PK_CF_97;

alter table CW_PROCESS_DATA
   drop constraint PK_CF_106;

alter table CW_PROCESS_INSTANCE
   drop constraint PK_CF_108;

alter table CW_TIME_ACTIVITY
   drop constraint PK_CF_111;

alter table CW_TRANS_INSTANCE
   drop constraint PK_CF_113;

alter table CW_TRANS_INSTANCE
   drop constraint PK_CF_115;

alter table CW_TRANS_INSTANCE
   drop constraint PK_CF_116;

alter table CW_WORK_ITEMS
   drop constraint PK_CF_118;

alter table CW_WORK_PERSON
   drop constraint PK_CF_120;

drop table CD_ATTRIBUTE_PRIVILEGE;

drop table CF_CODE_INFO;

drop table CF_CODE_TYPE;

drop table CF_DEMO_COAUDIT;

drop table CF_DEMO_DRAFT;

drop table CF_EXTEND_PROPERTY_CONFIG;

drop table CF_LOG;

drop table CF_SESSION_RECORD;

drop table CF_SESSION_SET;

drop table CM_LEAVE_APPLY;

drop table CM_URL_RESOURCE;

drop table CP_CONSOLE_OPERS;

drop table CP_INDIVIDUAL_VIEW;

drop table CP_MENU_RESOURCE;

drop table CP_URL_RESOURCE;

drop table CR_PROXY_MENU;

drop table CS_ACC_PERMISSION;

drop table CS_ACL_OPERATION;

drop table CS_ACL_PERMISSION;

drop table CS_ACL_RESOURCE;

drop table CS_ACL_RES_TYPE;

drop table CS_ACTION_RESOURCE;

drop table CS_CUSTOM_RESOURCE;

drop table CS_GROUP;

drop table CS_GROUP_ROLE;

drop table CS_ORGAN_EXTEND_PROPERTY;

drop table CS_ORGAN_MODEL;

drop table CS_ORGAN_NODE;

drop table CS_ORGAN_NODE_TYPE;

drop table CS_ORGAN_RELATION;

drop table CS_ORGAN_RELATION_TYPE;

drop table CS_ORGAN_ROLE;

drop table CS_ORGAN_RULE;

drop table CS_ORGAN_TREE;

drop table CS_ORGAN_TREE_TYPE;

drop table CS_PROXY_USER;

drop table CS_PUB_PERMISSION;

drop table CS_RESOURCE_GROUP;

drop table CS_REV_PERMISSION;

drop table CS_ROLE;

drop table CS_USER;

drop table CS_USER_APPNODE;

drop table CS_USER_EXTEND_PROPERTY;

drop table CS_USER_GROUP;

drop table CS_USER_LOGIN;

drop table CS_USER_ORGANNODE;

drop table CS_USER_ROLE;

drop table CS_WEBAPP_NODE;

drop table CW_ACT_INSTANCE;

drop table CW_AUDIT_ACT_INST;

drop table CW_AUDIT_CONFIG;

drop table CW_AUDIT_PDATA;

drop table CW_AUDIT_PINSTANCE;

drop table CW_AUDIT_PMODEL;

drop table CW_AUDIT_WORKITEM;

drop table CW_CON_INSTANCE;

drop table CW_PROCESS_DATA;

drop table CW_PROCESS_INSTANCE;

drop table CW_PROCESS_MODEL;

drop table CW_TIME_ACTIVITY;

drop table CW_TRANS_INSTANCE;

drop table CW_WORK_ITEMS;

drop table CW_WORK_PERSON;

/*==============================================================*/
/* Table: CD_ATTRIBUTE_PRIVILEGE                                */
/*==============================================================*/
create table CD_ATTRIBUTE_PRIVILEGE (
   ID                   NUMERIC(19)          not null,
   ATTRIBUTE_NAME       VARCHAR(255)         null,
   GROUP_ID             NUMERIC(19)          null,
   PRIVILEGE            NUMERIC(19)          null,
   constraint PK_CF_610 primary key (ID)
);

/*==============================================================*/
/* Table: CF_CODE_INFO                                          */
/*==============================================================*/
create table CF_CODE_INFO (
   ID                   NUMERIC(19)          not null,
   CODE_INFO_ID         NUMERIC(19)          null,
   TYPE_ID              NUMERIC(19)          null,
   DISP_ORDER           NUMERIC(10)          null,
   REMARK1              VARCHAR(255)         null,
   REMARK2              VARCHAR(255)         null,
   OPERATE_TIME         NUMERIC(19)          null,
   OPERATOR             VARCHAR(50)          null,
   REMOVED              NUMERIC(10)          null,
   CODE                 VARCHAR(100)         null,
   NAME                 VARCHAR(255)         not null,
   DESCRIPTION          VARCHAR(255)         null,
   constraint PK_CF_602 primary key (ID)
);

/*==============================================================*/
/* Table: CF_CODE_TYPE                                          */
/*==============================================================*/
create table CF_CODE_TYPE (
   ID                   NUMERIC(19)          not null,
   SYSTEM_TYPE          NUMERIC(10)          null,
   DISP_ORDER           NUMERIC(10)          null,
   LEVE                 NUMERIC(10)          null,
   OPERATE_TIME         NUMERIC(19)          null,
   OPERATOR             VARCHAR(50)          null,
   REMOVED              NUMERIC(10)          null,
   CODE                 VARCHAR(100)         null,
   NAME                 VARCHAR(255)         not null,
   DESCRIPTION          VARCHAR(255)         null,
   EXTERNAL_MAPPING     VARCHAR(255)         null,
   STORAGE_TYPE         NUMERIC(10)          null,
   constraint PK_CF_1 primary key (ID)
);

/*==============================================================*/
/* Table: CF_DEMO_COAUDIT                                       */
/*==============================================================*/
create table CF_DEMO_COAUDIT (
   ID                   NUMERIC(19)          not null,
   CONTENT              VARCHAR(255)         null,
   CREATE_DATE          DATE                 null,
   CREATOR              VARCHAR(255)         null,
   CO_AUDIT_ID          NUMERIC(19)          null,
   constraint PK_CF_607 primary key (ID)
);

/*==============================================================*/
/* Table: CF_DEMO_DRAFT                                         */
/*==============================================================*/
create table CF_DEMO_DRAFT (
   ID                   NUMERIC(19)          not null,
   PROCESS_INSTANCE     VARCHAR(255)         null,
   CONTENT              VARCHAR(255)         null,
   CREATOR              VARCHAR(255)         null,
   TITLE                VARCHAR(255)         null,
   TYPE                 NUMERIC(19)          null,
   CREATE_DATE          DATE                 null,
   AFFILIATED_COMMENT   VARCHAR(255)         null,
   AFFILIATED_DATE      DATE                 null,
   ARCHIVE_COMMENT      VARCHAR(255)         null,
   ARCHIVE_DATE         DATE                 null,
   AUDITOR              VARCHAR(255)         null,
   AUDITOR_DATE         DATE                 null,
   CONFIDENTIAL_LEVEL   NUMERIC(19)          null,
   FILE_CODE            VARCHAR(255)         null,
   HEADER_COMMENT       VARCHAR(255)         null,
   HEADER_DATE          DATE                 null,
   OFFICIAL_COMMENT     VARCHAR(255)         null,
   OFFICIAL_DATE        DATE                 null,
   PRINT_COMMENT        VARCHAR(255)         null,
   PRINT_DATE           DATE                 null,
   PRIORITY             NUMERIC(19)          null,
   VERIFIED_COMMENT     VARCHAR(255)         null,
   VERIFIED_DATE        DATE                 null,
   constraint PK_CF_608 primary key (ID)
);

/*==============================================================*/
/* Table: CF_EXTEND_PROPERTY_CONFIG                             */
/*==============================================================*/
create table CF_EXTEND_PROPERTY_CONFIG (
   ID                   NUMERIC(19) null,
   CLASS_NAME           VARCHAR(255)         null,
   EXT1                 VARCHAR(255)         null,
   EXT2                 VARCHAR(255)         null,
   EXT3                 VARCHAR(255)         null,
   EXT4                 VARCHAR(255)         null,
   EXT5                 VARCHAR(255)         null,
   EXT6                 VARCHAR(255)         null,
   EXT7                 VARCHAR(255)         null,
   EXT8                 VARCHAR(255)         null,
   EXT9                 VARCHAR(255)         null,
   EXT10                VARCHAR(255)         null,
   EXT11                VARCHAR(255)         null,
   EXT12                VARCHAR(255)         null,
   EXT13                VARCHAR(255)         null,
   EXT14                VARCHAR(255)         null,
   EXT15                VARCHAR(255)         null,
   EXT16                VARCHAR(255)         null,
   EXT17                VARCHAR(255)         null,
   EXT18                VARCHAR(255)         null,
   EXT19                VARCHAR(255)         null,
   EXT20                VARCHAR(255)         null,
   EXT21                VARCHAR(255)         null,
   EXT22                VARCHAR(255)         null,
   EXT23                VARCHAR(255)         null,
   EXT24                VARCHAR(255)         null,
   EXT25                VARCHAR(255)         null,
   EXT26                VARCHAR(255)         null,
   EXT27                VARCHAR(255)         null,
   EXT28                VARCHAR(255)         null,
   EXT29                VARCHAR(255)         null,
   EXT30                VARCHAR(255)         null,
   EXT31                VARCHAR(255)         null,
   EXT32                VARCHAR(255)         null,
   EXT33                VARCHAR(255)         null,
   EXT34                VARCHAR(255)         null,
   EXT35                VARCHAR(255)         null,
   EXT36                VARCHAR(255)         null,
   EXT37                VARCHAR(255)         null,
   EXT38                VARCHAR(255)         null,
   EXT39                VARCHAR(255)         null,
   EXT40                VARCHAR(255)         null
);

/*==============================================================*/
/* Table: CF_LOG                                                */
/*==============================================================*/
create table CF_LOG (
   ID                   NUMERIC(19)          not null,
   LOGINDATE            DATE                 null,
   OPERDATE             DATE                 null,
   USERID               VARCHAR(20)          null,
   USERNAME             VARCHAR(50)          null,
   LOGINFO              TEXT                 null,
   FULLCLASSNAME        VARCHAR(200)         null,
   ERROR_MESSAGE        TEXT                 null,
   constraint PK_CF_2 primary key (ID)
);

/*==============================================================*/
/* Table: CF_SESSION_RECORD                                     */
/*==============================================================*/
create table CF_SESSION_RECORD (
   ID                   NUMERIC(19)          not null,
   SESSION_KEY          VARCHAR(20)          not null,
   SESSIONSET_ID        NUMERIC(19)          null,
   SESSION_VALUE        CHAR                 null,
   constraint PK_CF_3 primary key (ID)
);

/*==============================================================*/
/* Table: CF_SESSION_SET                                        */
/*==============================================================*/
create table CF_SESSION_SET (
   ID                   NUMERIC(19)          not null,
   SESSION_ID           VARCHAR(100)         not null,
   CLIENT_ID            VARCHAR(100)         null,
   constraint PK_CF_4 primary key (ID)
);

/*==============================================================*/
/* Table: CM_LEAVE_APPLY                                        */
/*==============================================================*/
create table CM_LEAVE_APPLY (
   ID                   NUMERIC(19)          not null,
   NAME                 VARCHAR(255)         null,
   USER_ID              VARCHAR(255)         null,
   APPLY_DATE           DATE                 null,
   TYPE                 INT4                 null,
   CONTENT              VARCHAR(255)         null,
   FROM_DATE            DATE                 null,
   TO_DATE              DATE                 null,
   COUNT                INT4                 null,
   LEADER_SIGN          NUMERIC(1)           null,
   MANAGER_SIGN         NUMERIC(1)           null,
   constraint PK_CF_606 primary key (ID)
);

/*==============================================================*/
/* Table: CM_URL_RESOURCE                                       */
/*==============================================================*/
create table CM_URL_RESOURCE (
   ID                   NUMERIC(19)          not null,
   RESOURCE_NAME        VARCHAR(255)         null,
   DESCRIPTION          VARCHAR(255)         null,
   constraint PK_CF_609 primary key (ID)
);

/*==============================================================*/
/* Table: CP_CONSOLE_OPERS                                      */
/*==============================================================*/
create table CP_CONSOLE_OPERS (
   ID                   NUMERIC(19)          not null,
   DESCRIPTION          VARCHAR(255)         null,
   PARENT_RESOURCE_ID   NUMERIC(19)          null,
   RESOURCE_NAME        VARCHAR(255)         null,
   CODE                 VARCHAR(100)         null,
   constraint PK_CF_5 primary key (ID),
   constraint PK_CF_6 unique (CODE)
);

/*==============================================================*/
/* Table: CP_INDIVIDUAL_VIEW                                    */
/*==============================================================*/
create table CP_INDIVIDUAL_VIEW (
   ID                   NUMERIC(19)          not null,
   DESCRIPTION          VARCHAR(255)         null,
   DISP_ORDER           NUMERIC(10)          null,
   IMAGE                VARCHAR(255)         null,
   MENU_ID              NUMERIC(19)          null,
   NAME                 VARCHAR(255)         null,
   PARENT_VIEW_ID       NUMERIC(19)          null,
   USER_ID              NUMERIC(19)          null,
   constraint PK_CF_7 primary key (ID)
);

/*==============================================================*/
/* Table: CP_MENU_RESOURCE                                      */
/*==============================================================*/
create table CP_MENU_RESOURCE (
   ID                   NUMERIC(19)          not null,
   CODE                 VARCHAR(255)         null,
   RESOURCE_NAME        VARCHAR(255)         null,
   LINKPATH             VARCHAR(100)         null,
   DISP_ORDER           NUMERIC(10)          null,
   VALID                NUMERIC(1)           null,
   PARENT_RESOURCE_ID   NUMERIC(19)          null,
   HANDLER              VARCHAR(255)         null,
   CLS                  VARCHAR(255)         null,
   MENU_TYPE            NUMERIC(19)          null,
   TARGET               VARCHAR(255)         null,
   BIG_IMAGE            NUMERIC(19)          null,
   SMALL_IMAGE          NUMERIC(19)          null,
   BACKUP_IMAGE         VARCHAR(100)         null,
   BACKUP_IMAGE2        VARCHAR(100)         null,
   HOST                 NUMERIC(19)          null,
   ICON                 VARCHAR(255)         null,
   DESCRIPTION          VARCHAR(255)         null,
   EXT1                 VARCHAR(255)         null,
   EXT2                 VARCHAR(255)         null,
   EXT3                 VARCHAR(255)         null,
   EXT4                 VARCHAR(255)         null,
   EXT5                 VARCHAR(255)         null,
   EXT6                 VARCHAR(255)         null,
   EXT7                 VARCHAR(255)         null,
   EXT8                 VARCHAR(255)         null,
   constraint PK_CF_8 primary key (ID)
);

/*==============================================================*/
/* Table: CP_URL_RESOURCE                                       */
/*==============================================================*/
create table CP_URL_RESOURCE (
   ID                   NUMERIC(19)          not null,
   RESOURCE_NAME        VARCHAR(255)         null,
   PARENT_RESOURCE_ID   NUMERIC(19)          null,
   DISP_ORDER           NUMERIC(10)          null,
   LINKPATH             VARCHAR(100)         null,
   PARAMETERS           VARCHAR(100)         null,
   DESCRIPTION          VARCHAR(200)         null,
   CODE                 VARCHAR(200)         not null,
   constraint PK_CF_13 primary key (ID)
);

/*==============================================================*/
/* Table: CR_PROXY_MENU                                         */
/*==============================================================*/
create table CR_PROXY_MENU (
   PROXY_ID             NUMERIC(19)          not null,
   MENU_ID              NUMERIC(19)          not null,
   constraint PK_CF_15 primary key (PROXY_ID, MENU_ID)
);

/*==============================================================*/
/* Table: CS_ACC_PERMISSION                                     */
/*==============================================================*/
create table CS_ACC_PERMISSION (
   ACL_PERMISSION_ID    NUMERIC(19)          not null,
   SECURITY_ROLE_ID     NUMERIC(19)          not null,
   constraint PK_CF_18 primary key (SECURITY_ROLE_ID, ACL_PERMISSION_ID)
);

/*==============================================================*/
/* Table: CS_ACL_OPERATION                                      */
/*==============================================================*/
create table CS_ACL_OPERATION (
   ID                   NUMERIC(19)          not null,
   CODE                 VARCHAR(100)         null,
   NAME                 VARCHAR(255)         not null,
   DESCRIPTION          VARCHAR(255)         null,
   ACL_RES_TYPE_ID      NUMERIC(19)          null,
   OPERATE_TIME         NUMERIC(19)          null,
   OPERATOR             VARCHAR(50)          null,
   REMOVED              NUMERIC(10)          null,
   constraint PK_CF_26 primary key (ID)
);

/*==============================================================*/
/* Table: CS_ACL_PERMISSION                                     */
/*==============================================================*/
create table CS_ACL_PERMISSION (
   ID                   NUMERIC(19)          not null,
   ACL_OPERATION_ID     NUMERIC(19)          null,
   ACL_RESOURCE_ID      NUMERIC(19)          null,
   START_TIME           DATE                 null,
   DUE_TIME             DATE                 null,
   constraint PK_CF_28 primary key (ID)
);

/*==============================================================*/
/* Table: CS_ACL_RESOURCE                                       */
/*==============================================================*/
create table CS_ACL_RESOURCE (
   ID                   NUMERIC(19)          not null,
   NAME                 VARCHAR(255)         null,
   DESCRIPTION          VARCHAR(255)         null,
   PARENT_ACL_RES_ID    NUMERIC(19)          null,
   ACL_RES_TYPE_ID      NUMERIC(19)          null,
   NATIVE_RESOURCE_ID   VARCHAR(255)         null,
   constraint PK_CF_33 primary key (ID)
);

/*==============================================================*/
/* Table: CS_ACL_RES_TYPE                                       */
/*==============================================================*/
create table CS_ACL_RES_TYPE (
   ID                   NUMERIC(19)          not null,
   WEBAPP_NODE_ID       NUMERIC(19)          null,
   CATALOG              NUMERIC(10)          null,
   CODE                 VARCHAR(100)         null,
   DESCRIPTION          VARCHAR(255)         null,
   NAME                 VARCHAR(100)         not null,
   CLASS_NAME           VARCHAR(100)         null,
   INHERITTYPE          NUMERIC(10)          null,
   OPERATE_TIME         NUMERIC(19)          null,
   OPERATOR             VARCHAR(50)          null,
   REMOVED              NUMERIC(10)          null,
   constraint PK_CF_31 primary key (ID)
);

/*==============================================================*/
/* Table: CS_ACTION_RESOURCE                                    */
/*==============================================================*/
create table CS_ACTION_RESOURCE (
   ID                   NUMERIC(19)          not null,
   DESCRIPTION          VARCHAR(255)         null,
   PARENT_RESOURCE_ID   NUMERIC(19)          null,
   RESOURCE_NAME        VARCHAR(255)         null,
   CLASS_NAME           VARCHAR(255)         not null,
   METHOD_NAME          VARCHAR(100)         not null,
   constraint PK_CF_128 primary key (ID)
);

/*==============================================================*/
/* Table: CS_CUSTOM_RESOURCE                                    */
/*==============================================================*/
create table CS_CUSTOM_RESOURCE (
   ID                   NUMERIC(19)          not null,
   CODE                 VARCHAR(100)         null,
   DESCRIPTION          VARCHAR(255)         null,
   PARENT_RESOURCE_ID   NUMERIC(19)          null,
   NAME                 VARCHAR(255)         null,
   ACL_RES_TYPE_ID      NUMERIC(19)          null,
   OPERATE_TIME         NUMERIC(19)          null,
   OPERATOR             VARCHAR(50)          null,
   REMOVED              NUMERIC(10)          null,
   constraint PK_CF_129 primary key (ID)
);

/*==============================================================*/
/* Table: CS_GROUP                                              */
/*==============================================================*/
create table CS_GROUP (
   ID                   NUMERIC(19)          not null,
   DYNAACCESSCLASS      VARCHAR(255)         null,
   DYANPARAMS           VARCHAR(255)         null,
   DYNA                 NUMERIC(10)          null,
   OPERATE_TIME         NUMERIC(19)          null,
   OPERATOR             VARCHAR(50)          null,
   REMOVED              NUMERIC(10)          null,
   CODE                 VARCHAR(100)         null,
   NAME                 VARCHAR(255)         not null,
   DESCRIPTION          VARCHAR(255)         null,
   constraint PK_CF_41 primary key (ID)
);

/*==============================================================*/
/* Table: CS_GROUP_ROLE                                         */
/*==============================================================*/
create table CS_GROUP_ROLE (
   SECURITY_GROUP_ID    NUMERIC(19)          not null,
   SECURITY_ROLE_ID     NUMERIC(19)          not null,
   constraint PK_CF_42 primary key (SECURITY_GROUP_ID, SECURITY_ROLE_ID)
);

/*==============================================================*/
/* Table: CS_ORGAN_EXTEND_PROPERTY                              */
/*==============================================================*/
create table CS_ORGAN_EXTEND_PROPERTY (
   ID                   NUMERIC(19)          not null,
   EXT1                 VARCHAR(255)         null,
   EXT2                 VARCHAR(255)         null,
   EXT3                 VARCHAR(255)         null,
   EXT4                 VARCHAR(255)         null,
   EXT5                 VARCHAR(255)         null,
   EXT6                 VARCHAR(255)         null,
   EXT7                 VARCHAR(255)         null,
   EXT8                 VARCHAR(255)         null,
   EXT9                 VARCHAR(255)         null,
   EXT10                VARCHAR(255)         null,
   EXT11                VARCHAR(255)         null,
   EXT12                VARCHAR(255)         null,
   EXT13                VARCHAR(255)         null,
   EXT14                VARCHAR(255)         null,
   EXT15                VARCHAR(255)         null,
   EXT16                VARCHAR(255)         null,
   EXT17                VARCHAR(255)         null,
   EXT18                VARCHAR(255)         null,
   EXT19                VARCHAR(255)         null,
   EXT20                VARCHAR(255)         null,
   EXT21                VARCHAR(255)         null,
   EXT22                VARCHAR(255)         null,
   EXT23                VARCHAR(255)         null,
   EXT24                VARCHAR(255)         null,
   EXT25                VARCHAR(255)         null,
   EXT26                VARCHAR(255)         null,
   EXT27                VARCHAR(255)         null,
   EXT28                VARCHAR(255)         null,
   EXT29                VARCHAR(255)         null,
   EXT30                VARCHAR(255)         null,
   EXT31                VARCHAR(255)         null,
   EXT32                VARCHAR(255)         null,
   EXT33                VARCHAR(255)         null,
   EXT34                VARCHAR(255)         null,
   EXT35                VARCHAR(255)         null,
   EXT36                VARCHAR(255)         null,
   EXT37                VARCHAR(255)         null,
   EXT38                VARCHAR(255)         null,
   EXT39                VARCHAR(255)         null,
   EXT40                VARCHAR(255)         null,
   ORGAN_NODE_ID        NUMERIC(19)          null,
   constraint PK_CS_ORGAN_EXTEND_PROPERTY primary key (ID)
);

/*==============================================================*/
/* Table: CS_ORGAN_MODEL                                        */
/*==============================================================*/
create table CS_ORGAN_MODEL (
   ID                   NUMERIC(19)          not null,
   LFT                  NUMERIC(10)          null,
   RGT                  NUMERIC(10)          null,
   NODESTATUS           VARCHAR(255)         null,
   ORDERS               NUMERIC(19)          null,
   RANK                 VARCHAR(255)         null,
   ORG_NODE_ID          NUMERIC(19)          null,
   ORG_TREE_ID          NUMERIC(19)          null,
   PARENT_NODE_ID       NUMERIC(19)          null,
   OPERATE_TIME         NUMERIC(19)          null,
   OPERATOR             VARCHAR(50)          null,
   REMOVED              NUMERIC(10)          null,
   constraint PK_CF_45 primary key (ID)
);

/*==============================================================*/
/* Table: CS_ORGAN_NODE                                         */
/*==============================================================*/
create table CS_ORGAN_NODE (
   ID                   NUMERIC(19)          not null,
   CREATE_TIME          DATE                 null,
   DEPT_ADDRESS         VARCHAR(255)         null,
   ICON                 NUMERIC(19)          null,
   ORGAN_NODE_TYPE_ID   NUMERIC(19)          null,
   PRINCIPAL            VARCHAR(255)         null,
   PRINCIPAL_PHONE      VARCHAR(255)         null,
   FAX                  VARCHAR(255)         null,
   ADMIN_USER_ID        NUMERIC(19)          null,
   OPERATE_TIME         NUMERIC(19)          null,
   OPERATOR             VARCHAR(50)          null,
   REMOVED              NUMERIC(10)          null,
   CODE                 VARCHAR(100)         null,
   NAME                 VARCHAR(255)         not null,
   DESCRIPTION          VARCHAR(255)         null,
   constraint PK_CF_49 primary key (ID)
);

/*==============================================================*/
/* Table: CS_ORGAN_NODE_TYPE                                    */
/*==============================================================*/
create table CS_ORGAN_NODE_TYPE (
   ID                   NUMERIC(19)          not null,
   DOWN                 NUMERIC(10)          null,
   IMAGE                VARCHAR(50)          null,
   PEOPLE               NUMERIC(10)          null,
   IS_TOP               NUMERIC(10)          null,
   OPERATE_TIME         NUMERIC(19)          null,
   OPERATOR             VARCHAR(50)          null,
   REMOVED              NUMERIC(10)          null,
   CODE                 VARCHAR(100)         null,
   NAME                 VARCHAR(255)         not null,
   DESCRIPTION          VARCHAR(255)         null,
   constraint PK_CF_53 primary key (ID)
);

/*==============================================================*/
/* Table: CS_ORGAN_RELATION                                     */
/*==============================================================*/
create table CS_ORGAN_RELATION (
   ID                   NUMERIC(19)          not null,
   CHILD_NODE_ID        NUMERIC(19)          null,
   CREATE_TIME          DATE                 null,
   DESCRIPTION          VARCHAR(255)         null,
   NAME                 VARCHAR(255)         not null,
   ORGAN_REALTION_TYPE_ID NUMERIC(19)          null,
   PARENT_NODE_ID       NUMERIC(19)          null,
   OPERATE_TIME         NUMERIC(19)          null,
   OPERATOR             VARCHAR(50)          null,
   REMOVED              NUMERIC(10)          null,
   constraint PK_CF_54 primary key (ID)
);

/*==============================================================*/
/* Table: CS_ORGAN_RELATION_TYPE                                */
/*==============================================================*/
create table CS_ORGAN_RELATION_TYPE (
   ID                   NUMERIC(19)          not null,
   OPERATE_TIME         NUMERIC(19)          null,
   OPERATOR             VARCHAR(50)          null,
   REMOVED              NUMERIC(10)          null,
   CODE                 VARCHAR(100)         null,
   NAME                 VARCHAR(255)         not null,
   DESCRIPTION          VARCHAR(255)         null,
   constraint PK_CF_58 primary key (ID)
);

/*==============================================================*/
/* Table: CS_ORGAN_ROLE                                         */
/*==============================================================*/
create table CS_ORGAN_ROLE (
   ORGAN_MODEL_ID       NUMERIC(19)          not null,
   SECURITY_ROLE_ID     NUMERIC(19)          not null,
   constraint PK_CF_59 primary key (ORGAN_MODEL_ID, SECURITY_ROLE_ID)
);

/*==============================================================*/
/* Table: CS_ORGAN_RULE                                         */
/*==============================================================*/
create table CS_ORGAN_RULE (
   ID                   NUMERIC(19)          not null,
   ORGAN_TREE_TYPE_ID   NUMERIC(19)          null,
   SUPERIOR_NODE_ID     NUMERIC(19)          null,
   SUBORDINATE_NODE_ID  NUMERIC(19)          null,
   OPERATE_TIME         NUMERIC(19)          null,
   OPERATOR             VARCHAR(50)          null,
   REMOVED              NUMERIC(10)          null,
   CODE                 VARCHAR(100)         null,
   NAME                 VARCHAR(255)         not null,
   DESCRIPTION          VARCHAR(255)         null,
   constraint PK_CF_62 primary key (ID)
);

/*==============================================================*/
/* Table: CS_ORGAN_TREE                                         */
/*==============================================================*/
create table CS_ORGAN_TREE (
   ID                   NUMERIC(19)          not null,
   CREATE_TIME          DATE                 null,
   ROOT_NODE_ID         NUMERIC(19)          null,
   ORGAN_TREE_TYPE_ID   NUMERIC(19)          null,
   OPERATE_TIME         NUMERIC(19)          null,
   OPERATOR             VARCHAR(50)          null,
   REMOVED              NUMERIC(10)          null,
   CODE                 VARCHAR(100)         null,
   NAME                 VARCHAR(255)         not null,
   DESCRIPTION          VARCHAR(255)         null,
   constraint PK_CF_65 primary key (ID)
);

/*==============================================================*/
/* Table: CS_ORGAN_TREE_TYPE                                    */
/*==============================================================*/
create table CS_ORGAN_TREE_TYPE (
   ID                   NUMERIC(19)          not null,
   OPERATE_TIME         NUMERIC(19)          null,
   OPERATOR             VARCHAR(50)          null,
   REMOVED              NUMERIC(10)          null,
   CODE                 VARCHAR(100)         null,
   NAME                 VARCHAR(255)         not null,
   DESCRIPTION          VARCHAR(255)         null,
   constraint PK_CF_67 primary key (ID)
);

/*==============================================================*/
/* Table: CS_PROXY_USER                                         */
/*==============================================================*/
create table CS_PROXY_USER (
   ID                   NUMERIC(19)          not null,
   DESCRIPTION          VARCHAR(255)         null,
   END_TIME             DATE                 null,
   START_TIME           DATE                 null,
   PROXY_ID             NUMERIC(19)          null,
   USER_ID              NUMERIC(19)          null,
   constraint PK_CF_68 primary key (ID)
);

/*==============================================================*/
/* Table: CS_PUB_PERMISSION                                     */
/*==============================================================*/
create table CS_PUB_PERMISSION (
   ACL_PERMISSION_ID    NUMERIC(19)          not null,
   SECURITY_ROLE_ID     NUMERIC(19)          not null,
   constraint PK_CF_70 primary key (SECURITY_ROLE_ID, ACL_PERMISSION_ID)
);

/*==============================================================*/
/* Table: CS_RESOURCE_GROUP                                     */
/*==============================================================*/
create table CS_RESOURCE_GROUP (
   ID                   NUMERIC(19)          not null,
   ACL_RES_TYPE_ID      NUMERIC(19)          null,
   CLASS_NAME           VARCHAR(255)         not null,
   CODE                 VARCHAR(100)         null,
   DESCRIPTION          VARCHAR(255)         null,
   NAME                 VARCHAR(255)         null,
   PROP_VALUES          VARCHAR(255)         null,
   OPERATE_TIME         NUMERIC(19)          null,
   OPERATOR             VARCHAR(50)          null,
   REMOVED              NUMERIC(10)          null,
   constraint PK_CF_73 primary key (ID)
);

/*==============================================================*/
/* Table: CS_REV_PERMISSION                                     */
/*==============================================================*/
create table CS_REV_PERMISSION (
   ACL_PERMISSION_ID    NUMERIC(19)          not null,
   SECURITY_ROLE_ID     NUMERIC(19)          not null,
   constraint PK_CF_75 primary key (SECURITY_ROLE_ID, ACL_PERMISSION_ID)
);

/*==============================================================*/
/* Table: CS_ROLE                                               */
/*==============================================================*/
create table CS_ROLE (
   ID                   NUMERIC(19)          not null,
   NAME                 VARCHAR(100)         not null,
   TYPE                 VARCHAR(1)           not null,
   DESCRIPTION          VARCHAR(255)         null,
   PARENT_ROLE_ID       NUMERIC(19)          null,
   OPERATE_TIME         NUMERIC(19)          null,
   OPERATOR             VARCHAR(50)          null,
   REMOVED              NUMERIC(10)          null,
   constraint PK_CF_78 primary key (ID),
   constraint PK_CF_79 unique (NAME)
);

/*==============================================================*/
/* Table: CS_USER                                               */
/*==============================================================*/
create table CS_USER (
   ID                   NUMERIC(19)          not null,
   EXT1                 VARCHAR(255)         null,
   EXT2                 VARCHAR(255)         null,
   EXT3                 VARCHAR(255)         null,
   EXT4                 VARCHAR(255)         null,
   EXT5                 VARCHAR(255)         null,
   EXT6                 VARCHAR(255)         null,
   EXT7                 VARCHAR(255)         null,
   EXT8                 VARCHAR(255)         null,
   EMAIL                VARCHAR(100)         null,
   ADDRESS              VARCHAR(255)         null,
   MOBILE1              VARCHAR(50)          null,
   MOBILE2              VARCHAR(50)          null,
   FAX                  VARCHAR(50)          null,
   HOME_PHONE           VARCHAR(50)          null,
   OFFICE_PHONE         VARCHAR(50)          null,
   AUTHENTIC_TYPE       NUMERIC(10)          null,
   CERTIFICATE          VARCHAR(255)         null,
   STATUS               NUMERIC(19)          null,
   LOGIN_NAME           VARCHAR(100)         not null,
   NAME                 VARCHAR(100)         null,
   SEX                  VARCHAR(255)         null,
   PASSWORD             VARCHAR(100)         not null,
   USERTYPE             NUMERIC(10)          null,
   ACCOUNTTYPE          NUMERIC(10)          null,
   OPERATE_TIME         NUMERIC(19)          null,
   OPERATOR             VARCHAR(50)          null,
   REMOVED              NUMERIC(10)          null,
   CREATEDATE           NUMERIC(19)          null,
   RESET_CODE           VARCHAR(255)         null,
   constraint PK_CF_81 primary key (ID)
);

/*==============================================================*/
/* Table: CS_USER_APPNODE                                       */
/*==============================================================*/
create table CS_USER_APPNODE (
   APP_NODE_ID          NUMERIC(19)          not null,
   USER_ID              NUMERIC(19)          not null,
   constraint PK_CF_82 primary key (USER_ID, APP_NODE_ID)
);

/*==============================================================*/
/* Table: CS_USER_EXTEND_PROPERTY                               */
/*==============================================================*/
create table CS_USER_EXTEND_PROPERTY (
   ID                   NUMERIC(19)          not null,
   EXT1                 VARCHAR(255)         null,
   EXT2                 VARCHAR(255)         null,
   EXT3                 VARCHAR(255)         null,
   EXT4                 VARCHAR(255)         null,
   EXT5                 VARCHAR(255)         null,
   EXT6                 VARCHAR(255)         null,
   EXT7                 VARCHAR(255)         null,
   EXT8                 VARCHAR(255)         null,
   EXT9                 VARCHAR(255)         null,
   EXT10                VARCHAR(255)         null,
   EXT11                VARCHAR(255)         null,
   EXT12                VARCHAR(255)         null,
   EXT13                VARCHAR(255)         null,
   EXT14                VARCHAR(255)         null,
   EXT15                VARCHAR(255)         null,
   EXT16                VARCHAR(255)         null,
   EXT17                VARCHAR(255)         null,
   EXT18                VARCHAR(255)         null,
   EXT19                VARCHAR(255)         null,
   EXT20                VARCHAR(255)         null,
   EXT21                VARCHAR(255)         null,
   EXT22                VARCHAR(255)         null,
   EXT23                VARCHAR(255)         null,
   EXT24                VARCHAR(255)         null,
   EXT25                VARCHAR(255)         null,
   EXT26                VARCHAR(255)         null,
   EXT27                VARCHAR(255)         null,
   EXT28                VARCHAR(255)         null,
   EXT29                VARCHAR(255)         null,
   EXT30                VARCHAR(255)         null,
   EXT31                VARCHAR(255)         null,
   EXT32                VARCHAR(255)         null,
   EXT33                VARCHAR(255)         null,
   EXT34                VARCHAR(255)         null,
   EXT35                VARCHAR(255)         null,
   EXT36                VARCHAR(255)         null,
   EXT37                VARCHAR(255)         null,
   EXT38                VARCHAR(255)         null,
   EXT39                VARCHAR(255)         null,
   EXT40                VARCHAR(255)         null,
   SECURITY_USER_ID     NUMERIC(19)          null,
   constraint PK_CS_USER_EXTEND_PROPERTY primary key (ID)
);

/*==============================================================*/
/* Table: CS_USER_GROUP                                         */
/*==============================================================*/
create table CS_USER_GROUP (
   SECURITY_GROUP_ID    NUMERIC(19)          not null,
   SECURITY_USER_ID     NUMERIC(19)          not null,
   constraint PK_CF_85 primary key (SECURITY_USER_ID, SECURITY_GROUP_ID)
);

/*==============================================================*/
/* Table: CS_USER_LOGIN                                         */
/*==============================================================*/
create table CS_USER_LOGIN (
   ID                   NUMERIC(19)          not null,
   USER_ID              NUMERIC(19)          null,
   USER_NAME            VARCHAR(255)         null,
   LOGIN_NAME           VARCHAR(255)         null,
   APP_ID               NUMERIC(19)          null,
   APP_NAME             VARCHAR(255)         null,
   LOGIN_TIME           NUMERIC(19)          null,
   IP_ADDRESS           VARCHAR(255)         null,
   AREA                 VARCHAR(255)         null,
   OPERATING_SYSTEM     VARCHAR(255)         null,
   BROWSER              VARCHAR(255)         null,
   ONLINE_TIME          NUMERIC(19)          null,
   USER_STATE           NUMERIC(10)          null,
   ERROR_INFO           VARCHAR(255)         null,
   constraint PK_CS_USER_LOGIN primary key (ID)
);

/*==============================================================*/
/* Table: CS_USER_ORGANNODE                                     */
/*==============================================================*/
create table CS_USER_ORGANNODE (
   ORGAN_NODE_ID        NUMERIC(19)          not null,
   SECURITY_USER_ID     NUMERIC(19)          not null,
   ORDERS               NUMERIC(19)          null,
   constraint PK_CF_125 primary key (SECURITY_USER_ID, ORGAN_NODE_ID)
);

/*==============================================================*/
/* Table: CS_USER_ROLE                                          */
/*==============================================================*/
create table CS_USER_ROLE (
   SECURITY_ROLE_ID     NUMERIC(19)          not null,
   SECURITY_USER_ID     NUMERIC(19)          not null,
   constraint PK_CF_90 primary key (SECURITY_USER_ID, SECURITY_ROLE_ID)
);

/*==============================================================*/
/* Table: CS_WEBAPP_NODE                                        */
/*==============================================================*/
create table CS_WEBAPP_NODE (
   ID                   NUMERIC(19)          not null,
   COMPATABLE           NUMERIC(1)           null,
   CENTRAL_NODE_ID      NUMERIC(19)          null,
   APP_SERVER           VARCHAR(255)         null,
   CODE                 VARCHAR(100)         null,
   DB_SERVER            VARCHAR(255)         null,
   DESCRIPTION          VARCHAR(255)         null,
   INDEX_URL            VARCHAR(255)         null,
   IS_CUTE_SERIES       NUMERIC(1)           null,
   NAME                 VARCHAR(255)         null,
   NODE_TYPE            NUMERIC(19)          not null,
   VENDOR               VARCHAR(255)         null,
   VERSION              VARCHAR(255)         null,
   OPERATE_TIME         NUMERIC(19)          null,
   OPERATOR             VARCHAR(50)          null,
   REMOVED              NUMERIC(10)          null,
   MAX_INACTIVE_SECS    NUMERIC(19)          null,
   LOGOUT_STYLE         NUMERIC(1)           null,
   ROOT_MENU_CODE       VARCHAR(255)         null,
   ORGAN_ID             NUMERIC(19)          null,
   constraint PK_CF_94 primary key (ID)
);

/*==============================================================*/
/* Table: CW_ACT_INSTANCE                                       */
/*==============================================================*/
create table CW_ACT_INSTANCE (
   ACT_INSTANCE_ID      VARCHAR(255)         not null,
   ACTIVITY_ID          VARCHAR(255)         null,
   ACTIVITY_NAME        VARCHAR(255)         null,
   ACTIVITY_STATE       NUMERIC(10)          null,
   ACTIVITY_TYPE        NUMERIC(10)          null,
   END_TIME             DATE                 null,
   INPUT_CONTAINERS     VARCHAR(255)         null,
   OUTPUT_CONTAINERS    VARCHAR(255)         null,
   PROCESS_INSTANCE_ID  VARCHAR(255)         not null,
   START_TIME           DATE                 null,
   PROPERTIES           CHAR(2000)           null,
   constraint PK_CF_96 primary key (ACT_INSTANCE_ID)
);

/*==============================================================*/
/* Table: CW_AUDIT_ACT_INST                                     */
/*==============================================================*/
create table CW_AUDIT_ACT_INST (
   ID                   NUMERIC(19)          not null,
   ACTIVITY_NAME        VARCHAR(255)         not null,
   PROCESS_INSTANCE_ID  VARCHAR(255)         not null,
   ACTIVITY_INSTANCE_ID VARCHAR(255)         not null,
   CHANGEDTIME          DATE                 not null,
   NEWSTATE             NUMERIC(10)          null,
   PERVIOUSSTATE        NUMERIC(10)          null,
   ACTION               NUMERIC(10)          not null,
   constraint PK_CF_98 primary key (ID)
);

/*==============================================================*/
/* Table: CW_AUDIT_CONFIG                                       */
/*==============================================================*/
create table CW_AUDIT_CONFIG (
   ID                   NUMERIC(19)          not null,
   ISAUDIT_ACTIVITY_INSTANCE NUMERIC(1)           null,
   ISAUDIT_GLOBAL       NUMERIC(1)           null,
   ISAUDIT_PROCESS_INSTANCE NUMERIC(1)           null,
   ISAUDIT_PROCESS_MODEL NUMERIC(1)           null,
   ISAUDIT_WORKITEM     NUMERIC(1)           null,
   PROCESS_ID           VARCHAR(255)         null,
   ISAUDIT_PROCESS_DATA NUMERIC(1)           null,
   constraint PK_CF_99 primary key (ID),
   constraint PK_CF_100 unique (PROCESS_ID)
);

/*==============================================================*/
/* Table: CW_AUDIT_PDATA                                        */
/*==============================================================*/
create table CW_AUDIT_PDATA (
   ID                   NUMERIC(19)          not null,
   CONTAINER_INSTANCE_ID VARCHAR(255)         null,
   ACTION               NUMERIC(10)          null,
   NEW_DATA             VARCHAR(255)         null,
   OLD_DATA             VARCHAR(255)         null,
   CHANGED_TIME         DATE                 null,
   ATTRIBUTE_NAME       VARCHAR(255)         null,
   PROCESS_DATA_ID      NUMERIC(19)          null,
   PROCESS_INSTANCE_ID  VARCHAR(255)         null,
   constraint PK_CF_126 primary key (ID)
);

/*==============================================================*/
/* Table: CW_AUDIT_PINSTANCE                                    */
/*==============================================================*/
create table CW_AUDIT_PINSTANCE (
   ID                   NUMERIC(19)          not null,
   PROCESS_NAME         VARCHAR(255)         null,
   PROCESS_ID           VARCHAR(255)         not null,
   PROCESS_INSTANCE_ID  VARCHAR(255)         not null,
   CHANGEDTIME          DATE                 not null,
   NEWSTATE             NUMERIC(10)          null,
   PERVIOUSSTATE        NUMERIC(10)          null,
   ACTION               NUMERIC(10)          not null,
   constraint PK_CF_127 primary key (ID)
);

/*==============================================================*/
/* Table: CW_AUDIT_PMODEL                                       */
/*==============================================================*/
create table CW_AUDIT_PMODEL (
   ID                   NUMERIC(19)          not null,
   PROCESS_NAME         VARCHAR(255)         null,
   MODEL_ID             VARCHAR(255)         null,
   PROCESS_ID           VARCHAR(255)         null,
   CHANGEDTIME          DATE                 not null,
   NEWSTATE             NUMERIC(10)          null,
   PERVIOUSSTATE        NUMERIC(10)          null,
   ACTION               NUMERIC(10)          not null,
   constraint PK_CF_102 primary key (ID)
);

/*==============================================================*/
/* Table: CW_AUDIT_WORKITEM                                     */
/*==============================================================*/
create table CW_AUDIT_WORKITEM (
   ID                   NUMERIC(19)          not null,
   ACTIVITY_NAME        VARCHAR(255)         null,
   WORKITEM_ID          NUMERIC(19)          null,
   ACTIVITY_INSTANCE_ID VARCHAR(255)         null,
   CHANGEDTIME          DATE                 not null,
   NEWSTATE             NUMERIC(10)          null,
   PERVIOUSSTATE        NUMERIC(10)          null,
   ACTION               NUMERIC(10)          not null,
   constraint PK_CF_103 primary key (ID)
);

/*==============================================================*/
/* Table: CW_CON_INSTANCE                                       */
/*==============================================================*/
create table CW_CON_INSTANCE (
   CON_INSTANCE_ID      VARCHAR(255)         not null,
   PROCESS_INSTANCE_ID  VARCHAR(255)         null,
   CONTAINER_ID         VARCHAR(255)         null,
   CONTAINER_NAME       VARCHAR(255)         null,
   constraint PK_CF_104 primary key (CON_INSTANCE_ID)
);

/*==============================================================*/
/* Table: CW_PROCESS_DATA                                       */
/*==============================================================*/
create table CW_PROCESS_DATA (
   ID                   NUMERIC(19)          not null,
   ATTRIBUTE_NAME       VARCHAR(255)         null,
   ATTRIBUTE_TYPE       VARCHAR(255)         null,
   ATTRIBUTE_VALUE      VARCHAR(255)         null,
   CON_INSTANCE_ID      VARCHAR(255)         null,
   MODIFIED_TIME        DATE                 null,
   RECORD_INDEX         NUMERIC(10)          null,
   constraint PK_CF_105 primary key (ID)
);

/*==============================================================*/
/* Table: CW_PROCESS_INSTANCE                                   */
/*==============================================================*/
create table CW_PROCESS_INSTANCE (
   PROCESS_INSTANCE_ID  VARCHAR(255)         not null,
   ENABLED_ACTIVITIES   VARCHAR(255)         null,
   END_TIME             DATE                 null,
   EXTERNALE_KEY        VARCHAR(255)         null,
   PROCESS_ID           VARCHAR(255)         not null,
   PROCESS_STATE        NUMERIC(10)          null,
   START_TIME           DATE                 null,
   PROCESS_NAME         VARCHAR(255)         null,
   PROPERTIES           CHAR(2000)           null,
   BUSINESS_ENTITYID    VARCHAR(255)         null,
   STATE_DESCRIPTION    VARCHAR(255)         null,
   PARENT_INSTANCE_ID   VARCHAR(255)         null,
   constraint PK_CF_107 primary key (PROCESS_INSTANCE_ID)
);

/*==============================================================*/
/* Table: CW_PROCESS_MODEL                                      */
/*==============================================================*/
create table CW_PROCESS_MODEL (
   PROCESS_ID           VARCHAR(255)         not null,
   BUSINESS_TYPE        NUMERIC(10)          null,
   DESCRIPTION          VARCHAR(255)         null,
   MODEL                TEXT                 null,
   MODEL_ID             VARCHAR(255)         null,
   NAME                 VARCHAR(255)         null,
   PUBLISH_STATE        NUMERIC(10)          null,
   VERSION              NUMERIC(10)          null,
   constraint PK_CF_109 primary key (PROCESS_ID)
);

/*==============================================================*/
/* Table: CW_TIME_ACTIVITY                                      */
/*==============================================================*/
create table CW_TIME_ACTIVITY (
   ID                   NUMERIC(19)          not null,
   EXPECTED_TIME        DATE                 null,
   ACTIVITY_INSTANCE_ID VARCHAR(255)         null,
   STARTED_TIME         DATE                 null,
   constraint PK_CF_110 primary key (ID)
);

/*==============================================================*/
/* Table: CW_TRANS_INSTANCE                                     */
/*==============================================================*/
create table CW_TRANS_INSTANCE (
   TRANS_INSTANCE_ID    VARCHAR(255)         not null,
   FROM_AINSTANCE_ID    VARCHAR(255)         null,
   MODIFIED_TIME        DATE                 null,
   TO_AINSTANCE_ID      VARCHAR(255)         null,
   PROCESS_INSTANCE_ID  VARCHAR(255)         null,
   TRANSITION_ID        VARCHAR(255)         null,
   TRANSITION_NAME      VARCHAR(255)         null,
   TRANSITION_STATE     NUMERIC(10)          null,
   TRANSITION_TYPE      NUMERIC(10)          null,
   PROPERTIES           CHAR(2000)           null,
   constraint PK_CF_112 primary key (TRANS_INSTANCE_ID)
);

/*==============================================================*/
/* Table: CW_WORK_ITEMS                                         */
/*==============================================================*/
create table CW_WORK_ITEMS (
   WORKITEM_ID          NUMERIC(19)          not null,
   ACTIVITY_ID          VARCHAR(255)         null,
   ACT_INSTANCE_ID      VARCHAR(255)         null,
   ACTIVITY_NAME        VARCHAR(255)         null,
   DEADLINE             DATE                 null,
   DESCRIPTION          VARCHAR(255)         null,
   OUTER_ID             VARCHAR(255)         null,
   WORKITEM_TYPE        NUMERIC(10)          null,
   WORK_STATE           NUMERIC(10)          null,
   MODEL_ID             VARCHAR(255)         null,
   PROCESS_INSTANCE_ID  VARCHAR(255)         null,
   constraint PK_CF_117 primary key (WORKITEM_ID)
);

/*==============================================================*/
/* Table: CW_WORK_PERSON                                        */
/*==============================================================*/
create table CW_WORK_PERSON (
   ID                   NUMERIC(19)          not null,
   DESCRIPTION          VARCHAR(255)         null,
   IS_SHOW              NUMERIC(1)           null,
   EXECUTE_TIME         DATE                 null,
   WORK_INDEX           NUMERIC(10)          null,
   PERSON_ID            VARCHAR(255)         null,
   WORKITEM_ID          NUMERIC(19)          null,
   constraint PK_CF_119 primary key (ID)
);

alter table CD_ATTRIBUTE_PRIVILEGE
   add constraint PK_CF_334 foreign key (GROUP_ID)
      references CS_GROUP (ID)
      on delete restrict on update restrict;

alter table CF_CODE_INFO
   add constraint PK_CF_500 foreign key (CODE_INFO_ID)
      references CF_CODE_INFO (ID)
      on delete restrict on update restrict;

alter table CF_CODE_INFO
   add constraint PK_CF_300 foreign key (TYPE_ID)
      references CF_CODE_TYPE (ID)
      on delete restrict on update restrict;

alter table CF_DEMO_COAUDIT
   add constraint PK_CF_333 foreign key (CO_AUDIT_ID)
      references CF_DEMO_DRAFT (ID)
      on delete restrict on update restrict;

alter table CF_DEMO_DRAFT
   add constraint PK_CF_335 foreign key (TYPE)
      references CF_CODE_INFO (ID)
      on delete restrict on update restrict;

alter table CF_DEMO_DRAFT
   add constraint PK_CF_336 foreign key (CONFIDENTIAL_LEVEL)
      references CF_CODE_INFO (ID)
      on delete restrict on update restrict;

alter table CF_DEMO_DRAFT
   add constraint PK_CF_337 foreign key (PRIORITY)
      references CF_CODE_INFO (ID)
      on delete restrict on update restrict;

alter table CF_SESSION_RECORD
   add constraint PK_CF_20 foreign key (SESSIONSET_ID)
      references CF_SESSION_SET (ID)
      on delete restrict on update restrict;

alter table CP_CONSOLE_OPERS
   add constraint PK_CF_21 foreign key (PARENT_RESOURCE_ID)
      references CP_CONSOLE_OPERS (ID)
      on delete restrict on update restrict;

alter table CP_INDIVIDUAL_VIEW
   add constraint PK_CF_22 foreign key (MENU_ID)
      references CP_MENU_RESOURCE (ID)
      on delete restrict on update restrict;

alter table CP_INDIVIDUAL_VIEW
   add constraint PK_CF_23 foreign key (PARENT_VIEW_ID)
      references CP_INDIVIDUAL_VIEW (ID)
      on delete restrict on update restrict;

alter table CP_INDIVIDUAL_VIEW
   add constraint PK_CF_24 foreign key (USER_ID)
      references CS_USER (ID)
      on delete restrict on update restrict;

alter table CP_MENU_RESOURCE
   add constraint PK_CF_122 foreign key (PARENT_RESOURCE_ID)
      references CP_MENU_RESOURCE (ID)
      on delete restrict on update restrict;

alter table CP_MENU_RESOURCE
   add constraint PK_CF_11 foreign key (HOST)
      references CF_CODE_INFO (ID)
      on delete restrict on update restrict;

alter table CP_MENU_RESOURCE
   add constraint PK_CF_12 foreign key (MENU_TYPE)
      references CF_CODE_INFO (ID)
      on delete restrict on update restrict;

alter table CP_MENU_RESOURCE
   add constraint PK_CF_9 foreign key (BIG_IMAGE)
      references CF_CODE_INFO (ID)
      on delete restrict on update restrict;

alter table CP_MENU_RESOURCE
   add constraint PK_CF_10 foreign key (SMALL_IMAGE)
      references CF_CODE_INFO (ID)
      on delete restrict on update restrict;

alter table CP_URL_RESOURCE
   add constraint PK_CF_14 foreign key (PARENT_RESOURCE_ID)
      references CP_URL_RESOURCE (ID)
      on delete restrict on update restrict;

alter table CR_PROXY_MENU
   add constraint PK_CF_16 foreign key (MENU_ID)
      references CP_MENU_RESOURCE (ID)
      on delete restrict on update restrict;

alter table CR_PROXY_MENU
   add constraint PK_CF_17 foreign key (PROXY_ID)
      references CS_PROXY_USER (ID)
      on delete restrict on update restrict;

alter table CS_ACC_PERMISSION
   add constraint PK_CF_121 foreign key (ACL_PERMISSION_ID)
      references CS_ACL_PERMISSION (ID)
      on delete restrict on update restrict;

alter table CS_ACC_PERMISSION
   add constraint PK_CF_25 foreign key (SECURITY_ROLE_ID)
      references CS_ROLE (ID)
      on delete restrict on update restrict;

alter table CS_ACL_OPERATION
   add constraint PK_CF_27 foreign key (ACL_RES_TYPE_ID)
      references CS_ACL_RES_TYPE (ID)
      on delete restrict on update restrict;

alter table CS_ACL_PERMISSION
   add constraint PK_CF_29 foreign key (ACL_OPERATION_ID)
      references CS_ACL_OPERATION (ID)
      on delete restrict on update restrict;

alter table CS_ACL_RESOURCE
   add constraint PK_CF_34 foreign key (PARENT_ACL_RES_ID)
      references CS_ACL_RESOURCE (ID)
      on delete restrict on update restrict;

alter table CS_ACL_RES_TYPE
   add constraint PK_CF_32 foreign key (WEBAPP_NODE_ID)
      references CS_WEBAPP_NODE (ID)
      on delete restrict on update restrict;

alter table CS_ACTION_RESOURCE
   add constraint PK_CF_37 foreign key (PARENT_RESOURCE_ID)
      references CS_ACTION_RESOURCE (ID)
      on delete restrict on update restrict;

alter table CS_CUSTOM_RESOURCE
   add constraint PK_CF_39 foreign key (PARENT_RESOURCE_ID)
      references CS_CUSTOM_RESOURCE (ID)
      on delete restrict on update restrict;

alter table CS_CUSTOM_RESOURCE
   add constraint PK_CF_40 foreign key (ACL_RES_TYPE_ID)
      references CS_ACL_RES_TYPE (ID)
      on delete restrict on update restrict;

alter table CS_GROUP_ROLE
   add constraint PK_CF_43 foreign key (SECURITY_GROUP_ID)
      references CS_GROUP (ID)
      on delete restrict on update restrict;

alter table CS_GROUP_ROLE
   add constraint PK_CF_44 foreign key (SECURITY_ROLE_ID)
      references CS_ROLE (ID)
      on delete restrict on update restrict;

alter table CS_ORGAN_EXTEND_PROPERTY
   add constraint PK_CF_339 foreign key (ORGAN_NODE_ID)
      references CS_ORGAN_NODE (ID)
      on delete restrict on update restrict;

alter table CS_ORGAN_MODEL
   add constraint PK_CF_46 foreign key (ORG_NODE_ID)
      references CS_ORGAN_NODE (ID)
      on delete restrict on update restrict;

alter table CS_ORGAN_MODEL
   add constraint PK_CF_47 foreign key (PARENT_NODE_ID)
      references CS_ORGAN_NODE (ID)
      on delete restrict on update restrict;

alter table CS_ORGAN_MODEL
   add constraint PK_CF_48 foreign key (ORG_TREE_ID)
      references CS_ORGAN_TREE (ID)
      on delete restrict on update restrict;

alter table CS_ORGAN_NODE
   add constraint PK_CF_50 foreign key (ADMIN_USER_ID)
      references CS_USER (ID)
      on delete restrict on update restrict;

alter table CS_ORGAN_NODE
   add constraint PK_CF_51 foreign key (ICON)
      references CF_CODE_INFO (ID)
      on delete restrict on update restrict;

alter table CS_ORGAN_NODE
   add constraint PK_CF_52 foreign key (ORGAN_NODE_TYPE_ID)
      references CS_ORGAN_NODE_TYPE (ID)
      on delete restrict on update restrict;

alter table CS_ORGAN_RELATION
   add constraint PK_CF_55 foreign key (CHILD_NODE_ID)
      references CS_ORGAN_NODE (ID)
      on delete restrict on update restrict;

alter table CS_ORGAN_RELATION
   add constraint PK_CF_56 foreign key (PARENT_NODE_ID)
      references CS_ORGAN_NODE (ID)
      on delete restrict on update restrict;

alter table CS_ORGAN_RELATION
   add constraint PK_CF_57 foreign key (ORGAN_REALTION_TYPE_ID)
      references CS_ORGAN_RELATION_TYPE (ID)
      on delete restrict on update restrict;

alter table CS_ORGAN_ROLE
   add constraint PK_CF_60 foreign key (SECURITY_ROLE_ID)
      references CS_ROLE (ID)
      on delete restrict on update restrict;

alter table CS_ORGAN_ROLE
   add constraint PK_CF_61 foreign key (ORGAN_MODEL_ID)
      references CS_ORGAN_MODEL (ID)
      on delete restrict on update restrict;

alter table CS_ORGAN_RULE
   add constraint PK_CF_63 foreign key (SUPERIOR_NODE_ID)
      references CS_ORGAN_NODE_TYPE (ID)
      on delete restrict on update restrict;

alter table CS_ORGAN_RULE
   add constraint PK_CF_64 foreign key (SUBORDINATE_NODE_ID)
      references CS_ORGAN_NODE_TYPE (ID)
      on delete restrict on update restrict;

alter table CS_ORGAN_TREE
   add constraint PK_CF_66 foreign key (ORGAN_TREE_TYPE_ID)
      references CS_ORGAN_TREE_TYPE (ID)
      on delete restrict on update restrict;

alter table CS_PROXY_USER
   add constraint PK_CF_69 foreign key (USER_ID)
      references CS_USER (ID)
      on delete restrict on update restrict;

alter table CS_PUB_PERMISSION
   add constraint PK_CF_71 foreign key (ACL_PERMISSION_ID)
      references CS_ACL_PERMISSION (ID)
      on delete restrict on update restrict;

alter table CS_PUB_PERMISSION
   add constraint PK_CF_72 foreign key (SECURITY_ROLE_ID)
      references CS_ROLE (ID)
      on delete restrict on update restrict;

alter table CS_RESOURCE_GROUP
   add constraint PK_CF_74 foreign key (ACL_RES_TYPE_ID)
      references CS_ACL_RES_TYPE (ID)
      on delete restrict on update restrict;

alter table CS_REV_PERMISSION
   add constraint PK_CF_76 foreign key (ACL_PERMISSION_ID)
      references CS_ACL_PERMISSION (ID)
      on delete restrict on update restrict;

alter table CS_REV_PERMISSION
   add constraint PK_CF_77 foreign key (SECURITY_ROLE_ID)
      references CS_ROLE (ID)
      on delete restrict on update restrict;

alter table CS_ROLE
   add constraint PK_CF_80 foreign key (PARENT_ROLE_ID)
      references CS_ROLE (ID)
      on delete restrict on update restrict;

alter table CS_USER_APPNODE
   add constraint PK_CF_83 foreign key (APP_NODE_ID)
      references CS_WEBAPP_NODE (ID)
      on delete restrict on update restrict;

alter table CS_USER_APPNODE
   add constraint PK_CF_84 foreign key (USER_ID)
      references CS_USER (ID)
      on delete restrict on update restrict;

alter table CS_USER_EXTEND_PROPERTY
   add constraint PK_CF_338 foreign key (SECURITY_USER_ID)
      references CS_USER (ID)
      on delete restrict on update restrict;

alter table CS_USER_GROUP
   add constraint PK_CF_86 foreign key (SECURITY_GROUP_ID)
      references CS_GROUP (ID)
      on delete restrict on update restrict;

alter table CS_USER_GROUP
   add constraint PK_CF_123 foreign key (SECURITY_USER_ID)
      references CS_USER (ID)
      on delete restrict on update restrict;

alter table CS_USER_LOGIN
   add constraint PK_CF_340 foreign key (USER_ID)
      references CS_USER (ID)
      on delete restrict on update restrict;

alter table CS_USER_ORGANNODE
   add constraint PK_CF_88 foreign key (SECURITY_USER_ID)
      references CS_USER (ID)
      on delete restrict on update restrict;

alter table CS_USER_ORGANNODE
   add constraint PK_CF_89 foreign key (ORGAN_NODE_ID)
      references CS_ORGAN_NODE (ID)
      on delete restrict on update restrict;

alter table CS_USER_ROLE
   add constraint PK_CF_91 foreign key (SECURITY_USER_ID)
      references CS_USER (ID)
      on delete restrict on update restrict;

alter table CS_USER_ROLE
   add constraint PK_CF_92 foreign key (SECURITY_ROLE_ID)
      references CS_ROLE (ID)
      on delete restrict on update restrict;

alter table CW_ACT_INSTANCE
   add constraint PK_CF_97 foreign key (PROCESS_INSTANCE_ID)
      references CW_PROCESS_INSTANCE (PROCESS_INSTANCE_ID)
      on delete restrict on update restrict;

alter table CW_PROCESS_DATA
   add constraint PK_CF_106 foreign key (CON_INSTANCE_ID)
      references CW_CON_INSTANCE (CON_INSTANCE_ID)
      on delete restrict on update restrict;

alter table CW_PROCESS_INSTANCE
   add constraint PK_CF_108 foreign key (PROCESS_ID)
      references CW_PROCESS_MODEL (PROCESS_ID)
      on delete restrict on update restrict;

alter table CW_TIME_ACTIVITY
   add constraint PK_CF_111 foreign key (ACTIVITY_INSTANCE_ID)
      references CW_ACT_INSTANCE (ACT_INSTANCE_ID)
      on delete restrict on update restrict;

alter table CW_TRANS_INSTANCE
   add constraint PK_CF_113 foreign key (TO_AINSTANCE_ID)
      references CW_ACT_INSTANCE (ACT_INSTANCE_ID)
      on delete restrict on update restrict;

alter table CW_TRANS_INSTANCE
   add constraint PK_CF_115 foreign key (FROM_AINSTANCE_ID)
      references CW_ACT_INSTANCE (ACT_INSTANCE_ID)
      on delete restrict on update restrict;

alter table CW_TRANS_INSTANCE
   add constraint PK_CF_116 foreign key (PROCESS_INSTANCE_ID)
      references CW_PROCESS_INSTANCE (PROCESS_INSTANCE_ID)
      on delete restrict on update restrict;

alter table CW_WORK_ITEMS
   add constraint PK_CF_118 foreign key (ACT_INSTANCE_ID)
      references CW_ACT_INSTANCE (ACT_INSTANCE_ID)
      on delete restrict on update restrict;

alter table CW_WORK_PERSON
   add constraint PK_CF_120 foreign key (WORKITEM_ID)
      references CW_WORK_ITEMS (WORKITEM_ID)
      on delete restrict on update restrict;

