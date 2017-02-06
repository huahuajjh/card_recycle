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
    name VARCHAR(10) NULL COMMENT 'bank account name'
)ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='user info';

CREATE TABLE IF NOT EXISTS tb_admin(
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'primary key,auto increment',
    account VARCHAR(30) NOT NULL UNIQUE COMMENT 'account',
    pwd VARCHAR(64) NOT NULL DEFAULT 'abcd123' COMMENT 'admin password',
    token VARCHAR(32) NULL DEFAULT NULL COMMENT 'request token'
)ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='admin account info';

INSERT INTO tb_admin(account,pwd) VALUES('admin','abcd123');

CREATE TABLE IF NOT EXISTS tb_wallet(
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'primary key,auto increment',
    tb_user_id INT NOT NULL UNIQUE COMMENT 'wallet owner',
    balance DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT 'user wallet balance'
)ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='user wallt info';

CREATE TABLE IF NOT EXISTS tb_bank(
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'primary key,auto increment',
    name VARCHAR(30) NOT NULL UNIQUE COMMENT 'bank name'
)ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='bank type info';

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

CREATE TABLE IF NOT EXISTS tb_rechargeable_card_type_item(
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'primary key,auto increment',
    tb_rechargeable_card_type_id INT NOT NULL COMMENT 'car type id',
    support_amount DECIMAL NOT NULL COMMENT 'card support amount'
)ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='rechargeable card type detail info';

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
    order_time DATE NOT NULL DEFAULT NOW() COMMENT 'place a order time',
    order_number VARCHAR(64) NOT NULL UNIQUE COMMENT 'order number',
    order_status INT NOT NULL DEFAULT '0' COMMENT '0-processing,1-success,2-fail',
    process_time DATETIME NOT NULL COMMENT 'process order time',
    rechargeable_card_number VARCHAR(30) NOT NULL COMMENT 'rechargeable card number',
    actual_amount DECIMAL(10,2) NOT NULL COMMENT 'sale amount, amount=(card amount)*(sale ratio)',
    tb_rechargeable_card_id INT NOT NULL COMMENT 'card id',
    third_order_no VARCHAR(64) NULL COMMENT 'third order number',
    complete_time DATETIME NOT NULL COMMENT 'order completed time',
    third_msg VARCHAR(32) NULL COMMENT 'third api return message'
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
    LEFT JOIN tb_bank_account ba ON ba.tb_user_id = u.id
