
    alter table CD_ATTRIBUTE_PRIVILEGE 
        drop constraint FK96C96BB0A0A67EBD;

    alter table CF_CODE_INFO 
        drop constraint FKC5F13EE47FC4008;

    alter table CF_CODE_INFO 
        drop constraint FKC5F13EE460178CDA;

    alter table CF_DEMO_COAUDIT 
        drop constraint FK211662CFC47E5B93;

    alter table CF_DEMO_DRAFT 
        drop constraint FKD977E58193221148;

    alter table CF_DEMO_DRAFT 
        drop constraint FKD977E5814D73FBB2;

    alter table CF_DEMO_DRAFT 
        drop constraint FKD977E581F4A1F41B;

    alter table CF_SESSION_RECORD 
        drop constraint FKADB07196FD3D92;

    alter table CP_CONSOLE_OPERS 
        drop constraint FKCD47926B8DE72443;

    alter table CP_INDIVIDUAL_VIEW 
        drop constraint FKDD3E78B966D19017;

    alter table CP_INDIVIDUAL_VIEW 
        drop constraint FKDD3E78B9F2537C00;

    alter table CP_INDIVIDUAL_VIEW 
        drop constraint FKDD3E78B9641A0A84;

    alter table CP_MENU_RESOURCE 
        drop constraint FK591E8A9C73120231;

    alter table CP_MENU_RESOURCE 
        drop constraint FK591E8A9C931C77B6;

    alter table CP_MENU_RESOURCE 
        drop constraint FK591E8A9CE1324B6A;

    alter table CP_MENU_RESOURCE 
        drop constraint FK591E8A9CE757E160;

    alter table CP_MENU_RESOURCE 
        drop constraint FK591E8A9C37EB3828;

    alter table CP_URL_RESOURCE 
        drop constraint FK811CDF9050CB6C7C;

    alter table CR_PROXY_MENU 
        drop constraint FKD3CE3680C5CA564C;

    alter table CR_PROXY_MENU 
        drop constraint FKD3CE3680641A0A84;

    alter table CS_ACC_PERMISSION 
        drop constraint FK9F33AC1CB57DAA38;

    alter table CS_ACC_PERMISSION 
        drop constraint FK9F33AC1CCD109952;

    alter table CS_ACL_OPERATION 
        drop constraint FK73BA3623B9E31D81;

    alter table CS_ACL_PERMISSION 
        drop constraint FKE4727C7375E75762;

    alter table CS_ACL_PERMISSION 
        drop constraint FKE4727C7346E79AF2;

    alter table CS_ACL_RESOURCE 
        drop constraint FKD36ED7B2B9E31D81;

    alter table CS_ACL_RESOURCE 
        drop constraint FKD36ED7B25DB4015F;

    alter table CS_ACL_RES_TYPE 
        drop constraint FKD44FF73D97C65DBE;

    alter table CS_ACTION_RESOURCE 
        drop constraint FK31750E289D2D1261;

    alter table CS_CUSTOM_RESOURCE 
        drop constraint FK3899B34DB9E31D81;

    alter table CS_CUSTOM_RESOURCE 
        drop constraint FK3899B34D9D2D1261;

    alter table CS_GROUP_ROLE 
        drop constraint FK96DEFD8527AB60DC;

    alter table CS_GROUP_ROLE 
        drop constraint FK96DEFD85B57DAA38;

    alter table CS_ORGAN_EXTEND_PROPERTY 
        drop constraint FKABA06C7DC05442D;

    alter table CS_ORGAN_MODEL 
        drop constraint FKC25845EC640DE00;

    alter table CS_ORGAN_MODEL 
        drop constraint FKC25845EC896F11C6;

    alter table CS_ORGAN_MODEL 
        drop constraint FKC25845EC48D5A880;

    alter table CS_ORGAN_NODE 
        drop constraint FKD4B8FABF42FCA82E;

    alter table CS_ORGAN_NODE 
        drop constraint FKD4B8FABF931CBE87;

    alter table CS_ORGAN_NODE 
        drop constraint FKD4B8FABFA1527B27;

    alter table CS_ORGAN_RELATION 
        drop constraint FK224B98396280B758;

    alter table CS_ORGAN_RELATION 
        drop constraint FK224B98396F356C38;

    alter table CS_ORGAN_RELATION 
        drop constraint FK224B9839896F11C6;

    alter table CS_ORGAN_ROLE 
        drop constraint FKD4BACD33B57DAA38;

    alter table CS_ORGAN_ROLE 
        drop constraint FKD4BACD33CB9C1C7;

    alter table CS_ORGAN_RULE 
        drop constraint FKD4BAE3B933CCA966;

    alter table CS_ORGAN_RULE 
        drop constraint FKD4BAE3B98ADFB442;

    alter table CS_ORGAN_RULE 
        drop constraint FKD4BAE3B967CC1EA7;

    alter table CS_ORGAN_TREE 
        drop constraint FKD4BBC05B1DF9D1FE;

    alter table CS_ORGAN_TREE 
        drop constraint FKD4BBC05B33CCA966;

    alter table CS_PROXY_USER 
        drop constraint FKDB83B24B66D19017;

    alter table CS_PROXY_USER 
        drop constraint FKDB83B24B34DE0A94;

    alter table CS_PUB_PERMISSION 
        drop constraint FK98B74C20B57DAA38;

    alter table CS_PUB_PERMISSION 
        drop constraint FK98B74C20CD109952;

    alter table CS_RESOURCE_GROUP 
        drop constraint FK5D29CA9DB9E31D81;

    alter table CS_REV_PERMISSION 
        drop constraint FKE2362BAB57DAA38;

    alter table CS_REV_PERMISSION 
        drop constraint FKE2362BACD109952;

    alter table CS_ROLE 
        drop constraint FK6B44EEE5D0CB31A2;

    alter table CS_USER_APPNODE 
        drop constraint FKF3E1E27E66D19017;

    alter table CS_USER_APPNODE 
        drop constraint FKF3E1E27E8DFAC592;

    alter table CS_USER_EXTEND_PROPERTY 
        drop constraint FK3F3A25555AA86E18;

    alter table CS_USER_GROUP 
        drop constraint FKF1EFA81A27AB60DC;

    alter table CS_USER_GROUP 
        drop constraint FKF1EFA81A5AA86E18;

    alter table CS_USER_LOGIN 
        drop constraint FKF234A10466D19017;

    alter table CS_USER_ORGANNODE 
        drop constraint FK7C2333AEC05442D;

    alter table CS_USER_ORGANNODE 
        drop constraint FK7C2333AE5AA86E18;

    alter table CS_USER_ROLE 
        drop constraint FKACFC2B5B5AA86E18;

    alter table CS_USER_ROLE 
        drop constraint FKACFC2B5BB57DAA38;

    alter table CS_WEBAPP_NODE 
        drop constraint FK6D9E1365A4F1A4E6;

    alter table CW_ACT_INSTANCE 
        drop constraint FK329C57AD9C0FADB4;

    alter table CW_CON_INSTANCE 
        drop constraint FK82FF5CBD9C0FADB4;

    alter table CW_PROCESS_DATA 
        drop constraint FKCC16CD855A6CDD99;

    alter table CW_PROCESS_INSTANCE 
        drop constraint FKD7C704F08B49EE52;

    alter table CW_TIME_ACTIVITY 
        drop constraint FK4A9E9FB6964759E;

    alter table CW_TRANS_INSTANCE 
        drop constraint FK7AED0397F5F78242;

    alter table CW_TRANS_INSTANCE 
        drop constraint FK7AED0397E458E711;

    alter table CW_TRANS_INSTANCE 
        drop constraint FK7AED03979C0FADB4;

    alter table CW_WORK_ITEMS 
        drop constraint FK7C77FD9D2406CEA1;

    alter table CW_WORK_PERSON 
        drop constraint FK1DAC40F85DFA1312;

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
        ID numeric(19,0) identity not null,
        ATTRIBUTE_NAME varchar(255) null,
        PRIVILEGE int null,
        GROUP_ID numeric(19,0) null,
        primary key (ID)
    );

    create table CF_CODE_INFO (
        ID numeric(19,0) identity not null,
        OPERATE_TIME numeric(19,0) null,
        OPERATOR varchar(50) null,
        REMOVED int null,
        CODE varchar(100) null,
        DESCRIPTION varchar(255) null,
        NAME varchar(255) not null,
        DISP_ORDER int null,
        REMARK1 varchar(255) null,
        REMARK2 varchar(255) null,
        TYPE_ID numeric(19,0) null,
        CODE_INFO_ID numeric(19,0) null,
        primary key (ID)
    );

    create table CF_CODE_TYPE (
        ID numeric(19,0) identity not null,
        OPERATE_TIME numeric(19,0) null,
        OPERATOR varchar(50) null,
        REMOVED int null,
        CODE varchar(100) null,
        DESCRIPTION varchar(255) null,
        DISP_ORDER int null,
        EXTERNAL_MAPPING varchar(255) null,
        LEVE int null,
        NAME varchar(255) not null,
        STORAGE_TYPE int null,
        SYSTEM_TYPE int null,
        primary key (ID)
    );

    create table CF_DEMO_COAUDIT (
        ID numeric(19,0) identity not null,
        CONTENT varchar(255) null,
        CREATE_DATE datetime null,
        CREATOR varchar(255) null,
        CO_AUDIT_ID numeric(19,0) null,
        primary key (ID)
    );

    create table CF_DEMO_DRAFT (
        ID numeric(19,0) identity not null,
        AFFILIATED_COMMENT varchar(255) null,
        AFFILIATED_DATE datetime null,
        ARCHIVE_COMMENT varchar(255) null,
        ARCHIVE_DATE datetime null,
        AUDITOR varchar(255) null,
        AUDITOR_DATE datetime null,
        CONTENT varchar(255) null,
        CREATE_DATE datetime null,
        CREATOR varchar(255) null,
        FILE_CODE varchar(255) null,
        HEADER_COMMENT varchar(255) null,
        HEADER_DATE datetime null,
        OFFICIAL_COMMENT varchar(255) null,
        OFFICIAL_DATE datetime null,
        PRINT_COMMENT varchar(255) null,
        PRINT_DATE datetime null,
        TITLE varchar(255) null,
        VERIFIED_COMMENT varchar(255) null,
        VERIFIED_DATE datetime null,
        PROCESS_INSTANCE varchar(255) null,
        CONFIDENTIAL_LEVEL numeric(19,0) null,
        PRIORITY numeric(19,0) null,
        TYPE numeric(19,0) null,
        primary key (ID)
    );

    create table CF_EXTEND_PROPERTY_CONFIG (
        ID numeric(19,0) identity not null,
        CLASS_NAME varchar(255) null,
        EXT1 varchar(255) null,
        EXT10 varchar(255) null,
        EXT11 varchar(255) null,
        EXT12 varchar(255) null,
        EXT13 varchar(255) null,
        EXT14 varchar(255) null,
        EXT15 varchar(255) null,
        EXT16 varchar(255) null,
        EXT17 varchar(255) null,
        EXT18 varchar(255) null,
        EXT19 varchar(255) null,
        EXT2 varchar(255) null,
        EXT20 varchar(255) null,
        EXT21 varchar(255) null,
        EXT22 varchar(255) null,
        EXT23 varchar(255) null,
        EXT24 varchar(255) null,
        EXT25 varchar(255) null,
        EXT26 varchar(255) null,
        EXT27 varchar(255) null,
        EXT28 varchar(255) null,
        EXT29 varchar(255) null,
        EXT3 varchar(255) null,
        EXT30 varchar(255) null,
        EXT31 varchar(255) null,
        EXT32 varchar(255) null,
        EXT33 varchar(255) null,
        EXT34 varchar(255) null,
        EXT35 varchar(255) null,
        EXT36 varchar(255) null,
        EXT37 varchar(255) null,
        EXT38 varchar(255) null,
        EXT39 varchar(255) null,
        EXT4 varchar(255) null,
        EXT40 varchar(255) null,
        EXT5 varchar(255) null,
        EXT6 varchar(255) null,
        EXT7 varchar(255) null,
        EXT8 varchar(255) null,
        EXT9 varchar(255) null,
        primary key (ID)
    );

    create table CF_LOG (
        ID numeric(19,0) identity not null,
        ERROR_MESSAGE text null,
        FULLCLASSNAME varchar(200) null,
        LOGINFO text null,
        LOGINDATE datetime null,
        OPERDATE datetime null,
        USERID varchar(20) null,
        userName varchar(255) null,
        primary key (ID)
    );

    create table CF_SESSION_RECORD (
        ID numeric(19,0) identity not null,
        SESSION_KEY varchar(20) not null,
        SESSION_VALUE image null,
        SESSIONSET_ID numeric(19,0) null,
        primary key (ID)
    );

    create table CF_SESSION_SET (
        ID numeric(19,0) identity not null,
        CLIENT_ID varchar(100) null,
        SESSION_ID varchar(100) not null,
        primary key (ID)
    );

    create table CP_CONSOLE_OPERS (
        ID numeric(19,0) identity not null,
        CODE varchar(100) null unique,
        DESCRIPTION varchar(255) null,
        RESOURCE_NAME varchar(255) not null,
        PARENT_RESOURCE_ID numeric(19,0) null,
        primary key (ID)
    );

    create table CP_INDIVIDUAL_VIEW (
        ID numeric(19,0) identity not null,
        DESCRIPTION varchar(255) null,
        DISP_ORDER int null,
        IMAGE varchar(255) null,
        NAME varchar(255) not null,
        MENU_ID numeric(19,0) null,
        PARENT_VIEW_ID numeric(19,0) null,
        USER_ID numeric(19,0) null,
        primary key (ID)
    );

    create table CP_MENU_RESOURCE (
        ID numeric(19,0) identity not null,
        BACKUP_IMAGE varchar(100) null,
        BACKUP_IMAGE2 varchar(100) null,
        CODE varchar(255) null,
        DESCRIPTION varchar(255) null,
        DISP_ORDER int null,
        LINKPATH varchar(255) null,
        RESOURCE_NAME varchar(255) not null,
        VALID tinyint null,
        CLS varchar(255) null,
        EXT1 varchar(255) null,
        EXT2 varchar(255) null,
        EXT3 varchar(255) null,
        EXT4 varchar(255) null,
        EXT5 varchar(255) null,
        EXT6 varchar(255) null,
        EXT7 varchar(255) null,
        EXT8 varchar(255) null,
        HANDLER varchar(255) null,
        ICON varchar(255) null,
        TARGET varchar(255) null,
        BIG_IMAGE numeric(19,0) null,
        PARENT_RESOURCE_ID numeric(19,0) null,
        SMALL_IMAGE numeric(19,0) null,
        HOST numeric(19,0) null,
        MENU_TYPE numeric(19,0) null,
        primary key (ID)
    );

    create table CP_URL_RESOURCE (
        ID numeric(19,0) identity not null,
        CODE varchar(200) null,
        DESCRIPTION varchar(200) null,
        DISP_ORDER int null,
        LINKPATH varchar(100) null,
        PARAMETERS varchar(100) null,
        RESOURCE_NAME varchar(255) not null,
        PARENT_RESOURCE_ID numeric(19,0) null,
        primary key (ID)
    );

    create table CR_PROXY_MENU (
        PROXY_ID numeric(19,0) not null,
        MENU_ID numeric(19,0) not null,
        primary key (PROXY_ID, MENU_ID)
    );

    create table CS_ACC_PERMISSION (
        ACL_PERMISSION_ID numeric(19,0) not null,
        SECURITY_ROLE_ID numeric(19,0) not null,
        primary key (SECURITY_ROLE_ID, ACL_PERMISSION_ID)
    );

    create table CS_ACL_OPERATION (
        ID numeric(19,0) identity not null,
        OPERATE_TIME numeric(19,0) null,
        OPERATOR varchar(50) null,
        REMOVED int null,
        CODE varchar(100) null,
        DESCRIPTION varchar(255) null,
        NAME varchar(255) not null,
        ACL_RES_TYPE_ID numeric(19,0) null,
        primary key (ID)
    );

    create table CS_ACL_PERMISSION (
        ID numeric(19,0) identity not null,
        DUE_TIME datetime null,
        START_TIME datetime null,
        ACL_OPERATION_ID numeric(19,0) null,
        ACL_RESOURCE_ID numeric(19,0) null,
        primary key (ID)
    );

    create table CS_ACL_RESOURCE (
        ID numeric(19,0) identity not null,
        DESCRIPTION varchar(255) null,
        NAME varchar(255) null,
        NATIVE_RESOURCE_ID varchar(255) not null,
        ACL_RES_TYPE_ID numeric(19,0) null,
        PARENT_ACL_RES_ID numeric(19,0) null,
        primary key (ID)
    );

    create table CS_ACL_RES_TYPE (
        ID numeric(19,0) identity not null,
        OPERATE_TIME numeric(19,0) null,
        OPERATOR varchar(50) null,
        REMOVED int null,
        CATALOG int null,
        CLASS_NAME varchar(100) null,
        CODE varchar(100) null,
        DESCRIPTION varchar(255) null,
        INHERITTYPE int null,
        NAME varchar(100) not null,
        WEBAPP_NODE_ID numeric(19,0) null,
        primary key (ID)
    );

    create table CS_ACTION_RESOURCE (
        ID numeric(19,0) identity not null,
        CLASS_NAME varchar(255) not null,
        DESCRIPTION varchar(255) null,
        METHOD_NAME varchar(100) not null,
        RESOURCE_NAME varchar(255) not null,
        PARENT_RESOURCE_ID numeric(19,0) null,
        primary key (ID)
    );

    create table CS_CUSTOM_RESOURCE (
        ID numeric(19,0) identity not null,
        OPERATE_TIME numeric(19,0) null,
        OPERATOR varchar(50) null,
        REMOVED int null,
        CODE varchar(100) null,
        DESCRIPTION varchar(255) null,
        NAME varchar(255) null,
        PARENT_RESOURCE_ID numeric(19,0) null,
        ACL_RES_TYPE_ID numeric(19,0) null,
        primary key (ID)
    );

    create table CS_GROUP (
        ID numeric(19,0) identity not null,
        CODE varchar(100) null,
        DESCRIPTION varchar(255) null,
        NAME varchar(255) not null,
        OPERATE_TIME numeric(19,0) null,
        OPERATOR varchar(50) null,
        REMOVED int null,
        DYNA int null,
        DYNAACCESSCLASS varchar(255) null,
        DYANPARAMS varchar(255) null,
        primary key (ID)
    );

    create table CS_GROUP_ROLE (
        SECURITY_ROLE_ID numeric(19,0) not null,
        SECURITY_GROUP_ID numeric(19,0) not null,
        primary key (SECURITY_GROUP_ID, SECURITY_ROLE_ID)
    );

    create table CS_ORGAN_EXTEND_PROPERTY (
        ID numeric(19,0) identity not null,
        EXT1 varchar(255) null,
        EXT10 varchar(255) null,
        EXT11 varchar(255) null,
        EXT12 varchar(255) null,
        EXT13 varchar(255) null,
        EXT14 varchar(255) null,
        EXT15 varchar(255) null,
        EXT16 varchar(255) null,
        EXT17 varchar(255) null,
        EXT18 varchar(255) null,
        EXT19 varchar(255) null,
        EXT2 varchar(255) null,
        EXT20 varchar(255) null,
        EXT21 varchar(255) null,
        EXT22 varchar(255) null,
        EXT23 varchar(255) null,
        EXT24 varchar(255) null,
        EXT25 varchar(255) null,
        EXT26 varchar(255) null,
        EXT27 varchar(255) null,
        EXT28 varchar(255) null,
        EXT29 varchar(255) null,
        EXT3 varchar(255) null,
        EXT30 varchar(255) null,
        EXT31 varchar(255) null,
        EXT32 varchar(255) null,
        EXT33 varchar(255) null,
        EXT34 varchar(255) null,
        EXT35 varchar(255) null,
        EXT36 varchar(255) null,
        EXT37 varchar(255) null,
        EXT38 varchar(255) null,
        EXT39 varchar(255) null,
        EXT4 varchar(255) null,
        EXT40 varchar(255) null,
        EXT5 varchar(255) null,
        EXT6 varchar(255) null,
        EXT7 varchar(255) null,
        EXT8 varchar(255) null,
        EXT9 varchar(255) null,
        ORGAN_NODE_ID numeric(19,0) null,
        primary key (ID)
    );

    create table CS_ORGAN_MODEL (
        ID numeric(19,0) identity not null,
        OPERATE_TIME numeric(19,0) null,
        OPERATOR varchar(50) null,
        REMOVED int null,
        ORDERS numeric(19,0) null,
        RANK varchar(255) null,
        LFT int null,
        NODESTATUS varchar(255) null,
        RGT int null,
        ORG_NODE_ID numeric(19,0) null,
        ORG_TREE_ID numeric(19,0) null,
        PARENT_NODE_ID numeric(19,0) null,
        primary key (ID)
    );

    create table CS_ORGAN_NODE (
        ID numeric(19,0) identity not null,
        CODE varchar(100) null,
        DESCRIPTION varchar(255) null,
        NAME varchar(255) not null,
        OPERATE_TIME numeric(19,0) null,
        OPERATOR varchar(50) null,
        REMOVED int null,
        CREATE_TIME datetime null,
        DEPT_ADDRESS varchar(255) null,
        FAX varchar(255) null,
        PRINCIPAL varchar(255) null,
        PRINCIPAL_PHONE varchar(255) null,
        ADMIN_USER_ID numeric(19,0) null,
        ICON numeric(19,0) null,
        ORGAN_NODE_TYPE_ID numeric(19,0) null,
        primary key (ID)
    );

    create table CS_ORGAN_NODE_TYPE (
        ID numeric(19,0) identity not null,
        CODE varchar(100) null,
        DESCRIPTION varchar(255) null,
        NAME varchar(255) not null,
        OPERATE_TIME numeric(19,0) null,
        OPERATOR varchar(50) null,
        REMOVED int null,
        DOWN int null,
        IMAGE varchar(50) null,
        PEOPLE int null,
        IS_TOP int null,
        primary key (ID)
    );

    create table CS_ORGAN_RELATION (
        ID numeric(19,0) identity not null,
        OPERATE_TIME numeric(19,0) null,
        OPERATOR varchar(50) null,
        REMOVED int null,
        CREATE_TIME datetime null,
        DESCRIPTION varchar(255) null,
        NAME varchar(255) not null,
        CHILD_NODE_ID numeric(19,0) null,
        ORGAN_REALTION_TYPE_ID numeric(19,0) null,
        PARENT_NODE_ID numeric(19,0) null,
        primary key (ID)
    );

    create table CS_ORGAN_RELATION_TYPE (
        ID numeric(19,0) identity not null,
        CODE varchar(100) null,
        DESCRIPTION varchar(255) null,
        NAME varchar(255) not null,
        OPERATE_TIME numeric(19,0) null,
        OPERATOR varchar(50) null,
        REMOVED int null,
        primary key (ID)
    );

    create table CS_ORGAN_ROLE (
        ORGAN_MODEL_ID numeric(19,0) not null,
        SECURITY_ROLE_ID numeric(19,0) not null,
        primary key (ORGAN_MODEL_ID, SECURITY_ROLE_ID)
    );

    create table CS_ORGAN_RULE (
        ID numeric(19,0) identity not null,
        CODE varchar(100) null,
        DESCRIPTION varchar(255) null,
        NAME varchar(255) not null,
        OPERATE_TIME numeric(19,0) null,
        OPERATOR varchar(50) null,
        REMOVED int null,
        ORGAN_TREE_TYPE_ID numeric(19,0) null,
        SUBORDINATE_NODE_ID numeric(19,0) null,
        SUPERIOR_NODE_ID numeric(19,0) null,
        primary key (ID)
    );

    create table CS_ORGAN_TREE (
        ID numeric(19,0) identity not null,
        CODE varchar(100) null,
        DESCRIPTION varchar(255) null,
        NAME varchar(255) not null,
        OPERATE_TIME numeric(19,0) null,
        OPERATOR varchar(50) null,
        REMOVED int null,
        CREATE_TIME datetime null,
        ROOT_NODE_ID numeric(19,0) null,
        ORGAN_TREE_TYPE_ID numeric(19,0) null,
        primary key (ID)
    );

    create table CS_ORGAN_TREE_TYPE (
        ID numeric(19,0) identity not null,
        CODE varchar(100) null,
        DESCRIPTION varchar(255) null,
        NAME varchar(255) not null,
        OPERATE_TIME numeric(19,0) null,
        OPERATOR varchar(50) null,
        REMOVED int null,
        primary key (ID)
    );

    create table CS_ORGAN_TYPE (
        ID numeric(19,0) identity not null,
        CODE varchar(100) null,
        DESCRIPTION varchar(255) null,
        NAME varchar(255) not null,
        OPERATE_TIME numeric(19,0) null,
        OPERATOR varchar(50) null,
        REMOVED int null,
        DOWN int null,
        IMAGE varchar(50) null,
        PEOPLE int null,
        IS_TOP int null,
        primary key (ID)
    );

    create table CS_PROXY_USER (
        ID numeric(19,0) identity not null,
        DESCRIPTION varchar(255) null,
        END_TIME datetime null,
        START_TIME datetime null,
        PROXY_ID numeric(19,0) null,
        USER_ID numeric(19,0) null,
        primary key (ID)
    );

    create table CS_PUB_PERMISSION (
        ACL_PERMISSION_ID numeric(19,0) not null,
        SECURITY_ROLE_ID numeric(19,0) not null,
        primary key (SECURITY_ROLE_ID, ACL_PERMISSION_ID)
    );

    create table CS_RESOURCE_GROUP (
        ID numeric(19,0) identity not null,
        OPERATE_TIME numeric(19,0) null,
        OPERATOR varchar(50) null,
        REMOVED int null,
        CLASS_NAME varchar(255) not null,
        CODE varchar(100) null,
        DESCRIPTION varchar(255) null,
        PROP_VALUES varchar(255) null,
        NAME varchar(255) null,
        ACL_RES_TYPE_ID numeric(19,0) null,
        primary key (ID)
    );

    create table CS_REV_PERMISSION (
        ACL_PERMISSION_ID numeric(19,0) not null,
        SECURITY_ROLE_ID numeric(19,0) not null,
        primary key (SECURITY_ROLE_ID, ACL_PERMISSION_ID)
    );

    create table CS_ROLE (
        ID numeric(19,0) identity not null,
        OPERATE_TIME numeric(19,0) null,
        OPERATOR varchar(50) null,
        REMOVED int null,
        DESCRIPTION varchar(255) null,
        NAME varchar(100) not null unique,
        TYPE varchar(1) not null,
        PARENT_ROLE_ID numeric(19,0) null,
        primary key (ID)
    );

    create table CS_USER (
        ID numeric(19,0) identity not null,
        OPERATE_TIME numeric(19,0) null,
        OPERATOR varchar(50) null,
        REMOVED int null,
        ACCOUNTTYPE int null,
        AUTHENTIC_TYPE int null,
        CERTIFICATE varchar(255) null,
        CREATEDATE numeric(19,0) null,
        LOGIN_NAME varchar(100) not null,
        NAME varchar(100) null,
        PASSWORD varchar(100) not null,
        RESET_CODE varchar(255) null,
        SEX varchar(255) null,
        STATUS numeric(19,0) null,
        USERTYPE int null,
        ADDRESS varchar(255) null,
        EMAIL varchar(100) null,
        EXT1 varchar(255) null,
        EXT2 varchar(255) null,
        EXT3 varchar(255) null,
        EXT4 varchar(255) null,
        EXT5 varchar(255) null,
        EXT6 varchar(255) null,
        EXT7 varchar(255) null,
        EXT8 varchar(255) null,
        FAX varchar(50) null,
        HOME_PHONE varchar(50) null,
        MOBILE1 varchar(50) null,
        MOBILE2 varchar(50) null,
        OFFICE_PHONE varchar(50) null,
        primary key (ID)
    );

    create table CS_USER_APPNODE (
        APP_NODE_ID numeric(19,0) not null,
        USER_ID numeric(19,0) not null,
        primary key (USER_ID, APP_NODE_ID)
    );

    create table CS_USER_EXTEND_PROPERTY (
        ID numeric(19,0) identity not null,
        EXT1 varchar(255) null,
        EXT10 varchar(255) null,
        EXT11 varchar(255) null,
        EXT12 varchar(255) null,
        EXT13 varchar(255) null,
        EXT14 varchar(255) null,
        EXT15 varchar(255) null,
        EXT16 varchar(255) null,
        EXT17 varchar(255) null,
        EXT18 varchar(255) null,
        EXT19 varchar(255) null,
        EXT2 varchar(255) null,
        EXT20 varchar(255) null,
        EXT21 varchar(255) null,
        EXT22 varchar(255) null,
        EXT23 varchar(255) null,
        EXT24 varchar(255) null,
        EXT25 varchar(255) null,
        EXT26 varchar(255) null,
        EXT27 varchar(255) null,
        EXT28 varchar(255) null,
        EXT29 varchar(255) null,
        EXT3 varchar(255) null,
        EXT30 varchar(255) null,
        EXT31 varchar(255) null,
        EXT32 varchar(255) null,
        EXT33 varchar(255) null,
        EXT34 varchar(255) null,
        EXT35 varchar(255) null,
        EXT36 varchar(255) null,
        EXT37 varchar(255) null,
        EXT38 varchar(255) null,
        EXT39 varchar(255) null,
        EXT4 varchar(255) null,
        EXT40 varchar(255) null,
        EXT5 varchar(255) null,
        EXT6 varchar(255) null,
        EXT7 varchar(255) null,
        EXT8 varchar(255) null,
        EXT9 varchar(255) null,
        SECURITY_USER_ID numeric(19,0) null,
        primary key (ID)
    );

    create table CS_USER_GROUP (
        SECURITY_GROUP_ID numeric(19,0) not null,
        SECURITY_USER_ID numeric(19,0) not null,
        primary key (SECURITY_USER_ID, SECURITY_GROUP_ID)
    );

    create table CS_USER_LOGIN (
        ID numeric(19,0) identity not null,
        APP_ID numeric(19,0) null,
        APP_NAME varchar(255) null,
        AREA varchar(255) null,
        BROWSER varchar(255) null,
        ERROR_INFO varchar(255) null,
        IP_ADDRESS varchar(255) null,
        LOGIN_NAME varchar(255) null,
        LOGIN_TIME numeric(19,0) null,
        ONLINE_TIME numeric(19,0) null,
        OPERATING_SYSTEM varchar(255) null,
        USER_NAME varchar(255) null,
        USER_STATE int null,
        USER_ID numeric(19,0) null,
        primary key (ID)
    );

    create table CS_USER_ORGANNODE (
        ORGAN_NODE_ID numeric(19,0) not null,
        SECURITY_USER_ID numeric(19,0) not null,
        ORDERS numeric(19,0) null,
        primary key (SECURITY_USER_ID, ORGAN_NODE_ID)
    );

    create table CS_USER_ROLE (
        SECURITY_ROLE_ID numeric(19,0) not null,
        SECURITY_USER_ID numeric(19,0) not null,
        primary key (SECURITY_USER_ID, SECURITY_ROLE_ID)
    );

    create table CS_WEBAPP_NODE (
        ID numeric(19,0) identity not null,
        OPERATE_TIME numeric(19,0) null,
        OPERATOR varchar(50) null,
        REMOVED int null,
        APP_SERVER varchar(255) null,
        CODE varchar(100) null,
        COMPATABLE tinyint null,
        IS_CUTE_SERIES tinyint null,
        DB_SERVER varchar(255) null,
        DESCRIPTION varchar(255) null,
        INDEX_URL varchar(255) null,
        LOGOUT_STYLE tinyint null,
        MAX_INACTIVE_SECS numeric(19,0) null,
        NAME varchar(255) null,
        NODE_TYPE numeric(19,0) not null,
        ORGAN_ID numeric(19,0) null,
        ROOT_MENU_CODE varchar(255) null,
        VENDOR varchar(255) null,
        VERSION varchar(255) null,
        CENTRAL_NODE_ID numeric(19,0) null,
        primary key (ID)
    );

    create table CW_ACT_INSTANCE (
        ACT_INSTANCE_ID varchar(255) not null,
        PROPERTIES varbinary(2000) null,
        ACTIVITY_ID varchar(255) null,
        ACTIVITY_NAME varchar(255) null,
        ACTIVITY_STATE int null,
        ACTIVITY_TYPE int null,
        END_TIME datetime null,
        INPUT_CONTAINERS varchar(255) null,
        OUTPUT_CONTAINERS varchar(255) null,
        START_TIME datetime null,
        PROCESS_INSTANCE_ID varchar(255) not null,
        primary key (ACT_INSTANCE_ID)
    );

    create table CW_AUDIT_ACT_INST (
        id numeric(19,0) identity not null,
        ACTION int not null,
        CHANGEDTIME datetime not null,
        NEWSTATE int null,
        PERVIOUSSTATE int null,
        ACTIVITY_INSTANCE_ID varchar(255) not null,
        ACTIVITY_NAME varchar(255) not null,
        PROCESS_INSTANCE_ID varchar(255) not null,
        primary key (id)
    );

    create table CW_AUDIT_CONFIG (
        id numeric(19,0) identity not null,
        ISAUDIT_ACTIVITY_INSTANCE tinyint null,
        ISAUDIT_GLOBAL tinyint null,
        ISAUDIT_PROCESS_DATA tinyint null,
        PROCESS_ID varchar(255) null unique,
        ISAUDIT_PROCESS_INSTANCE tinyint null,
        ISAUDIT_PROCESS_MODEL tinyint null,
        ISAUDIT_WORKITEM tinyint null,
        primary key (id)
    );

    create table CW_AUDIT_PDATA (
        id numeric(19,0) identity not null,
        ACTION int null,
        ATTRIBUTE_NAME varchar(255) null,
        CHANGED_TIME datetime null,
        CONTAINER_INSTANCE_ID varchar(255) null,
        NEW_DATA varchar(255) null,
        OLD_DATA varchar(255) null,
        PROCESS_DATA_ID numeric(19,0) null,
        PROCESS_INSTANCE_ID varchar(255) null,
        primary key (id)
    );

    create table CW_AUDIT_PINSTANCE (
        ID numeric(19,0) identity not null,
        ACTION int not null,
        CHANGEDTIME datetime not null,
        NEWSTATE int null,
        PERVIOUSSTATE int null,
        PROCESS_ID varchar(255) not null,
        PROCESS_INSTANCE_ID varchar(255) not null,
        PROCESS_NAME varchar(255) null,
        primary key (ID)
    );

    create table CW_AUDIT_PMODEL (
        id numeric(19,0) identity not null,
        ACTION int not null,
        CHANGEDTIME datetime not null,
        NEWSTATE int null,
        PERVIOUSSTATE int null,
        MODEL_ID varchar(255) null,
        PROCESS_ID varchar(255) null,
        PROCESS_NAME varchar(255) null,
        primary key (id)
    );

    create table CW_AUDIT_WORKITEM (
        id numeric(19,0) identity not null,
        ACTION int not null,
        CHANGEDTIME datetime not null,
        NEWSTATE int null,
        PERVIOUSSTATE int null,
        ACTIVITY_INSTANCE_ID varchar(255) null,
        ACTIVITY_NAME varchar(255) null,
        WORKITEM_ID numeric(19,0) null,
        primary key (id)
    );

    create table CW_CON_INSTANCE (
        CON_INSTANCE_ID varchar(255) not null,
        CONTAINER_ID varchar(255) null,
        CONTAINER_NAME varchar(255) null,
        PROCESS_INSTANCE_ID varchar(255) null,
        primary key (CON_INSTANCE_ID)
    );

    create table CW_PROCESS_DATA (
        ID numeric(19,0) identity not null,
        ATTRIBUTE_NAME varchar(255) null,
        ATTRIBUTE_TYPE varchar(255) null,
        ATTRIBUTE_VALUE varchar(255) null,
        MODIFIED_TIME datetime null,
        RECORD_INDEX int null,
        CON_INSTANCE_ID varchar(255) null,
        primary key (ID)
    );

    create table CW_PROCESS_INSTANCE (
        PROCESS_INSTANCE_ID varchar(255) not null,
        PROPERTIES varbinary(2000) null,
        BUSINESS_ENTITYID varchar(255) null,
        ENABLED_ACTIVITIES varchar(255) null,
        END_TIME datetime null,
        EXTERNALE_KEY varchar(255) null,
        PARENT_INSTANCE_ID varchar(255) null,
        PROCESS_NAME varchar(255) null,
        PROCESS_STATE int null,
        START_TIME datetime null,
        PROCESS_ID varchar(255) not null,
        primary key (PROCESS_INSTANCE_ID)
    );

    create table CW_PROCESS_MODEL (
        PROCESS_ID varchar(255) not null,
        BUSINESS_TYPE int null,
        DESCRIPTION varchar(255) null,
        MODEL text null,
        MODEL_ID varchar(255) null,
        NAME varchar(255) null,
        PUBLISH_STATE int null,
        VERSION int null,
        primary key (PROCESS_ID)
    );

    create table CW_TIME_ACTIVITY (
        id numeric(19,0) identity not null,
        EXPECTED_TIME datetime null,
        STARTED_TIME datetime null,
        ACTIVITY_INSTANCE_ID varchar(255) null,
        primary key (id)
    );

    create table CW_TRANS_INSTANCE (
        TRANS_INSTANCE_ID varchar(255) not null,
        PROPERTIES varbinary(2000) null,
        MODIFIED_TIME datetime null,
        TRANSITION_ID varchar(255) null,
        TRANSITION_NAME varchar(255) null,
        TRANSITION_STATE int null,
        TRANSITION_TYPE int null,
        FROM_AINSTANCE_ID varchar(255) null,
        PROCESS_INSTANCE_ID varchar(255) null,
        TO_AINSTANCE_ID varchar(255) null,
        primary key (TRANS_INSTANCE_ID)
    );

    create table CW_WORK_ITEMS (
        WORKITEM_ID numeric(19,0) identity not null,
        ACTIVITY_ID varchar(255) null,
        ACTIVITY_NAME varchar(255) null,
        DEADLINE datetime null,
        DESCRIPTION varchar(255) null,
        MODEL_ID varchar(255) null,
        OUTER_ID varchar(255) null,
        PROCESS_INSTANCE_ID varchar(255) null,
        WORKITEM_TYPE int null,
        WORK_STATE int null,
        ACT_INSTANCE_ID varchar(255) null,
        primary key (WORKITEM_ID)
    );

    create table CW_WORK_PERSON (
        ID numeric(19,0) identity not null,
        DESCRIPTION varchar(255) null,
        EXECUTE_TIME datetime null,
        WORK_INDEX int null,
        IS_SHOW int null,
        PERSON_ID varchar(255) null,
        WORKITEM_ID numeric(19,0) null,
        primary key (ID)
    );

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
