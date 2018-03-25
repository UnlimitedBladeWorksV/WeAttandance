/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/11/22 17:16:44                          */
/*==============================================================*/


###################### DICTIONARY #########################################################

/*==============================================================*/
/* Table: T_ENTITY                                              */
/*==============================================================*/
create table T_ENTITY
(
   ENTITY_TABLE_NAME varchar(255) not null,
   ENTITY_TABLE_DESCRIPTION varchar(255) not null,
   primary key (ENTITY_TABLE_NAME)
);
insert into T_ENTITY_TABLE VALUES ('T_USER', '用户表')
insert into T_ENTITY_TABLE VALUES ('T_ATTEND', '考勤表')
insert into T_ENTITY_TABLE VALUES ('T_ORG_ATTEND', '考勤组织表')
insert into T_ENTITY_TABLE VALUES ('T_USER_ATTEND', '用户_考勤表')
insert into T_ENTITY_TABLE VALUES ('T_USER_ORG_ATTEND', '用户_考勤组织表')

/*==============================================================*/
/* Table: T_ROLE                                                */
/*==============================================================*/
create table T_ROLE
(
   ROLE_ID     bigint not null auto_increment,
   ROLE_NAME   varchar(255) not null,
   primary key (ROLE_ID)
);
insert into T_ROLE VALUES ('USER')
insert into T_ROLE VALUES ('ADMIN')



############################# ENTITY ##################################################

drop table if exists T_ATTEND;

drop table if exists T_ORG_ATTEND;

drop table if exists T_USER;

drop table if exists T_USER_ATTEND;

drop table if exists T_USER_ORG_ATTEND;

drop table if exists T_VERIFICATION_REGISTER_EMAIL;

/*==============================================================*/
/* Table: T_ATTEND                                              */
/*==============================================================*/
create table T_ATTEND
(
   PK_ATTEND_ID         bigint not null auto_increment,
   FK_ORG_ATTEND_ID     bigint not null,
   ATTEND_TITLE         varchar(255) not null,
   ATTEND_CREATE_BY_ID  bigint not null,
   ATTEND_CREATE_TIME   datetime not null,
   ATTEND_LOCATION      varchar(255) not null,
   ATTEND_START_TIME    datetime not null,
   ATTEND_OVER_TIME     datetime not null,
   ATTEND_PEOPLE_NUM    int not null,
   ATTEND_TRUE_NUM      int not null,
   ATTEND_LATE_NUM      int not null,
   ATTEND_FALSE_NUM     int not null,
   ATTEND_QR_CODE       varchar(255) not null,
   ATTEND_QR_CODE_OVERDUE datetime not null,
   ATTEND_QR_CODE_REFRESH_SECOND int not null,
   primary key (PK_ATTEND_ID)
);

/*==============================================================*/
/* Table: T_ORG_ATTEND                                          */
/*==============================================================*/
create table T_ORG_ATTEND
(
   PK_ORG_ATTEND_ID     bigint not null auto_increment,
   ORG_ATTEND_NAME      varchar(255) not null,
   ORG_ATTEND_CREATE_BY_ID bigint not null,
   ORG_ATTEND_CREATE_TIME datetime not null,
   ORG_ATTEND_PEOPLE_NUM int not null,
   ORG_ATTEND_QR_CODE   varchar(255) not null,
   ORG_ATTEND_QR_CODE_EFFECTIVE_DAYS int not null,
   ORG_ATTEND_QR_CODE_OVERDUE datetime not null,
   ORG_ATTEND_DESCRIPTION varchar(255) not null,
   primary key (PK_ORG_ATTEND_ID)
);

/*==============================================================*/
/* Table: T_USER                                                */
/*==============================================================*/
create table T_USER
(
   PK_USER_ID           bigint not null auto_increment,
   USER_EMAIL           varchar(255) not null,
   USER_PASSWD          varchar(255) not null,
   USER_USER_NAME       varchar(255) not null,
   USER_PHONE           varchar(255) not null,
   USER_SEX             bool not null, # true 男 false 女
   USER_IS_LOGIN        bool not null,
   USER_REGISTER_TIME   datetime not null,
   USER_DESCRIPTION     varchar(255) not null,
   USER_TRUE_NAME       varchar(255) not null,
   USER_AUTHORITY       varchar(255) not null,
   USER_LAST_RESET_PASSWD_TIME datetime not null,
   primary key (PK_USER_ID)
);

/*==============================================================*/
/* Table: T_USER_ATTEND                                         */
/*==============================================================*/
create table T_USER_ATTEND
(
   FK_ATTEND_ID         bigint not null,
   FK_USER_ID           bigint not null,
   ATTEND_TIME          datetime not null,
   ATTEND_STATE         varchar(255) not null,
   primary key (FK_ATTEND_ID, FK_USER_ID)
);

/*==============================================================*/
/* Table: T_USER_ORG_ATTEND                                     */
/*==============================================================*/
create table T_USER_ORG_ATTEND
(
   FK_USER_ID           bigint not null,
   FK_ORG_ATTEND_ID     bigint not null,
   USER_ATTEND_TIME     datetime not null,
   ATTEND_TRUE_NUM      int not null,
   ATTEND_LATE_NUM      int not null,
   ATTEND_FALSE_NUM     int not null,
   primary key (FK_USER_ID, FK_ORG_ATTEND_ID)
);

/*==============================================================*/
/* Table: T_VERIFICATION_REGISTER_EMAIL                         */
/*==============================================================*/
create table T_VERIFICATION_REGISTER_EMAIL
(
   VERI_EMAIL           varchar(255) not null,
   VERI_CODE            int not null,
   VERI_OVERDUE         datetime not null,
   VERI_IS_REGISTERED   bool not null,
   primary key (VERI_EMAIL)
);

/*==============================================================*/
/* Foreign Constraint (                                         */
/*                     name:FK_table_referTable)                */
/*==============================================================*/
alter table T_ATTEND add constraint FK_ATTEND_ORG_ATTEND foreign key (FK_ORG_ATTEND_ID)
      references T_ORG_ATTEND (PK_ORG_ATTEND_ID) on delete restrict on update restrict;

alter table T_USER_ATTEND add constraint FK_USER_ATTEND_ATTEND foreign key (FK_ATTEND_ID)
      references T_ATTEND (PK_ATTEND_ID) on delete restrict on update restrict;

alter table T_USER_ATTEND add constraint FK_USER_ATTEND_USER foreign key (FK_USER_ID)
      references T_USER (PK_USER_ID) on delete restrict on update restrict;

alter table T_USER_ORG_ATTEND add constraint FK_USER_ORG_ATTEND_USER foreign key (FK_USER_ID)
      references T_USER (PK_USER_ID) on delete restrict on update restrict;

alter table T_USER_ORG_ATTEND add constraint FK_USER_ORG_ATTEND_ORG_ATTEND foreign key (FK_ORG_ATTEND_ID)
      references T_ORG_ATTEND (PK_ORG_ATTEND_ID) on delete restrict on update restrict;







