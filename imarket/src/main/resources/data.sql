create table if not exists product (
    id int auto_increment primary key,
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