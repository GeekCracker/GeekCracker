-- �û���
drop table if exists t_user;
create table if not exists t_user(
	id bigint(20) not null primary key auto_increment comment '����id',
	user_name varchar(31) comment '�û���',
	user_password varchar(63) comment '�û�����',
	user_phone varchar(11) comment '�û���ϵ��ʽ',
	user_mail varchar(64) comment '�û�����',
	role_id bigint(20) default null comment '��ɫid',
	created_time timestamp null default null comment '�û�����ʱ��',
	updated_time timestamp null default null comment '�û��޸�ʱ��',
	deleted tinyint(4) default null comment '�Ƿ�ɾ��1����ɾ��0��ɾ��'
);
-- ��ɫ��
drop table if exists t_role;
create table if not exists t_role(
	id bigint(20) not null primary key auto_increment comment '����id',
	role_name varchar(31) comment '��ɫ����',
	created_time timestamp null default null comment '��ɫ����ʱ��',
	memo varchar(63) comment '��ɫ����',
	updated_time timestamp null default null comment '��ɫ�޸�ʱ��',
	deleted tinyint(4) default null comment '�Ƿ�ɾ��1����ɾ��0��ɾ��'
);
-- Ȩ�ޱ�
drop table if exists t_authority;
create table if not exists t_authority(
	id bigint(20) not null primary key auto_increment comment '����id',
	auth_name varchar(31) comment 'Ȩ������',
	memo varchar(63) comment 'Ȩ������',
	created_time timestamp null default null comment 'Ȩ�޴���ʱ��',
	updated_time timestamp null default null comment 'Ȩ���޸�ʱ��',
	deleted tinyint(4) default null comment '�Ƿ�ɾ��1����ɾ��0��ɾ��'
);
-- ��ɫȨ�޹�����
drop table if exists t_role_authority;
create table if not exists t_role_authority(
	id bigint(20) not null primary key auto_increment comment '����id',
	role_id bigint(20) default null comment '��ɫid',
	auth_id bigint(20) default null comment 'Ȩ��id'
);
-- ���±�
drop table if exists t_article;
create table if not exists t_article(
	id bigint(20) not null primary key auto_increment comment '����id',
	article_title varchar(63) comment '���±���',
	article_author varchar(15) comment '��������',
	article_content text(0) comment '��������',
	article_pic varchar(255) comment '���·���ͼƬ',
	article_seq tinyint(10) default 0 comment '�������',
	memo varchar(255) comment '��������',
	created_time timestamp null default null comment '����ʱ��',
	updated_time timestamp null default null comment '�޸�ʱ��',
	deleted tinyint(4) default null comment '�Ƿ�ɾ��1����ɾ��0��ɾ��'
);
-- ͼƬ��
drop table if exists t_image;
create table if not exists t_image(
	id bigint(20) not null primary key auto_increment comment '����id',
	image_title varchar(63) comment 'ͼƬ����',
	image_url varchar(255) comment 'ͼƬ����·��',
	image_seq tinyint(10) default 0 comment 'ͼƬ���',
	memo varchar(255) comment 'ͼƬ����',
	created_time timestamp null default null comment '����ʱ��',
	updated_time timestamp null default null comment '�޸�ʱ��',
	deleted tinyint(4) default null comment '�Ƿ�ɾ��1����ɾ��0��ɾ��'
);
-- �γ̱�
drop table if exists t_course;
create table if not exists t_course(
	id bigint(20) not null primary key auto_increment comment '����id',
	course_name varchar(63) comment '�γ�����',
	memo varchar(255) comment '�γ�����',
	created_time timestamp null default null comment '����ʱ��',
	updated_time timestamp null default null comment '�޸�ʱ��',
	deleted tinyint(4) default null comment '�Ƿ�ɾ��1����ɾ��0��ɾ��'
);
-- �û���γ��м��
drop table if exists t_user_course;
create table if not exists t_user_course(
	id bigint(20) not null primary key auto_increment comment '����id',
	user_id bigint(20) default null comment '�û�id',
	course_id bigint(20) default null comment '�γ�id',
	apply_time timestamp null default null comment '����ʱ��',
	cancel_time timestamp null default null comment 'ȡ������ʱ��',
	created_time timestamp null default null comment '����ʱ��',
	updated_time timestamp null default null comment '�޸�ʱ��',
	deleted tinyint(4) default null comment '�Ƿ�ɾ��1����ɾ��0��ɾ��'
);





