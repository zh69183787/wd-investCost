
    drop table CD_ATTRIBUTE_PRIVILEGE;

    drop table CF_CODE_INFO;

    drop table CF_CODE_TYPE;

    drop table CF_DEMO_COAUDIT;

    drop table CF_DEMO_DRAFT;

    drop table CF_EXTEND_PROPERTY_CONFIG;

    drop table CF_LOG;

    drop table CF_SESSION_RECORD;

    drop table CF_SESSION_SET;

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

    drop table CS_ORGAN_TYPE;

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

    create table CD_ATTRIBUTE_PRIVILEGE (
        ID bigint not null,
        ATTRIBUTE_NAME varchar(255),
        PRIVILEGE integer,
        GROUP_ID bigint,
        primary key (ID)
    )in CFSPACE;

    create table CF_CODE_INFO (
        ID bigint not null,
        OPERATE_TIME bigint,
        OPERATOR varchar(50),
        REMOVED integer,
        CODE varchar(100),
        DESCRIPTION varchar(255),
        NAME varchar(255) not null,
        DISP_ORDER integer,
        REMARK1 varchar(255),
        REMARK2 varchar(255),
        TYPE_ID bigint,
        CODE_INFO_ID bigint,
        primary key (ID)
    )in CFSPACE;

    create table CF_CODE_TYPE (
        ID bigint not null,
        OPERATE_TIME bigint,
        OPERATOR varchar(50),
        REMOVED integer,
        CODE varchar(100),
        DESCRIPTION varchar(255),
        DISP_ORDER integer,
        EXTERNAL_MAPPING varchar(255),
        LEVE integer,
        NAME varchar(255) not null,
        STORAGE_TYPE integer,
        SYSTEM_TYPE integer,
        primary key (ID)
    )in CFSPACE;

    create table CF_DEMO_COAUDIT (
        ID bigint not null,
        CONTENT varchar(255),
        CREATE_DATE date,
        CREATOR varchar(255),
        CO_AUDIT_ID bigint,
        primary key (ID)
    )in CFSPACE;

    create table CF_DEMO_DRAFT (
        ID bigint not null,
        AFFILIATED_COMMENT varchar(255),
        AFFILIATED_DATE date,
        ARCHIVE_COMMENT varchar(255),
        ARCHIVE_DATE date,
        AUDITOR varchar(255),
        AUDITOR_DATE date,
        CONTENT varchar(255),
        CREATE_DATE date,
        CREATOR varchar(255),
        FILE_CODE varchar(255),
        HEADER_COMMENT varchar(255),
        HEADER_DATE date,
        OFFICIAL_COMMENT varchar(255),
        OFFICIAL_DATE date,
        PRINT_COMMENT varchar(255),
        PRINT_DATE date,
        TITLE varchar(255),
        VERIFIED_COMMENT varchar(255),
        VERIFIED_DATE date,
        PROCESS_INSTANCE varchar(255),
        CONFIDENTIAL_LEVEL bigint,
        PRIORITY bigint,
        TYPE bigint,
        primary key (ID)
    )in CFSPACE;

    create table CF_EXTEND_PROPERTY_CONFIG (
        ID bigint not null,
        CLASS_NAME varchar(255),
        EXT1 varchar(255),
        EXT10 varchar(255),
        EXT11 varchar(255),
        EXT12 varchar(255),
        EXT13 varchar(255),
        EXT14 varchar(255),
        EXT15 varchar(255),
        EXT16 varchar(255),
        EXT17 varchar(255),
        EXT18 varchar(255),
        EXT19 varchar(255),
        EXT2 varchar(255),
        EXT20 varchar(255),
        EXT21 varchar(255),
        EXT22 varchar(255),
        EXT23 varchar(255),
        EXT24 varchar(255),
        EXT25 varchar(255),
        EXT26 varchar(255),
        EXT27 varchar(255),
        EXT28 varchar(255),
        EXT29 varchar(255),
        EXT3 varchar(255),
        EXT30 varchar(255),
        EXT31 varchar(255),
        EXT32 varchar(255),
        EXT33 varchar(255),
        EXT34 varchar(255),
        EXT35 varchar(255),
        EXT36 varchar(255),
        EXT37 varchar(255),
        EXT38 varchar(255),
        EXT39 varchar(255),
        EXT4 varchar(255),
        EXT40 varchar(255),
        EXT5 varchar(255),
        EXT6 varchar(255),
        EXT7 varchar(255),
        EXT8 varchar(255),
        EXT9 varchar(255),
        primary key (ID)
    )in CFSPACE;

    create table CF_LOG (
        ID bigint not null,
        ERROR_MESSAGE clob,
        FULLCLASSNAME varchar(200),
        LOGINFO clob,
        LOGINDATE timestamp,
        OPERDATE timestamp,
        USERID varchar(20),
        userName varchar(255),
        primary key (ID)
    )in CFSPACE;

    create table CF_SESSION_RECORD (
        ID bigint not null,
        SESSION_KEY varchar(20) not null,
        SESSION_VALUE blob(255),
        SESSIONSET_ID bigint,
        primary key (ID)
    )in CFSPACE;

    create table CF_SESSION_SET (
        ID bigint not null,
        CLIENT_ID varchar(100),
        SESSION_ID varchar(100) not null,
        primary key (ID)
    )in CFSPACE;

    create table CP_CONSOLE_OPERS (
        ID bigint not null,
        CODE varchar(100),
        DESCRIPTION varchar(255),
        RESOURCE_NAME varchar(255) not null,
        PARENT_RESOURCE_ID bigint,
        primary key (ID)
    )in CFSPACE;

    create table CP_INDIVIDUAL_VIEW (
        ID bigint not null,
        DESCRIPTION varchar(255),
        DISP_ORDER integer,
        IMAGE varchar(255),
        NAME varchar(255) not null,
        MENU_ID bigint,
        PARENT_VIEW_ID bigint,
        USER_ID bigint,
        primary key (ID)
    )in CFSPACE;

    create table CP_MENU_RESOURCE (
        ID bigint not null,
        BACKUP_IMAGE varchar(100),
        BACKUP_IMAGE2 varchar(100),
        CODE varchar(255),
        DESCRIPTION varchar(255),
        DISP_ORDER integer,
        LINKPATH varchar(255),
        RESOURCE_NAME varchar(255) not null,
        VALID smallint,
        CLS varchar(255),
        EXT1 varchar(255),
        EXT2 varchar(255),
        EXT3 varchar(255),
        EXT4 varchar(255),
        EXT5 varchar(255),
        EXT6 varchar(255),
        EXT7 varchar(255),
        EXT8 varchar(255),
        HANDLER varchar(255),
        ICON varchar(255),
        TARGET varchar(255),
        BIG_IMAGE bigint,
        PARENT_RESOURCE_ID bigint,
        SMALL_IMAGE bigint,
        HOST bigint,
        MENU_TYPE bigint,
        primary key (ID)
    )in CFSPACE;

    create table CP_URL_RESOURCE (
        ID bigint not null,
        CODE varchar(200),
        DESCRIPTION varchar(200),
        DISP_ORDER integer,
        LINKPATH varchar(100),
        PARAMETERS varchar(100),
        RESOURCE_NAME varchar(255) not null,
        PARENT_RESOURCE_ID bigint,
        primary key (ID)
    )in CFSPACE;

    create table CR_PROXY_MENU (
        PROXY_ID bigint not null,
        MENU_ID bigint not null,
        primary key (PROXY_ID, MENU_ID)
    )in CFSPACE;

    create table CS_ACC_PERMISSION (
        ACL_PERMISSION_ID bigint not null,
        SECURITY_ROLE_ID bigint not null,
        primary key (SECURITY_ROLE_ID, ACL_PERMISSION_ID)
    )in CFSPACE;

    create table CS_ACL_OPERATION (
        ID bigint not null,
        OPERATE_TIME bigint,
        OPERATOR varchar(50),
        REMOVED integer,
        CODE varchar(100),
        DESCRIPTION varchar(255),
        NAME varchar(255) not null,
        ACL_RES_TYPE_ID bigint,
        primary key (ID)
    )in CFSPACE;

    create table CS_ACL_PERMISSION (
        ID bigint not null,
        DUE_TIME date,
        START_TIME date,
        ACL_OPERATION_ID bigint,
        ACL_RESOURCE_ID bigint,
        primary key (ID)
    )in CFSPACE;

    create table CS_ACL_RESOURCE (
        ID bigint not null,
        DESCRIPTION varchar(255),
        NAME varchar(255),
        NATIVE_RESOURCE_ID varchar(255) not null,
        ACL_RES_TYPE_ID bigint,
        PARENT_ACL_RES_ID bigint,
        primary key (ID)
    )in CFSPACE;

    create table CS_ACL_RES_TYPE (
        ID bigint not null,
        OPERATE_TIME bigint,
        OPERATOR varchar(50),
        REMOVED integer,
        CATALOG integer,
        CLASS_NAME varchar(100),
        CODE varchar(100),
        DESCRIPTION varchar(255),
        INHERITTYPE integer,
        NAME varchar(100) not null,
        WEBAPP_NODE_ID bigint,
        primary key (ID)
    )in CFSPACE;

    create table CS_ACTION_RESOURCE (
        ID bigint not null,
        CLASS_NAME varchar(255) not null,
        DESCRIPTION varchar(255),
        METHOD_NAME varchar(100) not null,
        RESOURCE_NAME varchar(255) not null,
        PARENT_RESOURCE_ID bigint,
        primary key (ID)
    )in CFSPACE;

    create table CS_CUSTOM_RESOURCE (
        ID bigint not null,
        OPERATE_TIME bigint,
        OPERATOR varchar(50),
        REMOVED integer,
        CODE varchar(100),
        DESCRIPTION varchar(255),
        NAME varchar(255),
        PARENT_RESOURCE_ID bigint,
        ACL_RES_TYPE_ID bigint,
        primary key (ID)
    )in CFSPACE;

    create table CS_GROUP (
        ID bigint not null,
        CODE varchar(100),
        DESCRIPTION varchar(255),
        NAME varchar(255) not null,
        OPERATE_TIME bigint,
        OPERATOR varchar(50),
        REMOVED integer,
        DYNA integer,
        DYNAACCESSCLASS varchar(255),
        DYANPARAMS varchar(255),
        primary key (ID)
    )in CFSPACE;

    create table CS_GROUP_ROLE (
        SECURITY_ROLE_ID bigint not null,
        SECURITY_GROUP_ID bigint not null,
        primary key (SECURITY_GROUP_ID, SECURITY_ROLE_ID)
    )in CFSPACE;

    create table CS_ORGAN_EXTEND_PROPERTY (
        ID bigint not null,
        EXT1 varchar(255),
        EXT10 varchar(255),
        EXT11 varchar(255),
        EXT12 varchar(255),
        EXT13 varchar(255),
        EXT14 varchar(255),
        EXT15 varchar(255),
        EXT16 varchar(255),
        EXT17 varchar(255),
        EXT18 varchar(255),
        EXT19 varchar(255),
        EXT2 varchar(255),
        EXT20 varchar(255),
        EXT21 varchar(255),
        EXT22 varchar(255),
        EXT23 varchar(255),
        EXT24 varchar(255),
        EXT25 varchar(255),
        EXT26 varchar(255),
        EXT27 varchar(255),
        EXT28 varchar(255),
        EXT29 varchar(255),
        EXT3 varchar(255),
        EXT30 varchar(255),
        EXT31 varchar(255),
        EXT32 varchar(255),
        EXT33 varchar(255),
        EXT34 varchar(255),
        EXT35 varchar(255),
        EXT36 varchar(255),
        EXT37 varchar(255),
        EXT38 varchar(255),
        EXT39 varchar(255),
        EXT4 varchar(255),
        EXT40 varchar(255),
        EXT5 varchar(255),
        EXT6 varchar(255),
        EXT7 varchar(255),
        EXT8 varchar(255),
        EXT9 varchar(255),
        ORGAN_NODE_ID bigint,
        primary key (ID)
    )in CFSPACE;

    create table CS_ORGAN_MODEL (
        ID bigint not null,
        OPERATE_TIME bigint,
        OPERATOR varchar(50),
        REMOVED integer,
        ORDERS bigint,
        RANK varchar(255),
        LFT integer,
        NODESTATUS varchar(255),
        RGT integer,
        ORG_NODE_ID bigint,
        ORG_TREE_ID bigint,
        PARENT_NODE_ID bigint,
        primary key (ID)
    )in CFSPACE;

    create table CS_ORGAN_NODE (
        ID bigint not null,
        CODE varchar(100),
        DESCRIPTION varchar(255),
        NAME varchar(255) not null,
        OPERATE_TIME bigint,
        OPERATOR varchar(50),
        REMOVED integer,
        CREATE_TIME date,
        DEPT_ADDRESS varchar(255),
        FAX varchar(255),
        PRINCIPAL varchar(255),
        PRINCIPAL_PHONE varchar(255),
        ADMIN_USER_ID bigint,
        ICON bigint,
        ORGAN_NODE_TYPE_ID bigint,
        primary key (ID)
    )in CFSPACE;

    create table CS_ORGAN_NODE_TYPE (
        ID bigint not null,
        CODE varchar(100),
        DESCRIPTION varchar(255),
        NAME varchar(255) not null,
        OPERATE_TIME bigint,
        OPERATOR varchar(50),
        REMOVED integer,
        DOWN integer,
        IMAGE varchar(50),
        PEOPLE integer,
        IS_TOP integer,
        primary key (ID)
    )in CFSPACE;

    create table CS_ORGAN_RELATION (
        ID bigint not null,
        OPERATE_TIME bigint,
        OPERATOR varchar(50),
        REMOVED integer,
        CREATE_TIME date,
        DESCRIPTION varchar(255),
        NAME varchar(255) not null,
        CHILD_NODE_ID bigint,
        ORGAN_REALTION_TYPE_ID bigint,
        PARENT_NODE_ID bigint,
        primary key (ID)
    )in CFSPACE;

    create table CS_ORGAN_RELATION_TYPE (
        ID bigint not null,
        CODE varchar(100),
        DESCRIPTION varchar(255),
        NAME varchar(255) not null,
        OPERATE_TIME bigint,
        OPERATOR varchar(50),
        REMOVED integer,
        primary key (ID)
    )in CFSPACE;

    create table CS_ORGAN_ROLE (
        ORGAN_MODEL_ID bigint not null,
        SECURITY_ROLE_ID bigint not null,
        primary key (ORGAN_MODEL_ID, SECURITY_ROLE_ID)
    )in CFSPACE;

    create table CS_ORGAN_RULE (
        ID bigint not null,
        CODE varchar(100),
        DESCRIPTION varchar(255),
        NAME varchar(255) not null,
        OPERATE_TIME bigint,
        OPERATOR varchar(50),
        REMOVED integer,
        ORGAN_TREE_TYPE_ID bigint,
        SUBORDINATE_NODE_ID bigint,
        SUPERIOR_NODE_ID bigint,
        primary key (ID)
    )in CFSPACE;

    create table CS_ORGAN_TREE (
        ID bigint not null,
        CODE varchar(100),
        DESCRIPTION varchar(255),
        NAME varchar(255) not null,
        OPERATE_TIME bigint,
        OPERATOR varchar(50),
        REMOVED integer,
        CREATE_TIME date,
        ROOT_NODE_ID bigint,
        ORGAN_TREE_TYPE_ID bigint,
        primary key (ID)
    )in CFSPACE;

    create table CS_ORGAN_TREE_TYPE (
        ID bigint not null,
        CODE varchar(100),
        DESCRIPTION varchar(255),
        NAME varchar(255) not null,
        OPERATE_TIME bigint,
        OPERATOR varchar(50),
        REMOVED integer,
        primary key (ID)
    )in CFSPACE;

    create table CS_ORGAN_TYPE (
        ID bigint not null,
        CODE varchar(100),
        DESCRIPTION varchar(255),
        NAME varchar(255) not null,
        OPERATE_TIME bigint,
        OPERATOR varchar(50),
        REMOVED integer,
        DOWN integer,
        IMAGE varchar(50),
        PEOPLE integer,
        IS_TOP integer,
        primary key (ID)
    )in CFSPACE;

    create table CS_PROXY_USER (
        ID bigint not null,
        DESCRIPTION varchar(255),
        END_TIME date,
        START_TIME date,
        PROXY_ID bigint,
        USER_ID bigint,
        primary key (ID)
    )in CFSPACE;

    create table CS_PUB_PERMISSION (
        ACL_PERMISSION_ID bigint not null,
        SECURITY_ROLE_ID bigint not null,
        primary key (SECURITY_ROLE_ID, ACL_PERMISSION_ID)
    )in CFSPACE;

    create table CS_RESOURCE_GROUP (
        ID bigint not null,
        OPERATE_TIME bigint,
        OPERATOR varchar(50),
        REMOVED integer,
        CLASS_NAME varchar(255) not null,
        CODE varchar(100),
        DESCRIPTION varchar(255),
        PROP_VALUES varchar(255),
        NAME varchar(255),
        ACL_RES_TYPE_ID bigint,
        primary key (ID)
    )in CFSPACE;

    create table CS_REV_PERMISSION (
        ACL_PERMISSION_ID bigint not null,
        SECURITY_ROLE_ID bigint not null,
        primary key (SECURITY_ROLE_ID, ACL_PERMISSION_ID)
    )in CFSPACE;

    create table CS_ROLE (
        ID bigint not null,
        OPERATE_TIME bigint,
        OPERATOR varchar(50),
        REMOVED integer,
        DESCRIPTION varchar(255),
        NAME varchar(100) not null unique,
        TYPE varchar(1) not null,
        PARENT_ROLE_ID bigint,
        primary key (ID)
    )in CFSPACE;

    create table CS_USER (
        ID bigint not null,
        OPERATE_TIME bigint,
        OPERATOR varchar(50),
        REMOVED integer,
        ACCOUNTTYPE integer,
        AUTHENTIC_TYPE integer,
        CERTIFICATE varchar(255),
        CREATEDATE bigint,
        LOGIN_NAME varchar(100) not null,
        NAME varchar(100),
        PASSWORD varchar(100) not null,
        RESET_CODE varchar(255),
        SEX varchar(255),
        STATUS bigint,
        USERTYPE integer,
        ADDRESS varchar(255),
        EMAIL varchar(100),
        EXT1 varchar(255),
        EXT2 varchar(255),
        EXT3 varchar(255),
        EXT4 varchar(255),
        EXT5 varchar(255),
        EXT6 varchar(255),
        EXT7 varchar(255),
        EXT8 varchar(255),
        FAX varchar(50),
        HOME_PHONE varchar(50),
        MOBILE1 varchar(50),
        MOBILE2 varchar(50),
        OFFICE_PHONE varchar(50),
        primary key (ID)
    )in CFSPACE;

    create table CS_USER_APPNODE (
        APP_NODE_ID bigint not null,
        USER_ID bigint not null,
        primary key (USER_ID, APP_NODE_ID)
    )in CFSPACE;

    create table CS_USER_EXTEND_PROPERTY (
        ID bigint not null,
        EXT1 varchar(255),
        EXT10 varchar(255),
        EXT11 varchar(255),
        EXT12 varchar(255),
        EXT13 varchar(255),
        EXT14 varchar(255),
        EXT15 varchar(255),
        EXT16 varchar(255),
        EXT17 varchar(255),
        EXT18 varchar(255),
        EXT19 varchar(255),
        EXT2 varchar(255),
        EXT20 varchar(255),
        EXT21 varchar(255),
        EXT22 varchar(255),
        EXT23 varchar(255),
        EXT24 varchar(255),
        EXT25 varchar(255),
        EXT26 varchar(255),
        EXT27 varchar(255),
        EXT28 varchar(255),
        EXT29 varchar(255),
        EXT3 varchar(255),
        EXT30 varchar(255),
        EXT31 varchar(255),
        EXT32 varchar(255),
        EXT33 varchar(255),
        EXT34 varchar(255),
        EXT35 varchar(255),
        EXT36 varchar(255),
        EXT37 varchar(255),
        EXT38 varchar(255),
        EXT39 varchar(255),
        EXT4 varchar(255),
        EXT40 varchar(255),
        EXT5 varchar(255),
        EXT6 varchar(255),
        EXT7 varchar(255),
        EXT8 varchar(255),
        EXT9 varchar(255),
        SECURITY_USER_ID bigint,
        primary key (ID)
    )in CFSPACE;

    create table CS_USER_GROUP (
        SECURITY_GROUP_ID bigint not null,
        SECURITY_USER_ID bigint not null,
        primary key (SECURITY_USER_ID, SECURITY_GROUP_ID)
    )in CFSPACE;

    create table CS_USER_LOGIN (
        ID bigint not null,
        APP_ID bigint,
        APP_NAME varchar(255),
        AREA varchar(255),
        BROWSER varchar(255),
        ERROR_INFO varchar(255),
        IP_ADDRESS varchar(255),
        LOGIN_NAME varchar(255),
        LOGIN_TIME bigint,
        ONLINE_TIME bigint,
        OPERATING_SYSTEM varchar(255),
        USER_NAME varchar(255),
        USER_STATE integer,
        USER_ID bigint,
        primary key (ID)
    )in CFSPACE;

    create table CS_USER_ORGANNODE (
        ORGAN_NODE_ID bigint not null,
        SECURITY_USER_ID bigint not null,
        ORDERS bigint,
        primary key (SECURITY_USER_ID, ORGAN_NODE_ID)
    )in CFSPACE;

    create table CS_USER_ROLE (
        SECURITY_ROLE_ID bigint not null,
        SECURITY_USER_ID bigint not null,
        primary key (SECURITY_USER_ID, SECURITY_ROLE_ID)
    )in CFSPACE;

    create table CS_WEBAPP_NODE (
        ID bigint not null,
        OPERATE_TIME bigint,
        OPERATOR varchar(50),
        REMOVED integer,
        APP_SERVER varchar(255),
        CODE varchar(100),
        COMPATABLE smallint,
        IS_CUTE_SERIES smallint,
        DB_SERVER varchar(255),
        DESCRIPTION varchar(255),
        INDEX_URL varchar(255),
        LOGOUT_STYLE smallint,
        MAX_INACTIVE_SECS bigint,
        NAME varchar(255),
        NODE_TYPE bigint not null,
        ORGAN_ID bigint,
        ROOT_MENU_CODE varchar(255),
        VENDOR varchar(255),
        VERSION varchar(255),
        CENTRAL_NODE_ID bigint,
        primary key (ID)
    )in CFSPACE;

    create table CW_ACT_INSTANCE (
        ACT_INSTANCE_ID varchar(255) not null,
        PROPERTIES varchar(2000) for bit data,
        ACTIVITY_ID varchar(255),
        ACTIVITY_NAME varchar(255),
        ACTIVITY_STATE integer,
        ACTIVITY_TYPE integer,
        END_TIME date,
        INPUT_CONTAINERS varchar(255),
        OUTPUT_CONTAINERS varchar(255),
        START_TIME date,
        PROCESS_INSTANCE_ID varchar(255) not null,
        primary key (ACT_INSTANCE_ID)
    )in CFSPACE;

    create table CW_AUDIT_ACT_INST (
        id bigint not null,
        ACTION integer not null,
        CHANGEDTIME date not null,
        NEWSTATE integer,
        PERVIOUSSTATE integer,
        ACTIVITY_INSTANCE_ID varchar(255) not null,
        ACTIVITY_NAME varchar(255) not null,
        PROCESS_INSTANCE_ID varchar(255) not null,
        primary key (id)
    )in CFSPACE;

    create table CW_AUDIT_CONFIG (
        id bigint not null,
        ISAUDIT_ACTIVITY_INSTANCE smallint,
        ISAUDIT_GLOBAL smallint,
        ISAUDIT_PROCESS_DATA smallint,
        PROCESS_ID varchar(255),
        ISAUDIT_PROCESS_INSTANCE smallint,
        ISAUDIT_PROCESS_MODEL smallint,
        ISAUDIT_WORKITEM smallint,
        primary key (id)
    )in CFSPACE;

    create table CW_AUDIT_PDATA (
        id bigint not null,
        ACTION integer,
        ATTRIBUTE_NAME varchar(255),
        CHANGED_TIME date,
        CONTAINER_INSTANCE_ID varchar(255),
        NEW_DATA varchar(255),
        OLD_DATA varchar(255),
        PROCESS_DATA_ID bigint,
        PROCESS_INSTANCE_ID varchar(255),
        primary key (id)
    )in CFSPACE;

    create table CW_AUDIT_PINSTANCE (
        ID bigint not null,
        ACTION integer not null,
        CHANGEDTIME date not null,
        NEWSTATE integer,
        PERVIOUSSTATE integer,
        PROCESS_ID varchar(255) not null,
        PROCESS_INSTANCE_ID varchar(255) not null,
        PROCESS_NAME varchar(255),
        primary key (ID)
    )in CFSPACE;

    create table CW_AUDIT_PMODEL (
        id bigint not null,
        ACTION integer not null,
        CHANGEDTIME date not null,
        NEWSTATE integer,
        PERVIOUSSTATE integer,
        MODEL_ID varchar(255),
        PROCESS_ID varchar(255),
        PROCESS_NAME varchar(255),
        primary key (id)
    )in CFSPACE;

    create table CW_AUDIT_WORKITEM (
        id bigint not null,
        ACTION integer not null,
        CHANGEDTIME date not null,
        NEWSTATE integer,
        PERVIOUSSTATE integer,
        ACTIVITY_INSTANCE_ID varchar(255),
        ACTIVITY_NAME varchar(255),
        WORKITEM_ID bigint,
        primary key (id)
    )in CFSPACE;

    create table CW_CON_INSTANCE (
        CON_INSTANCE_ID varchar(255) not null,
        CONTAINER_ID varchar(255),
        CONTAINER_NAME varchar(255),
        PROCESS_INSTANCE_ID varchar(255),
        primary key (CON_INSTANCE_ID)
    )in CFSPACE;

    create table CW_PROCESS_DATA (
        ID bigint not null,
        ATTRIBUTE_NAME varchar(255),
        ATTRIBUTE_TYPE varchar(255),
        ATTRIBUTE_VALUE varchar(255),
        MODIFIED_TIME date,
        RECORD_INDEX integer,
        CON_INSTANCE_ID varchar(255),
        primary key (ID)
    )in CFSPACE;

    create table CW_PROCESS_INSTANCE (
        PROCESS_INSTANCE_ID varchar(255) not null,
        PROPERTIES varchar(2000) for bit data,
        BUSINESS_ENTITYID varchar(255),
        ENABLED_ACTIVITIES varchar(255),
        END_TIME date,
        EXTERNALE_KEY varchar(255),
        PARENT_INSTANCE_ID varchar(255),
        PROCESS_NAME varchar(255),
        PROCESS_STATE integer,
        START_TIME date,
        PROCESS_ID varchar(255) not null,
        primary key (PROCESS_INSTANCE_ID)
    )in CFSPACE;

    create table CW_PROCESS_MODEL (
        PROCESS_ID varchar(255) not null,
        BUSINESS_TYPE integer,
        DESCRIPTION varchar(255),
        MODEL clob,
        MODEL_ID varchar(255),
        NAME varchar(255),
        PUBLISH_STATE integer,
        VERSION integer,
        primary key (PROCESS_ID)
    )in CFSPACE;

    create table CW_TIME_ACTIVITY (
        id bigint not null,
        EXPECTED_TIME date,
        STARTED_TIME date,
        ACTIVITY_INSTANCE_ID varchar(255),
        primary key (id)
    )in CFSPACE;

    create table CW_TRANS_INSTANCE (
        TRANS_INSTANCE_ID varchar(255) not null,
        PROPERTIES varchar(2000) for bit data,
        MODIFIED_TIME date,
        TRANSITION_ID varchar(255),
        TRANSITION_NAME varchar(255),
        TRANSITION_STATE integer,
        TRANSITION_TYPE integer,
        FROM_AINSTANCE_ID varchar(255),
        PROCESS_INSTANCE_ID varchar(255),
        TO_AINSTANCE_ID varchar(255),
        primary key (TRANS_INSTANCE_ID)
    )in CFSPACE;

    create table CW_WORK_ITEMS (
        WORKITEM_ID bigint not null,
        ACTIVITY_ID varchar(255),
        ACTIVITY_NAME varchar(255),
        DEADLINE date,
        DESCRIPTION varchar(255),
        MODEL_ID varchar(255),
        OUTER_ID varchar(255),
        PROCESS_INSTANCE_ID varchar(255),
        WORKITEM_TYPE integer,
        WORK_STATE integer,
        ACT_INSTANCE_ID varchar(255),
        primary key (WORKITEM_ID)
    )in CFSPACE;

    create table CW_WORK_PERSON (
        ID bigint not null,
        DESCRIPTION varchar(255),
        EXECUTE_TIME date,
        WORK_INDEX integer,
        IS_SHOW integer,
        PERSON_ID varchar(255),
        WORKITEM_ID bigint,
        primary key (ID)
    )in CFSPACE;

    alter table CD_ATTRIBUTE_PRIVILEGE 
        add constraint FK96C96BB0A0A67EBD 
        foreign key (GROUP_ID) 
        references CS_GROUP;

    alter table CF_CODE_INFO 
        add constraint FKC5F13EE47FC4008 
        foreign key (CODE_INFO_ID) 
        references CF_CODE_INFO;

    alter table CF_CODE_INFO 
        add constraint FKC5F13EE460178CDA 
        foreign key (TYPE_ID) 
        references CF_CODE_TYPE;

    alter table CF_DEMO_COAUDIT 
        add constraint FK211662CFC47E5B93 
        foreign key (CO_AUDIT_ID) 
        references CF_DEMO_DRAFT;

    alter table CF_DEMO_DRAFT 
        add constraint FKD977E58193221148 
        foreign key (TYPE) 
        references CF_CODE_INFO;

    alter table CF_DEMO_DRAFT 
        add constraint FKD977E5814D73FBB2 
        foreign key (PRIORITY) 
        references CF_CODE_INFO;

    alter table CF_DEMO_DRAFT 
        add constraint FKD977E581F4A1F41B 
        foreign key (CONFIDENTIAL_LEVEL) 
        references CF_CODE_INFO;

    alter table CF_SESSION_RECORD 
        add constraint FKADB07196FD3D92 
        foreign key (SESSIONSET_ID) 
        references CF_SESSION_SET;

    alter table CP_CONSOLE_OPERS 
        add constraint FKCD47926B8DE72443 
        foreign key (PARENT_RESOURCE_ID) 
        references CP_CONSOLE_OPERS;

    alter table CP_INDIVIDUAL_VIEW 
        add constraint FKDD3E78B966D19017 
        foreign key (USER_ID) 
        references CS_USER;

    alter table CP_INDIVIDUAL_VIEW 
        add constraint FKDD3E78B9F2537C00 
        foreign key (PARENT_VIEW_ID) 
        references CP_INDIVIDUAL_VIEW;

    alter table CP_INDIVIDUAL_VIEW 
        add constraint FKDD3E78B9641A0A84 
        foreign key (MENU_ID) 
        references CP_MENU_RESOURCE;

    alter table CP_MENU_RESOURCE 
        add constraint FK591E8A9C73120231 
        foreign key (SMALL_IMAGE) 
        references CF_CODE_INFO;

    alter table CP_MENU_RESOURCE 
        add constraint FK591E8A9C931C77B6 
        foreign key (HOST) 
        references CF_CODE_INFO;

    alter table CP_MENU_RESOURCE 
        add constraint FK591E8A9CE1324B6A 
        foreign key (BIG_IMAGE) 
        references CF_CODE_INFO;

    alter table CP_MENU_RESOURCE 
        add constraint FK591E8A9CE757E160 
        foreign key (PARENT_RESOURCE_ID) 
        references CP_MENU_RESOURCE;

    alter table CP_MENU_RESOURCE 
        add constraint FK591E8A9C37EB3828 
        foreign key (MENU_TYPE) 
        references CF_CODE_INFO;

    alter table CP_URL_RESOURCE 
        add constraint FK811CDF9050CB6C7C 
        foreign key (PARENT_RESOURCE_ID) 
        references CP_URL_RESOURCE;

    alter table CR_PROXY_MENU 
        add constraint FKD3CE3680C5CA564C 
        foreign key (PROXY_ID) 
        references CS_PROXY_USER;

    alter table CR_PROXY_MENU 
        add constraint FKD3CE3680641A0A84 
        foreign key (MENU_ID) 
        references CP_MENU_RESOURCE;

    alter table CS_ACC_PERMISSION 
        add constraint FK9F33AC1CB57DAA38 
        foreign key (SECURITY_ROLE_ID) 
        references CS_ROLE;

    alter table CS_ACC_PERMISSION 
        add constraint FK9F33AC1CCD109952 
        foreign key (ACL_PERMISSION_ID) 
        references CS_ACL_PERMISSION;

    alter table CS_ACL_OPERATION 
        add constraint FK73BA3623B9E31D81 
        foreign key (ACL_RES_TYPE_ID) 
        references CS_ACL_RES_TYPE;

    alter table CS_ACL_PERMISSION 
        add constraint FKE4727C7375E75762 
        foreign key (ACL_OPERATION_ID) 
        references CS_ACL_OPERATION;

    alter table CS_ACL_PERMISSION 
        add constraint FKE4727C7346E79AF2 
        foreign key (ACL_RESOURCE_ID) 
        references CS_ACL_RESOURCE;

    alter table CS_ACL_RESOURCE 
        add constraint FKD36ED7B2B9E31D81 
        foreign key (ACL_RES_TYPE_ID) 
        references CS_ACL_RES_TYPE;

    alter table CS_ACL_RESOURCE 
        add constraint FKD36ED7B25DB4015F 
        foreign key (PARENT_ACL_RES_ID) 
        references CS_ACL_RESOURCE;

    alter table CS_ACL_RES_TYPE 
        add constraint FKD44FF73D97C65DBE 
        foreign key (WEBAPP_NODE_ID) 
        references CS_WEBAPP_NODE;

    alter table CS_ACTION_RESOURCE 
        add constraint FK31750E289D2D1261 
        foreign key (PARENT_RESOURCE_ID) 
        references CS_CUSTOM_RESOURCE;

    alter table CS_CUSTOM_RESOURCE 
        add constraint FK3899B34DB9E31D81 
        foreign key (ACL_RES_TYPE_ID) 
        references CS_ACL_RES_TYPE;

    alter table CS_CUSTOM_RESOURCE 
        add constraint FK3899B34D9D2D1261 
        foreign key (PARENT_RESOURCE_ID) 
        references CS_CUSTOM_RESOURCE;

    alter table CS_GROUP_ROLE 
        add constraint FK96DEFD8527AB60DC 
        foreign key (SECURITY_GROUP_ID) 
        references CS_GROUP;

    alter table CS_GROUP_ROLE 
        add constraint FK96DEFD85B57DAA38 
        foreign key (SECURITY_ROLE_ID) 
        references CS_ROLE;

    alter table CS_ORGAN_EXTEND_PROPERTY 
        add constraint FKABA06C7DC05442D 
        foreign key (ORGAN_NODE_ID) 
        references CS_ORGAN_NODE;

    alter table CS_ORGAN_MODEL 
        add constraint FKC25845EC640DE00 
        foreign key (ORG_NODE_ID) 
        references CS_ORGAN_NODE;

    alter table CS_ORGAN_MODEL 
        add constraint FKC25845EC896F11C6 
        foreign key (PARENT_NODE_ID) 
        references CS_ORGAN_NODE;

    alter table CS_ORGAN_MODEL 
        add constraint FKC25845EC48D5A880 
        foreign key (ORG_TREE_ID) 
        references CS_ORGAN_TREE;

    alter table CS_ORGAN_NODE 
        add constraint FKD4B8FABF42FCA82E 
        foreign key (ORGAN_NODE_TYPE_ID) 
        references CS_ORGAN_NODE_TYPE;

    alter table CS_ORGAN_NODE 
        add constraint FKD4B8FABF931CBE87 
        foreign key (ICON) 
        references CF_CODE_INFO;

    alter table CS_ORGAN_NODE 
        add constraint FKD4B8FABFA1527B27 
        foreign key (ADMIN_USER_ID) 
        references CS_USER;

    alter table CS_ORGAN_RELATION 
        add constraint FK224B98396280B758 
        foreign key (ORGAN_REALTION_TYPE_ID) 
        references CS_ORGAN_RELATION_TYPE;

    alter table CS_ORGAN_RELATION 
        add constraint FK224B98396F356C38 
        foreign key (CHILD_NODE_ID) 
        references CS_ORGAN_NODE;

    alter table CS_ORGAN_RELATION 
        add constraint FK224B9839896F11C6 
        foreign key (PARENT_NODE_ID) 
        references CS_ORGAN_NODE;

    alter table CS_ORGAN_ROLE 
        add constraint FKD4BACD33B57DAA38 
        foreign key (SECURITY_ROLE_ID) 
        references CS_ROLE;

    alter table CS_ORGAN_ROLE 
        add constraint FKD4BACD33CB9C1C7 
        foreign key (ORGAN_MODEL_ID) 
        references CS_ORGAN_MODEL;

    alter table CS_ORGAN_RULE 
        add constraint FKD4BAE3B933CCA966 
        foreign key (ORGAN_TREE_TYPE_ID) 
        references CS_ORGAN_TREE_TYPE;

    alter table CS_ORGAN_RULE 
        add constraint FKD4BAE3B98ADFB442 
        foreign key (SUBORDINATE_NODE_ID) 
        references CS_ORGAN_NODE_TYPE;

    alter table CS_ORGAN_RULE 
        add constraint FKD4BAE3B967CC1EA7 
        foreign key (SUPERIOR_NODE_ID) 
        references CS_ORGAN_NODE_TYPE;

    alter table CS_ORGAN_TREE 
        add constraint FKD4BBC05B1DF9D1FE 
        foreign key (ROOT_NODE_ID) 
        references CS_ORGAN_NODE;

    alter table CS_ORGAN_TREE 
        add constraint FKD4BBC05B33CCA966 
        foreign key (ORGAN_TREE_TYPE_ID) 
        references CS_ORGAN_TREE_TYPE;

    alter table CS_PROXY_USER 
        add constraint FKDB83B24B66D19017 
        foreign key (USER_ID) 
        references CS_USER;

    alter table CS_PROXY_USER 
        add constraint FKDB83B24B34DE0A94 
        foreign key (PROXY_ID) 
        references CS_USER;

    alter table CS_PUB_PERMISSION 
        add constraint FK98B74C20B57DAA38 
        foreign key (SECURITY_ROLE_ID) 
        references CS_ROLE;

    alter table CS_PUB_PERMISSION 
        add constraint FK98B74C20CD109952 
        foreign key (ACL_PERMISSION_ID) 
        references CS_ACL_PERMISSION;

    alter table CS_RESOURCE_GROUP 
        add constraint FK5D29CA9DB9E31D81 
        foreign key (ACL_RES_TYPE_ID) 
        references CS_ACL_RES_TYPE;

    alter table CS_REV_PERMISSION 
        add constraint FKE2362BAB57DAA38 
        foreign key (SECURITY_ROLE_ID) 
        references CS_ROLE;

    alter table CS_REV_PERMISSION 
        add constraint FKE2362BACD109952 
        foreign key (ACL_PERMISSION_ID) 
        references CS_ACL_PERMISSION;

    alter table CS_ROLE 
        add constraint FK6B44EEE5D0CB31A2 
        foreign key (PARENT_ROLE_ID) 
        references CS_ROLE;

    alter table CS_USER_APPNODE 
        add constraint FKF3E1E27E66D19017 
        foreign key (USER_ID) 
        references CS_USER;

    alter table CS_USER_APPNODE 
        add constraint FKF3E1E27E8DFAC592 
        foreign key (APP_NODE_ID) 
        references CS_WEBAPP_NODE;

    alter table CS_USER_EXTEND_PROPERTY 
        add constraint FK3F3A25555AA86E18 
        foreign key (SECURITY_USER_ID) 
        references CS_USER;

    alter table CS_USER_GROUP 
        add constraint FKF1EFA81A27AB60DC 
        foreign key (SECURITY_GROUP_ID) 
        references CS_GROUP;

    alter table CS_USER_GROUP 
        add constraint FKF1EFA81A5AA86E18 
        foreign key (SECURITY_USER_ID) 
        references CS_USER;

    alter table CS_USER_LOGIN 
        add constraint FKF234A10466D19017 
        foreign key (USER_ID) 
        references CS_USER;

    alter table CS_USER_ORGANNODE 
        add constraint FK7C2333AEC05442D 
        foreign key (ORGAN_NODE_ID) 
        references CS_ORGAN_NODE;

    alter table CS_USER_ORGANNODE 
        add constraint FK7C2333AE5AA86E18 
        foreign key (SECURITY_USER_ID) 
        references CS_USER;

    alter table CS_USER_ROLE 
        add constraint FKACFC2B5B5AA86E18 
        foreign key (SECURITY_USER_ID) 
        references CS_USER;

    alter table CS_USER_ROLE 
        add constraint FKACFC2B5BB57DAA38 
        foreign key (SECURITY_ROLE_ID) 
        references CS_ROLE;

    alter table CS_WEBAPP_NODE 
        add constraint FK6D9E1365A4F1A4E6 
        foreign key (CENTRAL_NODE_ID) 
        references CS_WEBAPP_NODE;

    alter table CW_ACT_INSTANCE 
        add constraint FK329C57AD9C0FADB4 
        foreign key (PROCESS_INSTANCE_ID) 
        references CW_PROCESS_INSTANCE;

    alter table CW_CON_INSTANCE 
        add constraint FK82FF5CBD9C0FADB4 
        foreign key (PROCESS_INSTANCE_ID) 
        references CW_PROCESS_INSTANCE;

    alter table CW_PROCESS_DATA 
        add constraint FKCC16CD855A6CDD99 
        foreign key (CON_INSTANCE_ID) 
        references CW_CON_INSTANCE;

    alter table CW_PROCESS_INSTANCE 
        add constraint FKD7C704F08B49EE52 
        foreign key (PROCESS_ID) 
        references CW_PROCESS_MODEL;

    alter table CW_TIME_ACTIVITY 
        add constraint FK4A9E9FB6964759E 
        foreign key (ACTIVITY_INSTANCE_ID) 
        references CW_ACT_INSTANCE;

    alter table CW_TRANS_INSTANCE 
        add constraint FK7AED0397F5F78242 
        foreign key (FROM_AINSTANCE_ID) 
        references CW_ACT_INSTANCE;

    alter table CW_TRANS_INSTANCE 
        add constraint FK7AED0397E458E711 
        foreign key (TO_AINSTANCE_ID) 
        references CW_ACT_INSTANCE;

    alter table CW_TRANS_INSTANCE 
        add constraint FK7AED03979C0FADB4 
        foreign key (PROCESS_INSTANCE_ID) 
        references CW_PROCESS_INSTANCE;

    alter table CW_WORK_ITEMS 
        add constraint FK7C77FD9D2406CEA1 
        foreign key (ACT_INSTANCE_ID) 
        references CW_ACT_INSTANCE;

    alter table CW_WORK_PERSON 
        add constraint FK1DAC40F85DFA1312 
        foreign key (WORKITEM_ID) 
        references CW_WORK_ITEMS;
