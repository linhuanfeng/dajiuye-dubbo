create schema if not exists dajiuye collate utf8_general_ci;

create table if not exists acl_permission
(
	id char(19) default '' not null comment '编号'
		primary key,
	pid char(19) default '' not null comment '所属上级',
	name varchar(20) default '' not null comment '名称',
	type tinyint(3) default 0 not null comment '类型(1:菜单,2:按钮)',
	permission_value varchar(50) null comment '权限值',
	path varchar(100) null comment '访问路径',
	component varchar(100) null comment '组件路径',
	icon varchar(50) null comment '图标',
	status tinyint null comment '状态(0:禁止,1:正常)',
	is_deleted tinyint(1) unsigned default 0 not null comment '逻辑删除 1（true）已删除， 0（false）未删除',
	gmt_create datetime null comment '创建时间',
	gmt_modified datetime null comment '更新时间'
)
comment '权限' charset=utf8mb4;

create index idx_pid
	on acl_permission (pid);

create table if not exists acl_role
(
	id char(19) default '' not null comment '角色id'
		primary key,
	role_name varchar(20) default '' not null comment '角色名称',
	role_code varchar(20) null comment '角色编码',
	remark varchar(255) null comment '备注',
	is_deleted tinyint(1) unsigned default 0 not null comment '逻辑删除 1（true）已删除， 0（false）未删除',
	gmt_create datetime not null comment '创建时间',
	gmt_modified datetime not null comment '更新时间'
)
charset=utf8mb4;

create table if not exists acl_role_permission
(
	id char(19) default '' not null
		primary key,
	role_id char(19) default '' not null,
	permission_id char(19) default '' not null,
	is_deleted tinyint(1) unsigned default 0 not null comment '逻辑删除 1（true）已删除， 0（false）未删除',
	gmt_create datetime not null comment '创建时间',
	gmt_modified datetime not null comment '更新时间'
)
comment '角色权限' charset=utf8mb4;

create index idx_permission_id
	on acl_role_permission (permission_id);

create index idx_role_id
	on acl_role_permission (role_id);

create table if not exists acl_user_role
(
	id char(19) default '' not null comment '主键id'
		primary key,
	role_id char(19) default '0' not null comment '角色id',
	user_id varchar(255) default '0' not null comment '用户openId',
	is_deleted tinyint(1) unsigned default 0 not null comment '逻辑删除 1（true）已删除， 0（false）未删除',
	gmt_create datetime not null comment '创建时间',
	gmt_modified datetime not null comment '更新时间'
)
charset=utf8mb4;

create index idx_role_id
	on acl_user_role (role_id);

create index idx_user_id
	on acl_user_role (user_id);

create table if not exists area_city
(
	id int unsigned auto_increment comment '自增 id'
		primary key,
	city_name varchar(50) default '' null comment '城市名称',
	city_adcode int unsigned default 0 null comment '城市行政区划代码',
	province_adcode int unsigned default 0 null comment '省份行政区划代码',
	province_id int unsigned default 0 null comment '省份 id'
)
comment '城市表' charset=utf8mb4;

create table if not exists area_district
(
	id int unsigned auto_increment comment '自增 id'
		primary key,
	district_name varchar(50) default '' null comment '区县名称',
	district_adcode int unsigned default 0 null comment '区县行政区划代码',
	city_adcode int unsigned default 0 null comment '城市行政区划代码',
	city_id int unsigned default 0 null comment '城市 id',
	province_adcode int unsigned default 0 null comment '省份行政区划代码',
	province_id int unsigned default 0 null comment '省份 id'
)
comment '区县表' charset=utf8mb4;

create table if not exists area_province
(
	id int unsigned auto_increment comment '自增 id'
		primary key,
	province_name varchar(50) default '' null comment '省份名称',
	province_adcode int unsigned default 0 null comment '省份行政区划代码'
)
comment '省份表' charset=utf8mb4;

create table if not exists company
(
	comId varchar(80) not null
		primary key,
	comFullName varchar(40) null,
	comAuthCapital varchar(40) null,
	comIndustry varchar(40) null,
	comMail varchar(40) null,
	comAddr varchar(80) null,
	comWebsite varchar(80) null,
	comIntro varchar(3000) null,
	comMinName varchar(40) null,
	comScale varchar(40) null,
	comLicense varchar(160) null,
	comLogo varchar(160) null,
	comClass varchar(160) null,
	incorporationDate varchar(40) null,
	comWelfare varchar(3000) null,
	comLinks varchar(140) null
);

create table if not exists index_cate
(
	id int auto_increment
		primary key,
	name varchar(140) null,
	image_src varchar(140) null,
	open_type varchar(140) null,
	navigator_url varchar(140) null
);

create table if not exists job
(
	jobId varchar(80) not null
		primary key,
	jobSalary varchar(20) null,
	jobName varchar(80) null,
	jobPlace varchar(80) null,
	jobEdu varchar(10) null,
	jobAge varchar(10) null,
	jobDayPerWeek varchar(20) null,
	jobImg varchar(80) null,
	jobComId varchar(80) null,
	jobUpdateTime timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
	jobReleaseTime datetime default '2021-12-28 17:31:00' not null,
	email varchar(20) null comment '简历接收邮箱',
	jobAuthorId bigint null,
	jobDetails varchar(3000) null,
	jobStat varchar(80) null,
	jobViewCnt varchar(80) null,
	jobPriority varchar(80) null,
	jobIndustry varchar(40) null,
	jobTimeSpan varchar(40) null,
	jobDeadLine varchar(40) null,
	jobWelfare varchar(1000) null,
	jobMid int null comment '中分类外键',
	jobType int null comment '1表示实习，2表示校招，3表示社招',
	jobSid int null comment '小分类外键'
);

create index comfk
	on job (jobComId);

create index midfk
	on job (jobMid);

create index sidfk
	on job (jobSid);

create index tyepfk
	on job (jobType);

create table if not exists job_type
(
	id int auto_increment
		primary key,
	type_name varchar(50) null comment '类型名称'
);

create table if not exists lcategory
(
	id int not null
		primary key,
	lName varchar(80) null,
	lDesc varchar(140) null
);

create table if not exists mcategory
(
	id int not null
		primary key,
	mName varchar(80) null,
	mDesc varchar(140) null,
	lId int null,
	constraint lfk
		foreign key (lId) references lcategory (id)
);

create table if not exists scategory
(
	id int not null
		primary key,
	sName varchar(80) null,
	sDesc varchar(140) null,
	mId int null,
	constraint mfk
		foreign key (mId) references mcategory (id)
);

create table if not exists school
(
	schId bigint auto_increment
		primary key,
	schAuthor varchar(30) null,
	schImg1 varchar(256) null,
	schImg2 varchar(256) null,
	schImg3 varchar(256) null,
	schImg4 varchar(256) null,
	schImg5 varchar(256) null,
	schImg6 varchar(256) null,
	schImg7 varchar(256) null,
	schImg8 varchar(256) null,
	schCreateTime date null,
	schUpdateTime date null,
	schComId int not null,
	schStat tinyint(1) null,
	schViewCnt smallint null,
	schName varchar(30) null
);

create table if not exists sec_lesson
(
	id bigint null comment '秒杀课程id',
	start_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '开始时间',
	end_time timestamp default '0000-00-00 00:00:00' not null comment '结束时间',
	stock int null comment '库存数量',
	img varchar(255) null comment '课程图片',
	name varchar(64) null comment '课程名称'
);

create table if not exists swiper
(
	id int auto_increment
		primary key,
	image_src varchar(140) null,
	open_type varchar(140) null,
	goods_id int(140) null,
	navigator_url varchar(140) null
);

create table if not exists user
(
	id bigint unsigned auto_increment
		primary key,
	user_name varchar(63) null comment '用户名称',
	user_password varchar(63) null comment '用户密码',
	gender tinyint(3) default 0 null comment '性别：0 未知， 1男， 1 女',
	birthday date null comment '生日',
	temp_deliver_time datetime null comment '临时存储投递简历的时间',
	last_login_ip varchar(63) null comment '最近一次登录IP地址',
	com_id varchar(80) null comment '公司id',
	nick_name varchar(63) null comment '用户昵称或网络名称',
	mobile varchar(20) null comment '用户手机号码',
	avatar varchar(255) null comment '用户头像图片',
	open_id varchar(100) null comment '微信登录openid',
	session_key varchar(100) null comment '微信登录会话KEY',
	user_status tinyint(3) default 0 null comment '0 可用, 1 禁用, 2 注销',
	is_hr int default 0 null,
	resume varchar(255) null comment '简历',
	city varchar(100) null comment '城市',
	province varchar(100) null comment '省份',
	university varchar(80) null,
	qualification varchar(80) null comment '学历',
	graduation_year varchar(80) null comment '哪一届',
	honor varchar(80) null comment '荣誉奖项',
	temp_job_name varchar(80) null comment '方便返回应聘的公司名字',
	constraint idx_uni_openid
		unique (open_id)
)
comment '大就业小程序-用户表';

create table if not exists message
(
	id bigint auto_increment
		primary key,
	from_open_id varchar(100) null comment '发送者open_id',
	to_open_id varchar(100) null comment '接收者open_id',
	news varchar(512) null comment '消息',
	create_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '发送时间',
	constraint fk_user_from_open_id
		foreign key (from_open_id) references user (open_id)
			on delete set null,
	constraint fk_user_to_open_id
		foreign key (to_open_id) references user (open_id)
			on delete set null
);

create index idx_from_to
	on message (from_open_id, to_open_id);

create table if not exists user_deliver
(
	id bigint auto_increment
		primary key,
	from_user_id bigint null,
	to_hr_id bigint null,
	resume_url varchar(255) null,
	time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
	state smallint(10) null comment '投递状态：0未查看，1已查看，2通过初筛，3不合适',
	job_id varchar(80) null,
	message varchar(80) null comment '面试官留言'
);

create table if not exists zp_position
(
	id int auto_increment
		primary key,
	pid int default 0 null comment '上级ID',
	level tinyint(1) default 1 null comment '职位级别 1第一层职位 2第二层职位 3第三层职位',
	name varchar(64) null comment '职位名称',
	hide tinyint(1) default 0 null comment '0显示1隐藏',
	state tinyint(1) default 1 null comment '状态0禁用1启用',
	update_time int default 0 null comment '更新时间',
	create_time int default 0 null comment '创建时间',
	delete_time int default 0 null comment '删除时间'
);

create definer = root@localhost procedure update_mcategory()
BEGIN
	DECLARE i INT DEFAULT 30;
	WHILE i<115 DO
		SET @name=(SELECT mName FROM mcategory WHERE id=i+1);
		SET @lId=(SELECT lId FROM mcategory WHERE id=i+1);
		UPDATE mcategory SET mName=@name,lId=@lId WHERE id=i;
		SET i=i+1;
	END WHILE;
    END;

