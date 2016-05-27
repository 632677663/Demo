create table t_item(
id bigint(20) not null auto_increment comment '商品id',
title varchar(100) not null comment '商品标题',
sell_point varchar(150) default null comment '商品卖点',
price decimal(11,2) not null comment '商品价格',
num int(10) not null comment '库存数量',
barcode varchar(30) default null comment '商品条形码',
image varchar(500) default null comment '商品图片',
cid bigint(20) not null comment '所属类目',
status tinyint not null default '1' comment '商品状态:1-正常,2-下架,3-删除',
created timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
updated datetime not null comment '更新时间',
primary key (id),
key cid (cid),
key status (status),
key updated (updated)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';


create table t_item_desc(
id bigint(20) default null comment '商品id',
item_desc text comment '商品描述',
created timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
updated datetime not null comment '更新时间',
key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品描述表';


create table t_item_param(
id bigint(20) not null auto_increment comment '商品规则参数id',
item_cid bigint(20) comment '商品所属类目id',
item_data text comment '商品json格式参数数据',
created datetime default null comment '创建时间',
updated datetime default null comment '更新时间',
primary key (id),
key (item_cid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品规则参数表';


create table t_item_param_relation(
id bigint(20) not null auto_increment comment 'id',
item_id bigint(20) comment '商品id',
param_data text comment '商品json格式参数数据',
created timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
updated datetime default null comment '更新时间',
primary key (id),
key (item_id) using BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品规格与商品的关系表';




create table t_user(
id bigint(20) not null auto_increment comment '用户id',
username varchar(50) not null comment '用户名',
password varchar(32) not null comment '用户密码',
phone varchar(20) default null comment '电话号码',
email varchar(50) default null comment '邮箱',
created timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
updated datetime default null comment '更新时间',
primary key (id),
unique key username (username) using BTREE,
unique key phone (phone) using BTREE,
unique key email (email) using BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';




create table t_order(
order_id varchar(50) not null comment '订单id',
payment decimal(11,2) default '0' comment '实际支付金额',
payment_type int(2) default '1' comment '支付类型:1-在线支付 2-货到付款',
post_fee decimal(11,2) default '0' comment '邮递费用',
status int(2) default '0' comment '状态:0-未付款 1-已付款 2-未发货 3-已发货 4-退货 5-交易成功 6-交易关闭',
create_time timestamp not null default CURRENT_TIMESTAMP comment '订单创建时间',
update_time datetime default null comment '订单更新时间',
payment_time datetime default null comment '付款时间',
consign_time datetime default null comment '发货时间',
end_time datetime default null comment '交易完成时间',
close_time datetime default null comment '交易关闭时间',
shipping_name varchar(20) default null comment '物流名称',
shipping_code varchar(20) default null comment '物流单号',
user_id bigint(20) not null comment '用户id',
buyer_nick varchar(50) default null comment '买家名称',
buyer_message varchar(100) default null comment '买家留言',
buyer_rate int(2) default null comment '买家评级:1--5星',
unique key order_id (order_id) using BTREE,
key create_time (create_time),
key status (status),
key buyer_nick (buyer_nick),
key payment_type (payment_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';



create table t_order_item(
item_id bigint(10) not null comment '商品id',
order_id varchar(50) not null comment '订单id',
num int(10) not null comment '商品购买数量',
title varchar(100) not null comment '商品标题',
price decimal(11,2) not null comment '商品价格',
total_fee varchar(20) default null comment '电话号码',
pic_path varchar(50) default null comment '邮箱',
created timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
updated datetime default null comment '更新时间',
key order_id (order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单商品表';


create table t_cart(
id bigint(20) not null auto_increment comment 'id',
user_id bigint(20) default null comment '订单id',
item_id bigint(20) default null comment '商品id',
item_title varchar(100) default null comment '商品标题',
item_image varchar(200) default null comment '商品主图',
item_price decimal(11,2) default null comment '商品价格',
num int(5) default null comment '购买数量',
created timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
updated datetime default null comment '更新时间',
primary key (id),
key userId_itemId (user_id,item_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购物车表';


CREATE TABLE t_content (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  category_id bigint(20) NOT NULL COMMENT '内容类目ID',
  title varchar(200) DEFAULT NULL COMMENT '内容标题',
  sub_title varchar(100) DEFAULT NULL COMMENT '子标题',
  title_desc varchar(500) DEFAULT NULL COMMENT '标题描述',
  url varchar(500) DEFAULT NULL COMMENT '链接',
  pic varchar(300) DEFAULT NULL COMMENT '图片绝对路径',
  pic2 varchar(300) DEFAULT NULL COMMENT '图片2',
  content text COMMENT '内容',
  created timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
  updated datetime default null comment '更新时间',
  PRIMARY KEY (id),
  KEY category_id (category_id),
  KEY updated (updated)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='内容表';



DROP TABLE IF EXISTS tb_content_category;
CREATE TABLE t_content_category (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '类目ID',
  parent_id bigint(20) DEFAULT NULL COMMENT '父类目ID=0时，代表的是一级的类目',
  name varchar(50) DEFAULT NULL COMMENT '分类名称',
  status int(1) DEFAULT '1' COMMENT '状态。可选值:1(正常),2(删除)',
  sort_order int(4) DEFAULT NULL COMMENT '排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数',
  is_parent tinyint(1) DEFAULT '1' COMMENT '该类目是否为父类目，1为true，0为false',
  created timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
  updated datetime default null comment '更新时间',
  PRIMARY KEY (id),
  KEY parent_id (parent_id,status) USING BTREE,
  KEY sort_order (sort_order)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='内容分类';



CREATE TABLE t_order_shipping (
  order_id varchar(50) NOT NULL COMMENT '订单ID',
  receiver_name varchar(20) DEFAULT NULL COMMENT '收货人全名',
  receiver_phone varchar(20) DEFAULT NULL COMMENT '固定电话',
  receiver_mobile varchar(30) DEFAULT NULL COMMENT '移动电话',
  receiver_state varchar(10) DEFAULT NULL COMMENT '省份',
  receiver_city varchar(10) DEFAULT NULL COMMENT '城市',
  receiver_district varchar(20) DEFAULT NULL COMMENT '区/县',
  receiver_address varchar(200) DEFAULT NULL COMMENT '收货地址，如：xx路xx号',
  receiver_zip varchar(6) DEFAULT NULL COMMENT '邮政编码,如：310001',
  created timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
  updated datetime default null comment '更新时间',
  PRIMARY KEY (order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单邮寄地址明细';



DROP TABLE IF EXISTS t_item_cat;
CREATE TABLE t_item_cat (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '类目ID',
  parent_id bigint(20) DEFAULT NULL COMMENT '父类目ID=0时，代表的是一级的类目',
  name varchar(50) DEFAULT NULL COMMENT '类目名称',
  status int(1) DEFAULT '1' COMMENT '状态。可选值:1(正常),2(删除)',
  sort_order int(4) DEFAULT NULL COMMENT '排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数',
  is_parent tinyint(1) DEFAULT '1' COMMENT '该类目是否为父类目，1为true，0为false',
  created timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
  updated datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (id),
  KEY parent_id (parent_id,status) USING BTREE,
  KEY sort_order (sort_order)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品类目';