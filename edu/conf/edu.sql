-- 用户表
drop table if exists t_user;
create table if not exists t_user(
	id bigint(20) not null primary key auto_increment comment '主键id',
	user_name varchar(31) comment '用户名',
	user_password varchar(63) comment '用户密码',
	user_phone varchar(11) comment '用户联系方式',
	user_mail varchar(64) comment '用户邮箱',
	role_id bigint(20) default null comment '角色id',
	created_time timestamp null default null comment '用户创建时间',
	updated_time timestamp null default null comment '用户修改时间',
	deleted tinyint(4) default null comment '是否删除1：不删除0：删除'
);
-- 角色表
drop table if exists t_role;
create table if not exists t_role(
	id bigint(20) not null primary key auto_increment comment '主键id',
	role_name varchar(31) comment '角色名称',
	created_time timestamp null default null comment '角色创建时间',
	memo varchar(63) comment '角色描述',
	updated_time timestamp null default null comment '角色修改时间',
	deleted tinyint(4) default null comment '是否删除1：不删除0：删除'
);
-- 权限表
drop table if exists t_authority;
create table if not exists t_authority(
	id bigint(20) not null primary key auto_increment comment '主键id',
	auth_name varchar(31) comment '权限名称',
	memo varchar(63) comment '权限描述',
	created_time timestamp null default null comment '权限创建时间',
	updated_time timestamp null default null comment '权限修改时间',
	deleted tinyint(4) default null comment '是否删除1：不删除0：删除'
);
-- 角色权限关联表
drop table if exists t_role_authority;
create table if not exists t_role_authority(
	id bigint(20) not null primary key auto_increment comment '主键id',
	role_id bigint(20) default null comment '角色id',
	auth_id bigint(20) default null comment '权限id'
);
-- 文章表
drop table if exists t_article;
create table if not exists t_article(
	id bigint(20) not null primary key auto_increment comment '主键id',
	article_title varchar(63) comment '文章标题',
	article_author varchar(15) comment '文章作者',
	article_content text(0) comment '文章内容',
	article_pic varchar(255) comment '文章封面图片',
	article_seq tinyint(10) default 0 comment '文章序号',
	memo varchar(255) comment '文章描述',
	created_time timestamp null default null comment '创建时间',
	updated_time timestamp null default null comment '修改时间',
	deleted tinyint(4) default null comment '是否删除1：不删除0：删除'
);
-- 图片表
drop table if exists t_image;
create table if not exists t_image(
	id bigint(20) not null primary key auto_increment comment '主键id',
	image_title varchar(63) comment '图片标题',
	image_url varchar(255) comment '图片访问路径',
	image_seq tinyint(10) default 0 comment '图片序号',
	memo varchar(255) comment '图片描述',
	created_time timestamp null default null comment '创建时间',
	updated_time timestamp null default null comment '修改时间',
	deleted tinyint(4) default null comment '是否删除1：不删除0：删除'
);
-- 课程表
drop table if exists t_course;
create table if not exists t_course(
	id bigint(20) not null primary key auto_increment comment '主键id',
	course_name varchar(63) comment '课程名称',
	memo varchar(255) comment '课程描述',
	created_time timestamp null default null comment '创建时间',
	updated_time timestamp null default null comment '修改时间',
	deleted tinyint(4) default null comment '是否删除1：不删除0：删除'
);
-- 用户与课程中间表
drop table if exists t_user_course;
create table if not exists t_user_course(
	id bigint(20) not null primary key auto_increment comment '主键id',
	user_id bigint(20) default null comment '用户id',
	course_id bigint(20) default null comment '课程id',
	apply_time timestamp null default null comment '报名时间',
	cancel_time timestamp null default null comment '取消报名时间',
	created_time timestamp null default null comment '创建时间',
	updated_time timestamp null default null comment '修改时间',
	deleted tinyint(4) default null comment '是否删除1：不删除0：删除'
);





