create table t_item(
id bigint(10) not null auto_increment comment '商品id',
title varchar(100) not null comment '商品标题',
sell_point varchar(150) default null comment '商品卖点',
price bigint(20) not null comment '商品价格',
num int(10) not null comment '库存数量',
barcode varchar(30) default null comment '商品条形码',
image varchar(500) default null comment '商品图片',
cid bigint(10) not null comment '所属类目',
status tinyint not null default '1' comment '商品状态:1-正常,2-下架,3-删除',
created timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
updated datetime not null comment '更新时间',
primary key (id),
key cid (cid),
key status (status),
key updated (updated)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';


create table t_item_desc(
id bigint(10) default null comment '商品id',
item_desc text comment '商品描述',
created timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
updated datetime not null comment '更新时间',
key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品描述表';


create table t_item_param(
id bigint(10) not null auto_increment comment '商品规则参数id',
item_cid bigint(10) comment '商品所属类目id',
item_data text comment '商品json格式参数数据',
created datetime default null comment '创建时间',
updated datetime default null comment '更新时间',
primary key (id),
key (item_cid)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品规则参数表';


create table t_item_param_relation(
id bigint(10) not null auto_increment comment 'id',
item_id bigint(10) comment '商品id',
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
price bigint(20) not null comment '商品价格',
total_fee varchar(20) default null comment '电话号码',
pic_path varchar(50) default null comment '邮箱',
created timestamp not null default CURRENT_TIMESTAMP comment '创建时间',
updated datetime default null comment '更新时间',
key order_id (order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单商品表';
