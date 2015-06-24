/*==============================================================*/
/* DBMS name:      ORACLE Version 10g                           */
/* Created on:     2009-9-18 15:55:08                           */
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

drop table CD_ATTRIBUTE_PRIVILEGE cascade constraints;

drop table CF_CODE_INFO cascade constraints;

drop table CF_CODE_TYPE cascade constraints;

drop table CF_DEMO_COAUDIT cascade constraints;

drop table CF_DEMO_DRAFT cascade constraints;

drop table CF_EXTEND_PROPERTY_CONFIG cascade constraints;

drop table CF_LOG cascade constraints;

drop table CF_SESSION_RECORD cascade constraints;

drop table CF_SESSION_SET cascade constraints;

drop table CM_LEAVE_APPLY cascade constraints;

drop table CM_URL_RESOURCE cascade constraints;

drop table CP_CONSOLE_OPERS cascade constraints;

drop table CP_INDIVIDUAL_VIEW cascade constraints;

drop table CP_MENU_RESOURCE cascade constraints;

drop table CP_URL_RESOURCE cascade constraints;

drop table CR_PROXY_MENU cascade constraints;

drop table CS_ACC_PERMISSION cascade constraints;

drop table CS_ACL_OPERATION cascade constraints;

drop table CS_ACL_PERMISSION cascade constraints;

drop table CS_ACL_RESOURCE cascade constraints;

drop table CS_ACL_RES_TYPE cascade constraints;

drop table CS_ACTION_RESOURCE cascade constraints;

drop table CS_CUSTOM_RESOURCE cascade constraints;

drop table CS_GROUP cascade constraints;

drop table CS_GROUP_ROLE cascade constraints;

drop table CS_ORGAN_EXTEND_PROPERTY cascade constraints;

drop table CS_ORGAN_MODEL cascade constraints;

drop table CS_ORGAN_NODE cascade constraints;

drop table CS_ORGAN_NODE_TYPE cascade constraints;

drop table CS_ORGAN_RELATION cascade constraints;

drop table CS_ORGAN_RELATION_TYPE cascade constraints;

drop table CS_ORGAN_ROLE cascade constraints;

drop table CS_ORGAN_RULE cascade constraints;

drop table CS_ORGAN_TREE cascade constraints;

drop table CS_ORGAN_TREE_TYPE cascade constraints;

drop table CS_PROXY_USER cascade constraints;

drop table CS_PUB_PERMISSION cascade constraints;

drop table CS_RESOURCE_GROUP cascade constraints;

drop table CS_REV_PERMISSION cascade constraints;

drop table CS_ROLE cascade constraints;

drop table CS_USER cascade constraints;

drop table CS_USER_APPNODE cascade constraints;

drop table CS_USER_EXTEND_PROPERTY cascade constraints;

drop table CS_USER_GROUP cascade constraints;

drop table CS_USER_LOGIN cascade constraints;

drop table CS_USER_ORGANNODE cascade constraints;

drop table CS_USER_ROLE cascade constraints;

drop table CS_WEBAPP_NODE cascade constraints;

drop table CW_ACT_INSTANCE cascade constraints;

drop table CW_AUDIT_ACT_INST cascade constraints;

drop table CW_AUDIT_CONFIG cascade constraints;

drop table CW_AUDIT_PDATA cascade constraints;

drop table CW_AUDIT_PINSTANCE cascade constraints;

drop table CW_AUDIT_PMODEL cascade constraints;

drop table CW_AUDIT_WORKITEM cascade constraints;

drop table CW_CON_INSTANCE cascade constraints;

drop table CW_PROCESS_DATA cascade constraints;

drop table CW_PROCESS_INSTANCE cascade constraints;

drop table CW_PROCESS_MODEL cascade constraints;

drop table CW_TIME_ACTIVITY cascade constraints;

drop table CW_TRANS_INSTANCE cascade constraints;

drop table CW_WORK_ITEMS cascade constraints;

drop table CW_WORK_PERSON cascade constraints;

/*==============================================================*/
/* Table: CD_ATTRIBUTE_PRIVILEGE                                */
/*==============================================================*/
create table CD_ATTRIBUTE_PRIVILEGE  (
   ID                   NUMBER(19)                      not null,
   ATTRIBUTE_NAME       VARCHAR2(255 CHAR),
   GROUP_ID             NUMBER(19),
   PRIVILEGE            NUMBER(19),
   constraint PK_CF_610 primary key (ID)
);

/*==============================================================*/
/* Table: CF_CODE_INFO                                          */
/*==============================================================*/
create table CF_CODE_INFO  (
   ID                   NUMBER(19)                      not null,
   CODE_INFO_ID         NUMBER(19),
   TYPE_ID              NUMBER(19),
   DISP_ORDER           NUMBER(10),
   REMARK1              VARCHAR2(255),
   REMARK2              VARCHAR2(255),
   OPERATE_TIME         NUMBER(19),
   OPERATOR             VARCHAR2(50),
   REMOVED              NUMBER(10),
   CODE                 VARCHAR2(100),
   NAME                 VARCHAR2(255)                   not null,
   DESCRIPTION          VARCHAR2(255),
   constraint PK_CF_602 primary key (ID)
);

/*==============================================================*/
/* Table: CF_CODE_TYPE                                          */
/*==============================================================*/
create table CF_CODE_TYPE  (
   ID                   NUMBER(19)                      not null,
   SYSTEM_TYPE          NUMBER(10),
   DISP_ORDER           NUMBER(10),
   LEVE                 NUMBER(10),
   OPERATE_TIME         NUMBER(19),
   OPERATOR             VARCHAR2(50),
   REMOVED              NUMBER(10),
   CODE                 VARCHAR2(100),
   NAME                 VARCHAR2(255)                   not null,
   DESCRIPTION          VARCHAR2(255),
   EXTERNAL_MAPPING     VARCHAR2(4000 CHAR),
   STORAGE_TYPE         NUMBER(10),
   constraint PK_CF_1 primary key (ID)
);

/*==============================================================*/
/* Table: CF_DEMO_COAUDIT                                       */
/*==============================================================*/
create table CF_DEMO_COAUDIT  (
   ID                   NUMERIC(19)                     not null,
   CONTENT              VARCHAR2(255 CHAR),
   CREATE_DATE          DATE,
   CREATOR              VARCHAR2(255 CHAR),
   CO_AUDIT_ID          NUMBER(19),
   constraint PK_CF_607 primary key (ID)
);

/*==============================================================*/
/* Table: CF_DEMO_DRAFT                                         */
/*==============================================================*/
create table CF_DEMO_DRAFT  (
   ID                   NUMBER(19)                      not null,
   PROCESS_INSTANCE     VARCHAR2(255 CHAR),
   CONTENT              VARCHAR2(255 CHAR),
   CREATOR              VARCHAR2(255 CHAR),
   TITLE                VARCHAR2(255 CHAR),
   TYPE                 NUMBER(19),
   CREATE_DATE          DATE,
   AFFILIATED_COMMENT   VARCHAR2(255 CHAR),
   AFFILIATED_DATE      DATE,
   ARCHIVE_COMMENT      VARCHAR2(255 CHAR),
   ARCHIVE_DATE         DATE,
   AUDITOR              VARCHAR2(255 CHAR),
   AUDITOR_DATE         DATE,
   CONFIDENTIAL_LEVEL   NUMBER(19),
   FILE_CODE            VARCHAR2(255 CHAR),
   HEADER_COMMENT       VARCHAR2(255 CHAR),
   HEADER_DATE          DATE,
   OFFICIAL_COMMENT     VARCHAR2(255 CHAR),
   OFFICIAL_DATE        DATE,
   PRINT_COMMENT        VARCHAR2(255 CHAR),
   PRINT_DATE           DATE,
   PRIORITY             NUMBER(19),
   VERIFIED_COMMENT     VARCHAR2(255 CHAR),
   VERIFIED_DATE        DATE,
   constraint PK_CF_608 primary key (ID)
);

/*==============================================================*/
/* Table: CF_EXTEND_PROPERTY_CONFIG                             */
/*==============================================================*/
create table CF_EXTEND_PROPERTY_CONFIG  (
   ID                   NUMBER(19),
   CLASS_NAME           VARCHAR2(255),
   EXT1                 VARCHAR2(255),
   EXT2                 VARCHAR2(255),
   EXT3                 VARCHAR2(255),
   EXT4                 VARCHAR2(255),
   EXT5                 VARCHAR2(255),
   EXT6                 VARCHAR2(255),
   EXT7                 VARCHAR2(255),
   EXT8                 VARCHAR2(255),
   EXT9                 VARCHAR2(255),
   EXT10                VARCHAR2(255),
   EXT11                VARCHAR2(255),
   EXT12                VARCHAR2(255),
   EXT13                VARCHAR2(255),
   EXT14                VARCHAR2(255),
   EXT15                VARCHAR2(255),
   EXT16                VARCHAR2(255),
   EXT17                VARCHAR2(255),
   EXT18                VARCHAR2(255),
   EXT19                VARCHAR2(255),
   EXT20                VARCHAR2(255),
   EXT21                VARCHAR2(255),
   EXT22                VARCHAR2(255),
   EXT23                VARCHAR2(255),
   EXT24                VARCHAR2(255),
   EXT25                VARCHAR2(255),
   EXT26                VARCHAR2(255),
   EXT27                VARCHAR2(255),
   EXT28                VARCHAR2(255),
   EXT29                VARCHAR2(255),
   EXT30                VARCHAR2(255),
   EXT31                VARCHAR2(255),
   EXT32                VARCHAR2(255),
   EXT33                VARCHAR2(255),
   EXT34                VARCHAR2(255),
   EXT35                VARCHAR2(255),
   EXT36                VARCHAR2(255),
   EXT37                VARCHAR2(255),
   EXT38                VARCHAR2(255),
   EXT39                VARCHAR2(255),
   EXT40                VARCHAR2(255)
);

/*==============================================================*/
/* Table: CF_LOG                                                */
/*==============================================================*/
create table CF_LOG  (
   ID                   NUMBER(19)                      not null,
   LOGINDATE            DATE,
   OPERDATE             DATE,
   USERID               VARCHAR2(20),
   USERNAME             VARCHAR2(50),
   LOGINFO              CLOB,
   FULLCLASSNAME        VARCHAR2(200),
   ERROR_MESSAGE        CLOB,
   constraint PK_CF_2 primary key (ID)
);

/*==============================================================*/
/* Table: CF_SESSION_RECORD                                     */
/*==============================================================*/
create table CF_SESSION_RECORD  (
   ID                   NUMBER(19)                      not null,
   SESSION_KEY          VARCHAR2(20)                    not null,
   SESSIONSET_ID        NUMBER(19),
   SESSION_VALUE        BLOB,
   constraint PK_CF_3 primary key (ID)
);

/*==============================================================*/
/* Table: CF_SESSION_SET                                        */
/*==============================================================*/
create table CF_SESSION_SET  (
   ID                   NUMBER(19)                      not null,
   SESSION_ID           VARCHAR2(100)                   not null,
   CLIENT_ID            VARCHAR2(100),
   constraint PK_CF_4 primary key (ID)
);

/*==============================================================*/
/* Table: CM_LEAVE_APPLY                                        */
/*==============================================================*/
create table CM_LEAVE_APPLY  (
   ID                   NUMBER(19)                      not null,
   NAME                 VARCHAR2(255 CHAR),
   USER_ID              VARCHAR2(255 CHAR),
   APPLY_DATE           DATE,
   TYPE                 INTEGER,
   CONTENT              VARCHAR2(255 CHAR),
   FROM_DATE            DATE,
   TO_DATE              DATE,
   COUNT                INTEGER,
   LEADER_SIGN          NUMBER(1),
   MANAGER_SIGN         NUMBER(1),
   constraint PK_CF_606 primary key (ID)
);

/*==============================================================*/
/* Table: CM_URL_RESOURCE                                       */
/*==============================================================*/
create table CM_URL_RESOURCE  (
   ID                   NUMBER(19)                      not null,
   RESOURCE_NAME        VARCHAR2(255 CHAR),
   DESCRIPTION          VARCHAR2(255 CHAR),
   constraint PK_CF_609 primary key (ID)
);

/*==============================================================*/
/* Table: CP_CONSOLE_OPERS                                      */
/*==============================================================*/
create table CP_CONSOLE_OPERS  (
   ID                   NUMBER(19)                      not null,
   DESCRIPTION          VARCHAR2(255),
   PARENT_RESOURCE_ID   NUMBER(19),
   RESOURCE_NAME        VARCHAR2(255),
   CODE                 VARCHAR2(100),
   constraint PK_CF_5 primary key (ID),
   constraint PK_CF_6 unique (CODE)
);

/*==============================================================*/
/* Table: CP_INDIVIDUAL_VIEW                                    */
/*==============================================================*/
create table CP_INDIVIDUAL_VIEW  (
   ID                   NUMBER(19)                      not null,
   DESCRIPTION          VARCHAR2(255),
   DISP_ORDER           NUMBER(10),
   IMAGE                VARCHAR2(255),
   MENU_ID              NUMBER(19),
   NAME                 VARCHAR2(255),
   PARENT_VIEW_ID       NUMBER(19),
   USER_ID              NUMBER(19),
   constraint PK_CF_7 primary key (ID)
);

/*==============================================================*/
/* Table: CP_MENU_RESOURCE                                      */
/*==============================================================*/
create table CP_MENU_RESOURCE  (
   ID                   NUMBER(19)                      not null,
   CODE                 VARCHAR2(255),
   RESOURCE_NAME        VARCHAR2(255),
   LINKPATH             VARCHAR2(255),
   DISP_ORDER           NUMBER(10),
   VALID                NUMBER(1),
   PARENT_RESOURCE_ID   NUMBER(19),
   HANDLER              VARCHAR2(255),
   CLS                  VARCHAR2(255),
   MENU_TYPE            NUMBER(19),
   TARGET               VARCHAR2(255),
   BIG_IMAGE            NUMBER(19),
   SMALL_IMAGE          NUMBER(19),
   BACKUP_IMAGE         VARCHAR2(100),
   BACKUP_IMAGE2        VARCHAR2(100),
   HOST                 NUMBER(19),
   ICON                 VARCHAR2(255),
   DESCRIPTION          VARCHAR2(255),
   EXT1                 VARCHAR2(255),
   EXT2                 VARCHAR2(255),
   EXT3                 VARCHAR2(255),
   EXT4                 VARCHAR2(255),
   EXT5                 VARCHAR2(255),
   EXT6                 VARCHAR2(255),
   EXT7                 VARCHAR2(255),
   EXT8                 VARCHAR2(255),
   constraint PK_CF_8 primary key (ID)
);

/*==============================================================*/
/* Table: CP_URL_RESOURCE                                       */
/*==============================================================*/
create table CP_URL_RESOURCE  (
   ID                   NUMBER(19)                      not null,
   RESOURCE_NAME        VARCHAR2(255),
   PARENT_RESOURCE_ID   NUMBER(19),
   DISP_ORDER           NUMBER(10),
   LINKPATH             VARCHAR2(100),
   PARAMETERS           VARCHAR2(100),
   DESCRIPTION          VARCHAR2(200),
   CODE                 VARCHAR2(200)                   not null,
   constraint PK_CF_13 primary key (ID)
);

/*==============================================================*/
/* Table: CR_PROXY_MENU                                         */
/*==============================================================*/
create table CR_PROXY_MENU  (
   PROXY_ID             NUMBER(19)                      not null,
   MENU_ID              NUMBER(19)                      not null,
   constraint PK_CF_15 primary key (PROXY_ID, MENU_ID)
);

/*==============================================================*/
/* Table: CS_ACC_PERMISSION                                     */
/*==============================================================*/
create table CS_ACC_PERMISSION  (
   ACL_PERMISSION_ID    NUMBER(19)                      not null,
   SECURITY_ROLE_ID     NUMBER(19)                      not null,
   constraint PK_CF_18 primary key (SECURITY_ROLE_ID, ACL_PERMISSION_ID)
);

/*==============================================================*/
/* Table: CS_ACL_OPERATION                                      */
/*==============================================================*/
create table CS_ACL_OPERATION  (
   ID                   NUMBER(19)                      not null,
   CODE                 VARCHAR2(100),
   NAME                 VARCHAR2(255)                   not null,
   DESCRIPTION          VARCHAR2(255),
   ACL_RES_TYPE_ID      NUMBER(19),
   OPERATE_TIME         NUMBER(19),
   OPERATOR             VARCHAR2(50),
   REMOVED              NUMBER(10),
   constraint PK_CF_26 primary key (ID)
);

/*==============================================================*/
/* Table: CS_ACL_PERMISSION                                     */
/*==============================================================*/
create table CS_ACL_PERMISSION  (
   ID                   NUMBER(19)                      not null,
   ACL_OPERATION_ID     NUMBER(19),
   ACL_RESOURCE_ID      NUMBER(19),
   START_TIME           DATE,
   DUE_TIME             DATE,
   constraint PK_CF_28 primary key (ID)
);

/*==============================================================*/
/* Table: CS_ACL_RESOURCE                                       */
/*==============================================================*/
create table CS_ACL_RESOURCE  (
   ID                   NUMBER(19)                      not null,
   NAME                 VARCHAR2(255),
   DESCRIPTION          VARCHAR2(255),
   PARENT_ACL_RES_ID    NUMBER(19),
   ACL_RES_TYPE_ID      NUMBER(19),
   NATIVE_RESOURCE_ID   VARCHAR2(255 CHAR),
   constraint PK_CF_33 primary key (ID)
);

/*==============================================================*/
/* Table: CS_ACL_RES_TYPE                                       */
/*==============================================================*/
create table CS_ACL_RES_TYPE  (
   ID                   NUMBER(19)                      not null,
   WEBAPP_NODE_ID       NUMBER(19),
   CATALOG              NUMBER(10),
   CODE                 VARCHAR2(100),
   DESCRIPTION          VARCHAR2(255),
   NAME                 VARCHAR2(100)                   not null,
   CLASS_NAME           VARCHAR2(100),
   INHERITTYPE          NUMBER(10),
   OPERATE_TIME         NUMBER(19),
   OPERATOR             VARCHAR2(50),
   REMOVED              NUMBER(10),
   constraint PK_CF_31 primary key (ID)
);

/*==============================================================*/
/* Table: CS_ACTION_RESOURCE                                    */
/*==============================================================*/
create table CS_ACTION_RESOURCE  (
   ID                   NUMBER(19)                      not null,
   DESCRIPTION          VARCHAR2(255),
   PARENT_RESOURCE_ID   NUMBER(19),
   RESOURCE_NAME        VARCHAR2(255),
   CLASS_NAME           VARCHAR2(255)                   not null,
   METHOD_NAME          VARCHAR2(100)                   not null,
   constraint PK_CF_128 primary key (ID)
);

/*==============================================================*/
/* Table: CS_CUSTOM_RESOURCE                                    */
/*==============================================================*/
create table CS_CUSTOM_RESOURCE  (
   ID                   NUMBER(19)                      not null,
   CODE                 VARCHAR2(100),
   DESCRIPTION          VARCHAR2(255),
   PARENT_RESOURCE_ID   NUMBER(19),
   NAME                 VARCHAR2(255),
   ACL_RES_TYPE_ID      NUMBER(19),
   OPERATE_TIME         NUMBER(19),
   OPERATOR             VARCHAR2(50),
   REMOVED              NUMBER(10),
   constraint PK_CF_129 primary key (ID)
);

/*==============================================================*/
/* Table: CS_GROUP                                              */
/*==============================================================*/
create table CS_GROUP  (
   ID                   NUMBER(19)                      not null,
   DYNAACCESSCLASS      VARCHAR2(255),
   DYANPARAMS           VARCHAR2(255),
   DYNA                 NUMBER(10),
   OPERATE_TIME         NUMBER(19),
   OPERATOR             VARCHAR2(50),
   REMOVED              NUMBER(10),
   CODE                 VARCHAR2(100),
   NAME                 VARCHAR2(255)                   not null,
   DESCRIPTION          VARCHAR2(255),
   constraint PK_CF_41 primary key (ID)
);

/*==============================================================*/
/* Table: CS_GROUP_ROLE                                         */
/*==============================================================*/
create table CS_GROUP_ROLE  (
   SECURITY_GROUP_ID    NUMBER(19)                      not null,
   SECURITY_ROLE_ID     NUMBER(19)                      not null,
   constraint PK_CF_42 primary key (SECURITY_GROUP_ID, SECURITY_ROLE_ID)
);

/*==============================================================*/
/* Table: CS_ORGAN_EXTEND_PROPERTY                              */
/*==============================================================*/
create table CS_ORGAN_EXTEND_PROPERTY  (
   ID                   NUMBER(19)                      not null,
   EXT1                 VARCHAR2(255),
   EXT2                 VARCHAR2(255),
   EXT3                 VARCHAR2(255),
   EXT4                 VARCHAR2(255),
   EXT5                 VARCHAR2(255),
   EXT6                 VARCHAR2(255),
   EXT7                 VARCHAR2(255),
   EXT8                 VARCHAR2(255),
   EXT9                 VARCHAR2(255),
   EXT10                VARCHAR2(255),
   EXT11                VARCHAR2(255),
   EXT12                VARCHAR2(255),
   EXT13                VARCHAR2(255),
   EXT14                VARCHAR2(255),
   EXT15                VARCHAR2(255),
   EXT16                VARCHAR2(255),
   EXT17                VARCHAR2(255),
   EXT18                VARCHAR2(255),
   EXT19                VARCHAR2(255),
   EXT20                VARCHAR2(255),
   EXT21                VARCHAR2(255),
   EXT22                VARCHAR2(255),
   EXT23                VARCHAR2(255),
   EXT24                VARCHAR2(255),
   EXT25                VARCHAR2(255),
   EXT26                VARCHAR2(255),
   EXT27                VARCHAR2(255),
   EXT28                VARCHAR2(255),
   EXT29                VARCHAR2(255),
   EXT30                VARCHAR2(255),
   EXT31                VARCHAR2(255),
   EXT32                VARCHAR2(255),
   EXT33                VARCHAR2(255),
   EXT34                VARCHAR2(255),
   EXT35                VARCHAR2(255),
   EXT36                VARCHAR2(255),
   EXT37                VARCHAR2(255),
   EXT38                VARCHAR2(255),
   EXT39                VARCHAR2(255),
   EXT40                VARCHAR2(255),
   ORGAN_NODE_ID        NUMBER(19),
   constraint PK_CS_ORGAN_EXTEND_PROPERTY primary key (ID)
);

/*==============================================================*/
/* Table: CS_ORGAN_MODEL                                        */
/*==============================================================*/
create table CS_ORGAN_MODEL  (
   ID                   NUMBER(19)                      not null,
   LFT                  NUMBER(10),
   RGT                  NUMBER(10),
   NODESTATUS           VARCHAR2(255),
   ORDERS               NUMBER(19),
   RANK                 VARCHAR2(255),
   ORG_NODE_ID          NUMBER(19),
   ORG_TREE_ID          NUMBER(19),
   PARENT_NODE_ID       NUMBER(19),
   OPERATE_TIME         NUMBER(19),
   OPERATOR             VARCHAR2(50),
   REMOVED              NUMBER(10),
   constraint PK_CF_45 primary key (ID)
);

/*==============================================================*/
/* Table: CS_ORGAN_NODE                                         */
/*==============================================================*/
create table CS_ORGAN_NODE  (
   ID                   NUMBER(19)                      not null,
   CREATE_TIME          DATE,
   DEPT_ADDRESS         VARCHAR2(255),
   ICON                 NUMBER(19),
   ORGAN_NODE_TYPE_ID   NUMBER(19),
   PRINCIPAL            VARCHAR2(255),
   PRINCIPAL_PHONE      VARCHAR2(255),
   FAX                  VARCHAR2(255),
   ADMIN_USER_ID        NUMBER(19),
   OPERATE_TIME         NUMBER(19),
   OPERATOR             VARCHAR2(50),
   REMOVED              NUMBER(10),
   CODE                 VARCHAR2(100),
   NAME                 VARCHAR2(255)                   not null,
   DESCRIPTION          VARCHAR2(255),
   constraint PK_CF_49 primary key (ID)
);

/*==============================================================*/
/* Table: CS_ORGAN_NODE_TYPE                                    */
/*==============================================================*/
create table CS_ORGAN_NODE_TYPE  (
   ID                   NUMBER(19)                      not null,
   DOWN                 NUMBER(10),
   IMAGE                VARCHAR2(50),
   PEOPLE               NUMBER(10),
   IS_TOP               NUMBER(10),
   OPERATE_TIME         NUMBER(19),
   OPERATOR             VARCHAR2(50),
   REMOVED              NUMBER(10),
   CODE                 VARCHAR2(100),
   NAME                 VARCHAR2(255)                   not null,
   DESCRIPTION          VARCHAR2(255),
   constraint PK_CF_53 primary key (ID)
);

/*==============================================================*/
/* Table: CS_ORGAN_RELATION                                     */
/*==============================================================*/
create table CS_ORGAN_RELATION  (
   ID                   NUMBER(19)                      not null,
   CHILD_NODE_ID        NUMBER(19),
   CREATE_TIME          DATE,
   DESCRIPTION          VARCHAR2(255),
   NAME                 VARCHAR2(255)                   not null,
   ORGAN_REALTION_TYPE_ID NUMBER(19),
   PARENT_NODE_ID       NUMBER(19),
   OPERATE_TIME         NUMBER(19),
   OPERATOR             VARCHAR2(50),
   REMOVED              NUMBER(10),
   constraint PK_CF_54 primary key (ID)
);

/*==============================================================*/
/* Table: CS_ORGAN_RELATION_TYPE                                */
/*==============================================================*/
create table CS_ORGAN_RELATION_TYPE  (
   ID                   NUMBER(19)                      not null,
   OPERATE_TIME         NUMBER(19),
   OPERATOR             VARCHAR2(50),
   REMOVED              NUMBER(10),
   CODE                 VARCHAR2(100),
   NAME                 VARCHAR2(255)                   not null,
   DESCRIPTION          VARCHAR2(255),
   constraint PK_CF_58 primary key (ID)
);

/*==============================================================*/
/* Table: CS_ORGAN_ROLE                                         */
/*==============================================================*/
create table CS_ORGAN_ROLE  (
   ORGAN_MODEL_ID       NUMBER(19)                      not null,
   SECURITY_ROLE_ID     NUMBER(19)                      not null,
   constraint PK_CF_59 primary key (ORGAN_MODEL_ID, SECURITY_ROLE_ID)
);

/*==============================================================*/
/* Table: CS_ORGAN_RULE                                         */
/*==============================================================*/
create table CS_ORGAN_RULE  (
   ID                   NUMBER(19)                      not null,
   ORGAN_TREE_TYPE_ID   NUMBER(19),
   SUPERIOR_NODE_ID     NUMBER(19),
   SUBORDINATE_NODE_ID  NUMBER(19),
   OPERATE_TIME         NUMBER(19),
   OPERATOR             VARCHAR2(50),
   REMOVED              NUMBER(10),
   CODE                 VARCHAR2(100),
   NAME                 VARCHAR2(255)                   not null,
   DESCRIPTION          VARCHAR2(255),
   constraint PK_CF_62 primary key (ID)
);

/*==============================================================*/
/* Table: CS_ORGAN_TREE                                         */
/*==============================================================*/
create table CS_ORGAN_TREE  (
   ID                   NUMBER(19)                      not null,
   CREATE_TIME          DATE,
   ROOT_NODE_ID         NUMBER(19),
   ORGAN_TREE_TYPE_ID   NUMBER(19),
   OPERATE_TIME         NUMBER(19),
   OPERATOR             VARCHAR2(50),
   REMOVED              NUMBER(10),
   CODE                 VARCHAR2(100),
   NAME                 VARCHAR2(255)                   not null,
   DESCRIPTION          VARCHAR2(255),
   constraint PK_CF_65 primary key (ID)
);

/*==============================================================*/
/* Table: CS_ORGAN_TREE_TYPE                                    */
/*==============================================================*/
create table CS_ORGAN_TREE_TYPE  (
   ID                   NUMBER(19)                      not null,
   OPERATE_TIME         NUMBER(19),
   OPERATOR             VARCHAR2(50),
   REMOVED              NUMBER(10),
   CODE                 VARCHAR2(100),
   NAME                 VARCHAR2(255)                   not null,
   DESCRIPTION          VARCHAR2(255),
   constraint PK_CF_67 primary key (ID)
);

/*==============================================================*/
/* Table: CS_PROXY_USER                                         */
/*==============================================================*/
create table CS_PROXY_USER  (
   ID                   NUMBER(19)                      not null,
   DESCRIPTION          VARCHAR2(255),
   END_TIME             DATE,
   START_TIME           DATE,
   PROXY_ID             NUMBER(19),
   USER_ID              NUMBER(19),
   constraint PK_CF_68 primary key (ID)
);

/*==============================================================*/
/* Table: CS_PUB_PERMISSION                                     */
/*==============================================================*/
create table CS_PUB_PERMISSION  (
   ACL_PERMISSION_ID    NUMBER(19)                      not null,
   SECURITY_ROLE_ID     NUMBER(19)                      not null,
   constraint PK_CF_70 primary key (SECURITY_ROLE_ID, ACL_PERMISSION_ID)
);

/*==============================================================*/
/* Table: CS_RESOURCE_GROUP                                     */
/*==============================================================*/
create table CS_RESOURCE_GROUP  (
   ID                   NUMBER(19)                      not null,
   ACL_RES_TYPE_ID      NUMBER(19),
   CLASS_NAME           VARCHAR2(255)                   not null,
   CODE                 VARCHAR2(100),
   DESCRIPTION          VARCHAR2(255),
   NAME                 VARCHAR2(255),
   PROP_VALUES          VARCHAR2(255),
   OPERATE_TIME         NUMBER(19),
   OPERATOR             VARCHAR2(50),
   REMOVED              NUMBER(10),
   constraint PK_CF_73 primary key (ID)
);

/*==============================================================*/
/* Table: CS_REV_PERMISSION                                     */
/*==============================================================*/
create table CS_REV_PERMISSION  (
   ACL_PERMISSION_ID    NUMBER(19)                      not null,
   SECURITY_ROLE_ID     NUMBER(19)                      not null,
   constraint PK_CF_75 primary key (SECURITY_ROLE_ID, ACL_PERMISSION_ID)
);

/*==============================================================*/
/* Table: CS_ROLE                                               */
/*==============================================================*/
create table CS_ROLE  (
   ID                   NUMBER(19)                      not null,
   NAME                 VARCHAR2(100)                   not null,
   TYPE                 VARCHAR2(1)                     not null,
   DESCRIPTION          VARCHAR2(255),
   PARENT_ROLE_ID       NUMBER(19),
   OPERATE_TIME         NUMBER(19),
   OPERATOR             VARCHAR2(50),
   REMOVED              NUMBER(10),
   constraint PK_CF_78 primary key (ID),
   constraint PK_CF_79 unique (NAME)
);

/*==============================================================*/
/* Table: CS_USER                                               */
/*==============================================================*/
create table CS_USER  (
   ID                   NUMBER(19)                      not null,
   EXT1                 VARCHAR2(255),
   EXT2                 VARCHAR2(255),
   EXT3                 VARCHAR2(255),
   EXT4                 VARCHAR2(255),
   EXT5                 VARCHAR2(255),
   EXT6                 VARCHAR2(255),
   EXT7                 VARCHAR2(255),
   EXT8                 VARCHAR2(255),
   EMAIL                VARCHAR2(100),
   ADDRESS              VARCHAR2(255),
   MOBILE1              VARCHAR2(50),
   MOBILE2              VARCHAR2(50),
   FAX                  VARCHAR2(50),
   HOME_PHONE           VARCHAR2(50),
   OFFICE_PHONE         VARCHAR2(50),
   AUTHENTIC_TYPE       NUMBER(10),
   CERTIFICATE          VARCHAR2(255),
   STATUS               NUMBER(19),
   LOGIN_NAME           VARCHAR2(100)                   not null,
   NAME                 VARCHAR2(100),
   SEX                  VARCHAR2(255),
   PASSWORD             VARCHAR2(100)                   not null,
   USERTYPE             NUMBER(10),
   ACCOUNTTYPE          NUMBER(10),
   OPERATE_TIME         NUMBER(19),
   OPERATOR             VARCHAR2(50),
   REMOVED              NUMBER(10),
   CREATEDATE           NUMBER(19),
   RESET_CODE           VARCHAR2(255),
   constraint PK_CF_81 primary key (ID)
);

/*==============================================================*/
/* Table: CS_USER_APPNODE                                       */
/*==============================================================*/
create table CS_USER_APPNODE  (
   APP_NODE_ID          NUMBER(19)                      not null,
   USER_ID              NUMBER(19)                      not null,
   constraint PK_CF_82 primary key (USER_ID, APP_NODE_ID)
);

/*==============================================================*/
/* Table: CS_USER_EXTEND_PROPERTY                               */
/*==============================================================*/
create table CS_USER_EXTEND_PROPERTY  (
   ID                   NUMBER(19)                      not null,
   EXT1                 VARCHAR2(255),
   EXT2                 VARCHAR2(255),
   EXT3                 VARCHAR2(255),
   EXT4                 VARCHAR2(255),
   EXT5                 VARCHAR2(255),
   EXT6                 VARCHAR2(255),
   EXT7                 VARCHAR2(255),
   EXT8                 VARCHAR2(255),
   EXT9                 VARCHAR2(255),
   EXT10                VARCHAR2(255),
   EXT11                VARCHAR2(255),
   EXT12                VARCHAR2(255),
   EXT13                VARCHAR2(255),
   EXT14                VARCHAR2(255),
   EXT15                VARCHAR2(255),
   EXT16                VARCHAR2(255),
   EXT17                VARCHAR2(255),
   EXT18                VARCHAR2(255),
   EXT19                VARCHAR2(255),
   EXT20                VARCHAR2(255),
   EXT21                VARCHAR2(255),
   EXT22                VARCHAR2(255),
   EXT23                VARCHAR2(255),
   EXT24                VARCHAR2(255),
   EXT25                VARCHAR2(255),
   EXT26                VARCHAR2(255),
   EXT27                VARCHAR2(255),
   EXT28                VARCHAR2(255),
   EXT29                VARCHAR2(255),
   EXT30                VARCHAR2(255),
   EXT31                VARCHAR2(255),
   EXT32                VARCHAR2(255),
   EXT33                VARCHAR2(255),
   EXT34                VARCHAR2(255),
   EXT35                VARCHAR2(255),
   EXT36                VARCHAR2(255),
   EXT37                VARCHAR2(255),
   EXT38                VARCHAR2(255),
   EXT39                VARCHAR2(255),
   EXT40                VARCHAR2(255),
   SECURITY_USER_ID     NUMBER(19),
   constraint PK_CS_USER_EXTEND_PROPERTY primary key (ID)
);

/*==============================================================*/
/* Table: CS_USER_GROUP                                         */
/*==============================================================*/
create table CS_USER_GROUP  (
   SECURITY_GROUP_ID    NUMBER(19)                      not null,
   SECURITY_USER_ID     NUMBER(19)                      not null,
   constraint PK_CF_85 primary key (SECURITY_USER_ID, SECURITY_GROUP_ID)
);

/*==============================================================*/
/* Table: CS_USER_LOGIN                                         */
/*==============================================================*/
create table CS_USER_LOGIN  (
   ID                   NUMBER(19)                      not null,
   USER_ID              NUMBER(19),
   USER_NAME            VARCHAR2(255),
   LOGIN_NAME           VARCHAR2(255),
   APP_ID               NUMBER(19),
   APP_NAME             VARCHAR2(255),
   LOGIN_TIME           NUMBER(19),
   IP_ADDRESS           VARCHAR2(255),
   AREA                 VARCHAR2(255),
   OPERATING_SYSTEM     VARCHAR2(255),
   BROWSER              VARCHAR2(255),
   ONLINE_TIME          NUMBER(19),
   USER_STATE           NUMBER(10),
   ERROR_INFO           VARCHAR2(255),
   constraint PK_CS_USER_LOGIN primary key (ID)
);

/*==============================================================*/
/* Table: CS_USER_ORGANNODE                                     */
/*==============================================================*/
create table CS_USER_ORGANNODE  (
   ORGAN_NODE_ID        NUMBER(19)                      not null,
   SECURITY_USER_ID     NUMBER(19)                      not null,
   ORDERS               NUMBER(19),
   constraint PK_CF_125 primary key (SECURITY_USER_ID, ORGAN_NODE_ID)
);

/*==============================================================*/
/* Table: CS_USER_ROLE                                          */
/*==============================================================*/
create table CS_USER_ROLE  (
   SECURITY_ROLE_ID     NUMBER(19)                      not null,
   SECURITY_USER_ID     NUMBER(19)                      not null,
   constraint PK_CF_90 primary key (SECURITY_USER_ID, SECURITY_ROLE_ID)
);

/*==============================================================*/
/* Table: CS_WEBAPP_NODE                                        */
/*==============================================================*/
create table CS_WEBAPP_NODE  (
   ID                   NUMBER(19)                      not null,
   COMPATABLE           NUMBER(1),
   CENTRAL_NODE_ID      NUMBER(19),
   APP_SERVER           VARCHAR2(255),
   CODE                 VARCHAR2(100),
   DB_SERVER            VARCHAR2(255),
   DESCRIPTION          VARCHAR2(255),
   INDEX_URL            VARCHAR2(255),
   IS_CUTE_SERIES       NUMBER(1),
   NAME                 VARCHAR2(255),
   NODE_TYPE            NUMBER(19)                      not null,
   VENDOR               VARCHAR2(255),
   VERSION              VARCHAR2(255),
   OPERATE_TIME         NUMBER(19),
   OPERATOR             VARCHAR2(50),
   REMOVED              NUMBER(10),
   MAX_INACTIVE_SECS    NUMBER(19),
   LOGOUT_STYLE         NUMBER(1),
   ROOT_MENU_CODE       VARCHAR2(255),
   ORGAN_ID             NUMBER(19),
   constraint PK_CF_94 primary key (ID)
);

/*==============================================================*/
/* Table: CW_ACT_INSTANCE                                       */
/*==============================================================*/
create table CW_ACT_INSTANCE  (
   ACT_INSTANCE_ID      VARCHAR2(255)                   not null,
   ACTIVITY_ID          VARCHAR2(255),
   ACTIVITY_NAME        VARCHAR2(255),
   ACTIVITY_STATE       NUMBER(10),
   ACTIVITY_TYPE        NUMBER(10),
   END_TIME             DATE,
   INPUT_CONTAINERS     VARCHAR2(255),
   OUTPUT_CONTAINERS    VARCHAR2(255),
   PROCESS_INSTANCE_ID  VARCHAR2(255)                   not null,
   START_TIME           DATE,
   PROPERTIES           RAW(2000),
   constraint PK_CF_96 primary key (ACT_INSTANCE_ID)
);

/*==============================================================*/
/* Table: CW_AUDIT_ACT_INST                                     */
/*==============================================================*/
create table CW_AUDIT_ACT_INST  (
   ID                   NUMBER(19)                      not null,
   ACTIVITY_NAME        VARCHAR2(255)                   not null,
   PROCESS_INSTANCE_ID  VARCHAR2(255)                   not null,
   ACTIVITY_INSTANCE_ID VARCHAR2(255)                   not null,
   CHANGEDTIME          DATE                            not null,
   NEWSTATE             NUMBER(10),
   PERVIOUSSTATE        NUMBER(10),
   ACTION               NUMBER(10)                      not null,
   constraint PK_CF_98 primary key (ID)
);

/*==============================================================*/
/* Table: CW_AUDIT_CONFIG                                       */
/*==============================================================*/
create table CW_AUDIT_CONFIG  (
   ID                   NUMBER(19)                      not null,
   ISAUDIT_ACTIVITY_INSTANCE NUMBER(1),
   ISAUDIT_GLOBAL       NUMBER(1),
   ISAUDIT_PROCESS_INSTANCE NUMBER(1),
   ISAUDIT_PROCESS_MODEL NUMBER(1),
   ISAUDIT_WORKITEM     NUMBER(1),
   PROCESS_ID           VARCHAR2(255),
   ISAUDIT_PROCESS_DATA NUMBER(1),
   constraint PK_CF_99 primary key (ID),
   constraint PK_CF_100 unique (PROCESS_ID)
);

/*==============================================================*/
/* Table: CW_AUDIT_PDATA                                        */
/*==============================================================*/
create table CW_AUDIT_PDATA  (
   ID                   NUMBER(19)                      not null,
   CONTAINER_INSTANCE_ID VARCHAR2(255),
   ACTION               NUMBER(10),
   NEW_DATA             VARCHAR2(255),
   OLD_DATA             VARCHAR2(255),
   CHANGED_TIME         DATE,
   ATTRIBUTE_NAME       VARCHAR2(255),
   PROCESS_DATA_ID      NUMBER(19),
   PROCESS_INSTANCE_ID  VARCHAR2(255),
   constraint PK_CF_126 primary key (ID)
);

/*==============================================================*/
/* Table: CW_AUDIT_PINSTANCE                                    */
/*==============================================================*/
create table CW_AUDIT_PINSTANCE  (
   ID                   NUMBER(19)                      not null,
   PROCESS_NAME         VARCHAR2(255),
   PROCESS_ID           VARCHAR2(255)                   not null,
   PROCESS_INSTANCE_ID  VARCHAR2(255)                   not null,
   CHANGEDTIME          DATE                            not null,
   NEWSTATE             NUMBER(10),
   PERVIOUSSTATE        NUMBER(10),
   ACTION               NUMBER(10)                      not null,
   constraint PK_CF_127 primary key (ID)
);

/*==============================================================*/
/* Table: CW_AUDIT_PMODEL                                       */
/*==============================================================*/
create table CW_AUDIT_PMODEL  (
   ID                   NUMBER(19)                      not null,
   PROCESS_NAME         VARCHAR2(255),
   MODEL_ID             VARCHAR2(255),
   PROCESS_ID           VARCHAR2(255),
   CHANGEDTIME          DATE                            not null,
   NEWSTATE             NUMBER(10),
   PERVIOUSSTATE        NUMBER(10),
   ACTION               NUMBER(10)                      not null,
   constraint PK_CF_102 primary key (ID)
);

/*==============================================================*/
/* Table: CW_AUDIT_WORKITEM                                     */
/*==============================================================*/
create table CW_AUDIT_WORKITEM  (
   ID                   NUMBER(19)                      not null,
   ACTIVITY_NAME        VARCHAR2(255),
   WORKITEM_ID          NUMBER(19),
   ACTIVITY_INSTANCE_ID VARCHAR2(255),
   CHANGEDTIME          DATE                            not null,
   NEWSTATE             NUMBER(10),
   PERVIOUSSTATE        NUMBER(10),
   ACTION               NUMBER(10)                      not null,
   constraint PK_CF_103 primary key (ID)
);

/*==============================================================*/
/* Table: CW_CON_INSTANCE                                       */
/*==============================================================*/
create table CW_CON_INSTANCE  (
   CON_INSTANCE_ID      VARCHAR2(255)                   not null,
   PROCESS_INSTANCE_ID  VARCHAR2(255),
   CONTAINER_ID         VARCHAR2(255),
   CONTAINER_NAME       VARCHAR2(255),
   constraint PK_CF_104 primary key (CON_INSTANCE_ID)
);

/*==============================================================*/
/* Table: CW_PROCESS_DATA                                       */
/*==============================================================*/
create table CW_PROCESS_DATA  (
   ID                   NUMBER(19)                      not null,
   ATTRIBUTE_NAME       VARCHAR2(255),
   ATTRIBUTE_TYPE       VARCHAR2(255),
   ATTRIBUTE_VALUE      VARCHAR2(255),
   CON_INSTANCE_ID      VARCHAR2(255),
   MODIFIED_TIME        DATE,
   RECORD_INDEX         NUMBER(10),
   constraint PK_CF_105 primary key (ID)
);

/*==============================================================*/
/* Table: CW_PROCESS_INSTANCE                                   */
/*==============================================================*/
create table CW_PROCESS_INSTANCE  (
   PROCESS_INSTANCE_ID  VARCHAR2(255)                   not null,
   ENABLED_ACTIVITIES   VARCHAR2(255),
   END_TIME             DATE,
   EXTERNALE_KEY        VARCHAR2(255),
   PROCESS_ID           VARCHAR2(255)                   not null,
   PROCESS_STATE        NUMBER(10),
   START_TIME           DATE,
   PROCESS_NAME         VARCHAR2(255),
   PROPERTIES           RAW(2000),
   BUSINESS_ENTITYID    VARCHAR2(255),
   STATE_DESCRIPTION    VARCHAR2(255),
   PARENT_INSTANCE_ID   VARCHAR2(255 CHAR),
   constraint PK_CF_107 primary key (PROCESS_INSTANCE_ID)
);

/*==============================================================*/
/* Table: CW_PROCESS_MODEL                                      */
/*==============================================================*/
create table CW_PROCESS_MODEL  (
   PROCESS_ID           VARCHAR2(255)                   not null,
   BUSINESS_TYPE        NUMBER(10),
   DESCRIPTION          VARCHAR2(255),
   MODEL                CLOB,
   MODEL_ID             VARCHAR2(255),
   NAME                 VARCHAR2(255),
   PUBLISH_STATE        NUMBER(10),
   VERSION              NUMBER(10),
   constraint PK_CF_109 primary key (PROCESS_ID)
);

/*==============================================================*/
/* Table: CW_TIME_ACTIVITY                                      */
/*==============================================================*/
create table CW_TIME_ACTIVITY  (
   ID                   NUMBER(19)                      not null,
   EXPECTED_TIME        DATE,
   ACTIVITY_INSTANCE_ID VARCHAR2(255),
   STARTED_TIME         DATE,
   constraint PK_CF_110 primary key (ID)
);

/*==============================================================*/
/* Table: CW_TRANS_INSTANCE                                     */
/*==============================================================*/
create table CW_TRANS_INSTANCE  (
   TRANS_INSTANCE_ID    VARCHAR2(255)                   not null,
   FROM_AINSTANCE_ID    VARCHAR2(255),
   MODIFIED_TIME        DATE,
   TO_AINSTANCE_ID      VARCHAR2(255),
   PROCESS_INSTANCE_ID  VARCHAR2(255),
   TRANSITION_ID        VARCHAR2(255),
   TRANSITION_NAME      VARCHAR2(255),
   TRANSITION_STATE     NUMBER(10),
   TRANSITION_TYPE      NUMBER(10),
   PROPERTIES           RAW(2000),
   constraint PK_CF_112 primary key (TRANS_INSTANCE_ID)
);

/*==============================================================*/
/* Table: CW_WORK_ITEMS                                         */
/*==============================================================*/
create table CW_WORK_ITEMS  (
   WORKITEM_ID          NUMBER(19)                      not null,
   ACTIVITY_ID          VARCHAR2(255),
   ACT_INSTANCE_ID      VARCHAR2(255),
   ACTIVITY_NAME        VARCHAR2(255),
   DEADLINE             DATE,
   DESCRIPTION          VARCHAR2(255),
   OUTER_ID             VARCHAR2(255),
   WORKITEM_TYPE        NUMBER(10),
   WORK_STATE           NUMBER(10),
   MODEL_ID             VARCHAR2(255 CHAR),
   PROCESS_INSTANCE_ID  VARCHAR2(255 CHAR),
   constraint PK_CF_117 primary key (WORKITEM_ID)
);

/*==============================================================*/
/* Table: CW_WORK_PERSON                                        */
/*==============================================================*/
create table CW_WORK_PERSON  (
   ID                   NUMBER(19)                      not null,
   DESCRIPTION          VARCHAR2(255),
   IS_SHOW              NUMBER(1),
   EXECUTE_TIME         DATE,
   WORK_INDEX           NUMBER(10),
   PERSON_ID            VARCHAR2(255),
   WORKITEM_ID          NUMBER(19),
   constraint PK_CF_119 primary key (ID)
);

alter table CD_ATTRIBUTE_PRIVILEGE
   add constraint PK_CF_334 foreign key (GROUP_ID)
      references CS_GROUP (ID);

alter table CF_CODE_INFO
   add constraint PK_CF_500 foreign key (CODE_INFO_ID)
      references CF_CODE_INFO (ID);

alter table CF_CODE_INFO
   add constraint PK_CF_300 foreign key (TYPE_ID)
      references CF_CODE_TYPE (ID);

alter table CF_DEMO_COAUDIT
   add constraint PK_CF_333 foreign key (CO_AUDIT_ID)
      references CF_DEMO_DRAFT (ID);

alter table CF_DEMO_DRAFT
   add constraint PK_CF_335 foreign key (TYPE)
      references CF_CODE_INFO (ID);

alter table CF_DEMO_DRAFT
   add constraint PK_CF_336 foreign key (CONFIDENTIAL_LEVEL)
      references CF_CODE_INFO (ID);

alter table CF_DEMO_DRAFT
   add constraint PK_CF_337 foreign key (PRIORITY)
      references CF_CODE_INFO (ID);

alter table CF_SESSION_RECORD
   add constraint PK_CF_20 foreign key (SESSIONSET_ID)
      references CF_SESSION_SET (ID);

alter table CP_CONSOLE_OPERS
   add constraint PK_CF_21 foreign key (PARENT_RESOURCE_ID)
      references CP_CONSOLE_OPERS (ID);

alter table CP_INDIVIDUAL_VIEW
   add constraint PK_CF_22 foreign key (MENU_ID)
      references CP_MENU_RESOURCE (ID);

alter table CP_INDIVIDUAL_VIEW
   add constraint PK_CF_23 foreign key (PARENT_VIEW_ID)
      references CP_INDIVIDUAL_VIEW (ID);

alter table CP_INDIVIDUAL_VIEW
   add constraint PK_CF_24 foreign key (USER_ID)
      references CS_USER (ID);

alter table CP_MENU_RESOURCE
   add constraint PK_CF_122 foreign key (PARENT_RESOURCE_ID)
      references CP_MENU_RESOURCE (ID);

alter table CP_MENU_RESOURCE
   add constraint PK_CF_11 foreign key (HOST)
      references CF_CODE_INFO (ID);

alter table CP_MENU_RESOURCE
   add constraint PK_CF_12 foreign key (MENU_TYPE)
      references CF_CODE_INFO (ID);

alter table CP_MENU_RESOURCE
   add constraint PK_CF_9 foreign key (BIG_IMAGE)
      references CF_CODE_INFO (ID);

alter table CP_MENU_RESOURCE
   add constraint PK_CF_10 foreign key (SMALL_IMAGE)
      references CF_CODE_INFO (ID);

alter table CP_URL_RESOURCE
   add constraint PK_CF_14 foreign key (PARENT_RESOURCE_ID)
      references CP_URL_RESOURCE (ID);

alter table CR_PROXY_MENU
   add constraint PK_CF_16 foreign key (MENU_ID)
      references CP_MENU_RESOURCE (ID);

alter table CR_PROXY_MENU
   add constraint PK_CF_17 foreign key (PROXY_ID)
      references CS_PROXY_USER (ID);

alter table CS_ACC_PERMISSION
   add constraint PK_CF_121 foreign key (ACL_PERMISSION_ID)
      references CS_ACL_PERMISSION (ID);

alter table CS_ACC_PERMISSION
   add constraint PK_CF_25 foreign key (SECURITY_ROLE_ID)
      references CS_ROLE (ID);

alter table CS_ACL_OPERATION
   add constraint PK_CF_27 foreign key (ACL_RES_TYPE_ID)
      references CS_ACL_RES_TYPE (ID);

alter table CS_ACL_PERMISSION
   add constraint PK_CF_29 foreign key (ACL_OPERATION_ID)
      references CS_ACL_OPERATION (ID);

alter table CS_ACL_RESOURCE
   add constraint PK_CF_34 foreign key (PARENT_ACL_RES_ID)
      references CS_ACL_RESOURCE (ID);

alter table CS_ACL_RES_TYPE
   add constraint PK_CF_32 foreign key (WEBAPP_NODE_ID)
      references CS_WEBAPP_NODE (ID);

alter table CS_ACTION_RESOURCE
   add constraint PK_CF_37 foreign key (PARENT_RESOURCE_ID)
      references CS_ACTION_RESOURCE (ID);

alter table CS_CUSTOM_RESOURCE
   add constraint PK_CF_39 foreign key (PARENT_RESOURCE_ID)
      references CS_CUSTOM_RESOURCE (ID);

alter table CS_CUSTOM_RESOURCE
   add constraint PK_CF_40 foreign key (ACL_RES_TYPE_ID)
      references CS_ACL_RES_TYPE (ID);

alter table CS_GROUP_ROLE
   add constraint PK_CF_43 foreign key (SECURITY_GROUP_ID)
      references CS_GROUP (ID);

alter table CS_GROUP_ROLE
   add constraint PK_CF_44 foreign key (SECURITY_ROLE_ID)
      references CS_ROLE (ID);

alter table CS_ORGAN_EXTEND_PROPERTY
   add constraint PK_CF_339 foreign key (ORGAN_NODE_ID)
      references CS_ORGAN_NODE (ID);

alter table CS_ORGAN_MODEL
   add constraint PK_CF_46 foreign key (ORG_NODE_ID)
      references CS_ORGAN_NODE (ID);

alter table CS_ORGAN_MODEL
   add constraint PK_CF_47 foreign key (PARENT_NODE_ID)
      references CS_ORGAN_NODE (ID);

alter table CS_ORGAN_MODEL
   add constraint PK_CF_48 foreign key (ORG_TREE_ID)
      references CS_ORGAN_TREE (ID);

alter table CS_ORGAN_NODE
   add constraint PK_CF_50 foreign key (ADMIN_USER_ID)
      references CS_USER (ID);

alter table CS_ORGAN_NODE
   add constraint PK_CF_51 foreign key (ICON)
      references CF_CODE_INFO (ID);

alter table CS_ORGAN_NODE
   add constraint PK_CF_52 foreign key (ORGAN_NODE_TYPE_ID)
      references CS_ORGAN_NODE_TYPE (ID);

alter table CS_ORGAN_RELATION
   add constraint PK_CF_55 foreign key (CHILD_NODE_ID)
      references CS_ORGAN_NODE (ID);

alter table CS_ORGAN_RELATION
   add constraint PK_CF_56 foreign key (PARENT_NODE_ID)
      references CS_ORGAN_NODE (ID);

alter table CS_ORGAN_RELATION
   add constraint PK_CF_57 foreign key (ORGAN_REALTION_TYPE_ID)
      references CS_ORGAN_RELATION_TYPE (ID);

alter table CS_ORGAN_ROLE
   add constraint PK_CF_60 foreign key (SECURITY_ROLE_ID)
      references CS_ROLE (ID);

alter table CS_ORGAN_ROLE
   add constraint PK_CF_61 foreign key (ORGAN_MODEL_ID)
      references CS_ORGAN_MODEL (ID);

alter table CS_ORGAN_RULE
   add constraint PK_CF_63 foreign key (SUPERIOR_NODE_ID)
      references CS_ORGAN_NODE_TYPE (ID);

alter table CS_ORGAN_RULE
   add constraint PK_CF_64 foreign key (SUBORDINATE_NODE_ID)
      references CS_ORGAN_NODE_TYPE (ID);

alter table CS_ORGAN_TREE
   add constraint PK_CF_66 foreign key (ORGAN_TREE_TYPE_ID)
      references CS_ORGAN_TREE_TYPE (ID);

alter table CS_PROXY_USER
   add constraint PK_CF_69 foreign key (USER_ID)
      references CS_USER (ID);

alter table CS_PUB_PERMISSION
   add constraint PK_CF_71 foreign key (ACL_PERMISSION_ID)
      references CS_ACL_PERMISSION (ID);

alter table CS_PUB_PERMISSION
   add constraint PK_CF_72 foreign key (SECURITY_ROLE_ID)
      references CS_ROLE (ID);

alter table CS_RESOURCE_GROUP
   add constraint PK_CF_74 foreign key (ACL_RES_TYPE_ID)
      references CS_ACL_RES_TYPE (ID);

alter table CS_REV_PERMISSION
   add constraint PK_CF_76 foreign key (ACL_PERMISSION_ID)
      references CS_ACL_PERMISSION (ID);

alter table CS_REV_PERMISSION
   add constraint PK_CF_77 foreign key (SECURITY_ROLE_ID)
      references CS_ROLE (ID);

alter table CS_ROLE
   add constraint PK_CF_80 foreign key (PARENT_ROLE_ID)
      references CS_ROLE (ID);

alter table CS_USER_APPNODE
   add constraint PK_CF_83 foreign key (APP_NODE_ID)
      references CS_WEBAPP_NODE (ID);

alter table CS_USER_APPNODE
   add constraint PK_CF_84 foreign key (USER_ID)
      references CS_USER (ID);

alter table CS_USER_EXTEND_PROPERTY
   add constraint PK_CF_338 foreign key (SECURITY_USER_ID)
      references CS_USER (ID);

alter table CS_USER_GROUP
   add constraint PK_CF_86 foreign key (SECURITY_GROUP_ID)
      references CS_GROUP (ID);

alter table CS_USER_GROUP
   add constraint PK_CF_123 foreign key (SECURITY_USER_ID)
      references CS_USER (ID);

alter table CS_USER_LOGIN
   add constraint PK_CF_340 foreign key (USER_ID)
      references CS_USER (ID);

alter table CS_USER_ORGANNODE
   add constraint PK_CF_88 foreign key (SECURITY_USER_ID)
      references CS_USER (ID);

alter table CS_USER_ORGANNODE
   add constraint PK_CF_89 foreign key (ORGAN_NODE_ID)
      references CS_ORGAN_NODE (ID);

alter table CS_USER_ROLE
   add constraint PK_CF_91 foreign key (SECURITY_USER_ID)
      references CS_USER (ID);

alter table CS_USER_ROLE
   add constraint PK_CF_92 foreign key (SECURITY_ROLE_ID)
      references CS_ROLE (ID);

alter table CW_ACT_INSTANCE
   add constraint PK_CF_97 foreign key (PROCESS_INSTANCE_ID)
      references CW_PROCESS_INSTANCE (PROCESS_INSTANCE_ID);

alter table CW_PROCESS_DATA
   add constraint PK_CF_106 foreign key (CON_INSTANCE_ID)
      references CW_CON_INSTANCE (CON_INSTANCE_ID);

alter table CW_PROCESS_INSTANCE
   add constraint PK_CF_108 foreign key (PROCESS_ID)
      references CW_PROCESS_MODEL (PROCESS_ID);

alter table CW_TIME_ACTIVITY
   add constraint PK_CF_111 foreign key (ACTIVITY_INSTANCE_ID)
      references CW_ACT_INSTANCE (ACT_INSTANCE_ID);

alter table CW_TRANS_INSTANCE
   add constraint PK_CF_113 foreign key (TO_AINSTANCE_ID)
      references CW_ACT_INSTANCE (ACT_INSTANCE_ID);

alter table CW_TRANS_INSTANCE
   add constraint PK_CF_115 foreign key (FROM_AINSTANCE_ID)
      references CW_ACT_INSTANCE (ACT_INSTANCE_ID);

alter table CW_TRANS_INSTANCE
   add constraint PK_CF_116 foreign key (PROCESS_INSTANCE_ID)
      references CW_PROCESS_INSTANCE (PROCESS_INSTANCE_ID);

alter table CW_WORK_ITEMS
   add constraint PK_CF_118 foreign key (ACT_INSTANCE_ID)
      references CW_ACT_INSTANCE (ACT_INSTANCE_ID);

alter table CW_WORK_PERSON
   add constraint PK_CF_120 foreign key (WORKITEM_ID)
      references CW_WORK_ITEMS (WORKITEM_ID);

