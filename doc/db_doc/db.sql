CREATE DATABASE IF NOT EXISTS care_recycle DEFAULT CHARACTER SET = utf8;
USE care_recycle;

CREATE TABLE IF NOT EXISTS tb_user(
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'primary key,auto increment',
    account VARCHAR(30) NOT NULL UNIQUE COMMENT 'account',
    pwd VARCHAR(64) NOT NULL COMMENT 'password',
    tel VARCHAR(11) NOT NULL UNIQUE COMMENT 'telephone',
    QQ VARCHAR(12) NULL UNIQUE COMMENT 'qq number',
    business_id VARCHAR(10) NOT NULL UNIQUE COMMENT 'business id',
    business_pwd VARCHAR(32) NOT NULL COMMENT 'business password'
)ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='user info';

CREATE TABLE IF NOT EXISTS tb_admin(
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'primary key,auto increment',
    account VARCHAR(30) NOT NULL UNIQUE COMMENT 'account',
    pwd VARCHAR(64) NOT NULL DEFAULT 'abcd123' COMMENT 'admin password'
)ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='admin account info';

CREATE TABLE IF NOT EXISTS tb_wallet(
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'primary key,auto increment',
    tb_user_id INT NOT NULL UNIQUE COMMENT 'wallet owner',
    balance DECIMAL(10,2) NOT NULL DEFAULT '0.00' COMMENT 'user wallet balance'
)ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='user wallt info';

CREATE TABLE IF NOT EXISTS tb_back(
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'primary key,auto increment',
    name VARCHAR(30) NOT NULL UNIQUE COMMENT 'back name'
)ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='back type info';

CREATE TABLE IF NOT EXISTS tb_back_account(
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'primary key,auto increment',
    card_number VARCHAR(30) NOT NULL UNIQUE COMMENT 'back card number',
    name VARCHAR(10) NOT NULL COMMENT 'back account name',
    withdraw_pwd VARCHAR(30) NOT NULL COMMENT 'account withdraw password',
    tb_user_id int NOT NULL COMMENT 'card owner id',
    back_name VARCHAR(30) NOT NULL COMMENT 'back type name',
    tb_back_id int NOT NULL COMMENT 'back id'
)ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='back card info';

CREATE TABLE IF NOT EXISTS tb_withdraw_record(
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'primary key,auto increment',
    withdraw_time DATETIME NOT NULL COMMENT 'withdraw time',
    back_name VARCHAR(30) NOT NULL COMMENT 'back name',
    tb_back_id int NOT NULL COMMENT 'back id',
    withdraw_amount DECIMAL(10,2) NOT NULL COMMENT 'withdraw amount',
    service_charge DECIMAL(3,2) NOT NULL DEFAULT '0.00' COMMENT 'service charge',
    actual_account_amount DECIMAL(10,2) NOT NULL COMMENT 'actual account amount',
    process_time DATETIME NOT NULL DEFAULT NOW() COMMENT 'process time',
    apply_time DATETIME NOT NULL DEFAULT NOW() COMMENT 'apply time',
    process_status INT NOT NULL DEFAULT '0' COMMENT 'process status,0-processing,1-success,2-fail',
    tb_user_id INT NOT NULL COMMENT 'withdraw user id'
)ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='withdraw info';

CREATE TABLE IF NOT EXISTS tb_rechargeable_card_type(
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'primary key,auto increment',
    name VARCHAR(30) NOT NULL UNIQUE COMMENT 'card name',
    card_shortcut VARCHAR(10) NOT NULL UNIQUE COMMENT 'card shortcut code'
)ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='rechargeable card type info';

CREATE TABLE IF NOT EXISTS tb_rechargeable_card_type_detail(
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'primary key,auto increment',
    tb_rechargeable_card_type_id INT NOT NULL COMMENT 'car type id',
    support_amount DECIMAL NOT NULL COMMENT 'card support amount',
    sale_ratio FLOAT(4,2) NOT NULL COMMENT 'sale ratio'
)ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='rechargeable card type detail info';

CREATE TABLE IF NOT EXISTS tb_rechargeable_card(
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'primary key,auto increment',
    card_number VARCHAR(30) NOT NULL COMMENT 'card number',
    card_pwd VARCHAR(30) NOT NULL COMMENT 'card password',
    tb_user_id INT NOT NULL COMMENT 'user id'
)ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='rechargeable card info';

CREATE TABLE IF NOT EXISTS tb_order(
    id INT PRIMARY KEY AUTO_INCREMENT COMMENT 'primary key,auto increment',
    tb_user_id INT NOT NULL COMMENT 'user id',
    tb_rechargeable_card_type_id INT NOT NULL COMMENT 'card type id',
    tb_rechargeable_card_type_item_id INT NOT NULL COMMENT 'card item id',
    order_time DATETIME NOT NULL DEFAULT NOW() COMMENT 'place a order time',
    order_number VARCHAR(64) NOT NULL UNIQUE COMMENT 'order number',
    order_status INT NOT NULL DEFAULT '0' COMMENT '0-processing,1-success,2-fail',
    process_time DATETIME NOT NULL COMMENT 'process order time',
    rechargeable_card_number VARCHAR(30) NOT NULL COMMENT 'rechargeable card number',
    actual_amount DECIMAL(10,2) NOT NULL COMMENT 'sale amount, amount=(card amount)*(sale ratio)',
    tb_rechargeable_card INT NOT NULL COMMENT 'card id',
    complete_time DATETIME NOT NULL COMMENT 'order completed time'
)ENGINE = InnoDB DEFAULT CHARSET=utf8 COMMENT='order info';