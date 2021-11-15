create table if not exists product (
    id long auto_increment primary key,
    name varchar(255) not null,
    brand varchar(255) not null,
    price float not null,
    quantity int not null,
    description varchar(255) not null,
    percent float not null,
    create datetime default null,
    update datetime default null,
    promotion boolean not null,
    category varchar(255) not null
);

drop table if exists user;
create table  user(
    id long auto_increment primary key,
    name varchar(255) not null,
    email varchar(255) not null,
    password varchar(255) not null,
    token varchar(255),
    acess boolean not null
);

drop table if exists user_login;
create table user_login(
    id long primary key,
    name varchar(255) not null,
    token varchar(255) not null,
    acess boolean not null
);

drop table if exists card;
create table card(
    id long primary key,
    banner varchar(255) not null,
    type varchar(255) not null,
    card_number varchar(255) not null,
    name varchar(255) not null,
    limite double not null,
    cvv varchar(255) not null
);

drop table if exists shop_cart_item;
create table shop_cart_item(
    id int auto_increment primary key,
    quantity int not null,
    price float not null
);

drop table if exists shop_cart;
create table shop_cart(
    id long primary key,
    total float not null,
    last_modification Datetime not null
);

