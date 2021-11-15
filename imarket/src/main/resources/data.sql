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