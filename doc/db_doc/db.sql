DROP DATABASE IF EXISTS card_recycle;

CREATE DATABASE IF NOT EXISTS card_recycle DEFAULT CHARACTER SET = utf8;
USE card_recycle;

CREATE TABLE IF NOT EXISTS tb_user(
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'primary key,auto increment',
    account VARCHAR(30) NOT NULL UNIQUE COMMENT 'account',
    pwd VARCHAR(64) NOT NULL COMMENT 'password',
    tel VARCHAR(11) NOT NULL UNIQUE COMMENT 'telephone',
    qq VARCHAR(12) NULL UNIQUE COMMENT 'qq number',
    business_id VARCHAR(64) NOT NULL UNIQUE COMMENT 'business id',
    business_pwd VARCHAR(64) NOT NULL COMMENT 'business password',
    token VARCHAR(32) NULL DEFAULT NULL COMMENT 'request token',
    withdraw_pwd VARCHAR(64) NULL COMMENT 'account withdraw password',
    id_card_num VARCHAR(20) NULL COMMENT 'id card number',
    name VARCHAR(10) NULL COMMENT 'bank account name',
    last_login_time DATETIME NULL COMMENT 'last login time'
)ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='user info';

INSERT INTO tb_user(account,pwd,tel,qq,business_id,business_pwd,token,withdraw_pwd,id_card_num,name,last_login_time)
    VALUES('test@test.com','58334dcc9c656c9e8b56910466fe5614','15928981624','703825021','58334dcc9c656c9e8b56910466fe5614','58334dcc9c656c9e8b56910466fe5614',NULL,'58334dcc9c656c9e8b56910466fe5614','511527198909130638','贾松','2017-01-01 12:12:12');

CREATE TABLE IF NOT EXISTS tb_admin(
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'primary key,auto increment',
    account VARCHAR(30) NOT NULL UNIQUE COMMENT 'account',
    pwd VARCHAR(64) NOT NULL DEFAULT 'abcd123' COMMENT 'admin password',
    token VARCHAR(32) NULL DEFAULT NULL COMMENT 'request token',
    last_login_time DATETIME NULL COMMENT 'last login time'
)ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='admin account info';

INSERT INTO tb_admin(account,pwd,last_login_time) VALUES('admin','58334dcc9c656c9e8b56910466fe5614','2017-01-01 12:12:12');

CREATE TABLE IF NOT EXISTS tb_wallet(
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'primary key,auto increment',
    tb_user_id INT NOT NULL UNIQUE COMMENT 'wallet owner',
    balance DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT 'user wallet balance'
)ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='user wallt info';

CREATE TABLE IF NOT EXISTS tb_bank(
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'primary key,auto increment',
    name VARCHAR(30) NOT NULL UNIQUE COMMENT 'bank name'
)ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='bank type info';

INSERT INTO tb_bank(name) VALUES('招商银行'),('中国农业银行'),('中国建设银行'),('中国工商银行'),('中国银行'),('广发银行'),('中国交通银行');

CREATE TABLE IF NOT EXISTS tb_bank_account(
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'primary key,auto increment',
    card_number VARCHAR(30) NULL UNIQUE COMMENT 'bank card number',
    tb_user_id INT NOT NULL COMMENT 'card owner id',
    bank_name VARCHAR(30) NULL COMMENT 'bank type name',
    tb_bank_id INT NULL COMMENT 'bank id',
    name VARCHAR(20) NOT NULL COMMENT 'bank account name'
)ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='bank card info';

CREATE TABLE IF NOT EXISTS tb_withdraw_record(
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'primary key,auto increment',
    withdraw_time DATETIME NOT NULL DEFAULT '1990-01-01' COMMENT 'withdraw time',
    bank_name VARCHAR(30) NOT NULL COMMENT 'bank name',
    tb_bank_id int NOT NULL COMMENT 'bank id',
    withdraw_amount DECIMAL(10,2) NOT NULL COMMENT 'withdraw amount',
    service_charge DECIMAL(3,2) NOT NULL DEFAULT '0.00' COMMENT 'service charge',
    actual_account_amount DECIMAL(10,2) NOT NULL COMMENT 'actual account amount',
    process_time DATETIME NOT NULL DEFAULT NOW() COMMENT 'process time',
    apply_time DATETIME NOT NULL DEFAULT NOW() COMMENT 'apply time',
    process_status INT NOT NULL DEFAULT '0' COMMENT 'process status,0-processing,1-success,2-fail',
    tb_user_id INT NOT NULL COMMENT 'withdraw user id',
    msg VARCHAR(32) NULL COMMENT 'message after deal with withdraw',
    process_msg VARCHAR(32) NULL COMMENT 'background processing message',
    bank_card_num VARCHAR(20) NOT NULL COMMENT 'bank account card number'
)ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='withdraw info';

CREATE TABLE IF NOT EXISTS tb_rechargeable_card_type(
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'primary key,auto increment',
    name VARCHAR(30) NOT NULL UNIQUE COMMENT 'card name',
    card_shortcut VARCHAR(10) NOT NULL UNIQUE COMMENT 'card shortcut code',
    sale_ratio FLOAT(4,3) NOT NULL COMMENT 'sale ratio'
)ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='rechargeable card type info';

INSERT INTO tb_rechargeable_card_type(name,card_shortcut,sale_ratio) VALUES('神州行充值卡','SZX','0.800');
INSERT INTO tb_rechargeable_card_type(name,card_shortcut,sale_ratio) VALUES('联通充值卡','LT','0.800');
INSERT INTO tb_rechargeable_card_type(name,card_shortcut,sale_ratio) VALUES('电信充值卡','DX','0.800');
INSERT INTO tb_rechargeable_card_type(name,card_shortcut,sale_ratio) VALUES('盛大一卡通','SD','0.800');
INSERT INTO tb_rechargeable_card_type(name,card_shortcut,sale_ratio) VALUES('骏网一卡通','JW','0.800');
INSERT INTO tb_rechargeable_card_type(name,card_shortcut,sale_ratio) VALUES('搜狐一卡通','SH','0.800');
INSERT INTO tb_rechargeable_card_type(name,card_shortcut,sale_ratio) VALUES('网易一卡通','WY','0.800');
INSERT INTO tb_rechargeable_card_type(name,card_shortcut,sale_ratio) VALUES('完美一卡通','WM','0.800');
INSERT INTO tb_rechargeable_card_type(name,card_shortcut,sale_ratio) VALUES('盛付通','SFT','0.800');
INSERT INTO tb_rechargeable_card_type(name,card_shortcut,sale_ratio) VALUES('征途一卡通','ZT','0.800');
INSERT INTO tb_rechargeable_card_type(name,card_shortcut,sale_ratio) VALUES('久游一卡通','JY','0.800');
INSERT INTO tb_rechargeable_card_type(name,card_shortcut,sale_ratio) VALUES('Q币一卡通','QQ','0.800');
INSERT INTO tb_rechargeable_card_type(name,card_shortcut,sale_ratio) VALUES('纵游一卡通','ZY','0.800');

CREATE TABLE IF NOT EXISTS tb_rechargeable_card_type_item(
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'primary key,auto increment',
    tb_rechargeable_card_type_id INT NOT NULL COMMENT 'car type id',
    support_amount DECIMAL NOT NULL COMMENT 'card support amount'
)ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='rechargeable card type detail info';

INSERT INTO tb_rechargeable_card_type_item(tb_rechargeable_card_type_id,support_amount)
    VALUES(1,'10.00'),(1,'30.00'),(1,'50.00'),(1,'100.00'),(1,'500.00'),
          (2,'10.00'),(2,'30.00'),(2,'50.00'),(2,'100.00'),(2,'500.00'),
          (3,'10.00'),(3,'30.00'),(3,'50.00'),(3,'100.00'),(3,'500.00'),
          (4,'10.00'),(4,'30.00'),(4,'50.00'),(4,'100.00'),(4,'500.00'),
          (5,'10.00'),(5,'30.00'),(5,'50.00'),(5,'100.00'),(5,'500.00'),
          (6,'10.00'),(6,'30.00'),(6,'50.00'),(6,'100.00'),(6,'500.00'),
          (7,'10.00'),(7,'30.00'),(7,'50.00'),(7,'100.00'),(7,'500.00'),
          (8,'10.00'),(8,'30.00'),(8,'50.00'),(8,'100.00'),(8,'500.00'),
          (9,'10.00'),(9,'30.00'),(9,'50.00'),(9,'100.00'),(9,'500.00'),
          (10,'10.00'),(10,'30.00'),(10,'50.00'),(10,'100.00'),(10,'500.00'),
          (11,'10.00'),(11,'30.00'),(11,'50.00'),(11,'100.00'),(11,'500.00'),
          (12,'10.00'),(12,'30.00'),(12,'50.00'),(12,'100.00'),(12,'500.00'),
          (13,'10.00'),(13,'30.00'),(13,'50.00'),(13,'100.00'),(13,'500.00');

CREATE TABLE IF NOT EXISTS tb_rechargeable_card(
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'primary key,auto increment',
    card_number VARCHAR(30) NOT NULL COMMENT 'card number',
    card_pwd VARCHAR(64) NOT NULL COMMENT 'card password',
    tb_rechargeable_card_type_id INT NOT NULL COMMENT 'card type id',
    tb_rechargeable_card_type_item_id INT NOT NULL COMMENT 'card item id',
    tb_user_id INT NOT NULL COMMENT 'user id',
    sale_ratio FLOAT(4,3) NOT NULL COMMENT 'sale ratio',
    support_amount DECIMAL NOT NULL COMMENT 'card support amount'
)ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='rechargeable card info';

CREATE TABLE IF NOT EXISTS tb_order(
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'primary key,auto increment',
    tb_user_id INT NOT NULL COMMENT 'user id',
    tb_rechargeable_card_type_id INT NOT NULL COMMENT 'card type id',
    tb_rechargeable_card_type_item_id INT NOT NULL COMMENT 'card item id',
    order_time DATETIME NOT NULL COMMENT 'place a order time',
    order_number VARCHAR(64) NOT NULL UNIQUE COMMENT 'order number',
    order_status INT NOT NULL DEFAULT '0' COMMENT '0-processing,1-success,2-fail',
    process_time DATETIME NOT NULL COMMENT 'process order time',
    rechargeable_card_number VARCHAR(30) NOT NULL COMMENT 'rechargeable card number',
    actual_amount DECIMAL(10,2) NOT NULL COMMENT 'sale amount, amount=(card amount)*(sale ratio)',
    tb_rechargeable_card_id INT NOT NULL COMMENT 'card id',
    third_order_no VARCHAR(64) NULL COMMENT 'third order number',
    complete_time DATETIME NOT NULL COMMENT 'order completed time',
    third_msg VARCHAR(255) NULL COMMENT 'third api return message'
)ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='order info';

CREATE VIEW v_order AS
    SELECT
        o.id,
        o.order_number,
        o.order_time,
        o.order_status,
        o.process_time,
        u.account,
        o.actual_amount,
        o.third_msg,
        u.name,
        u.tel,
        u.id as tb_user_id,
        u.id_card_num,
        cti.support_amount,
        c.card_number,
        ct.sale_ratio,
        ct.name as cardTypeName,
        o.tb_rechargeable_card_type_id,
        o.tb_rechargeable_card_type_item_id,
        o.tb_rechargeable_card_id
    FROM tb_order o
    LEFT JOIN tb_user u ON o.tb_user_id = u.id
    LEFT JOIN tb_rechargeable_card c ON c.id = o.tb_rechargeable_card_id
    LEFT JOIN tb_rechargeable_card_type ct ON ct.id = o.tb_rechargeable_card_type_id
    LEFT JOIN tb_rechargeable_card_type_item cti ON cti.id = o.tb_rechargeable_card_type_item_id;

CREATE VIEW v_withdraw_record AS
    SELECT
        w.id,
        u.business_id,
        u.name,
        w.withdraw_amount,
        wl.balance,
        w.process_status,
        w.process_time,
        ba.card_number,
        u.account,
        w.apply_time,
        w.service_charge,
        w.actual_account_amount,
        w.msg,
        w.bank_name
    FROM tb_withdraw_record w
    LEFT JOIN tb_user u ON u.id = w.tb_user_id
    LEFT JOIN tb_bank b ON b.id = w.tb_bank_id
    LEFT JOIN tb_wallet wl ON wl.tb_user_id = u.id
    LEFT JOIN tb_bank_account ba ON ba.tb_user_id = u.id;

CREATE VIEW v_card_type_and_items AS
    SELECT
        t.name,
        t.sale_ratio,
        (SELECT group_concat(i.support_amount) FROM tb_rechargeable_card_type_item i WHERE i.tb_rechargeable_card_type_id = t.id) as amounts,
        t.card_shortcut
    FROM tb_rechargeable_card_type t;

CREATE VIEW v_admin_overview AS
    SELECT
        (SELECT COUNT(1) FROM tb_order o ) AS totalOrder,
        (SELECT COUNT(1) FROM tb_order o WHERE o.order_status=1) AS successCount,
        (SELECT COUNT(1) FROM tb_order o WHERE o.order_status=2) AS failCount,
        (SELECT COUNT(1) FROM tb_order o WHERE o.order_status=0) AS processingCount,
        (SELECT COUNT(1) FROM tb_user u) AS totalUser,
        (SELECT COUNT(1) FROM tb_withdraw_record wr) AS withdrawCount;