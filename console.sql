create table if not exists category
(
    id   int auto_increment
        primary key,
    name varchar(255) not null
);

create table if not exists flower
(
    id          int auto_increment
        primary key,
    name        varchar(255) not null,
    detail      text         not null,
    image_url   varchar(255) not null,
    price       float        null,
    category_id int          not null,
    constraint flower_ibfk_1
        foreign key (category_id) references category (id)
);

create index category_id
    on flower (category_id);

create table if not exists orders
(
    id           int auto_increment
        primary key,
    total_price  float     default 0                 not null,
    f_and_l_name varchar(255)                        not null,
    phone_number varchar(255)                        not null,
    address      text                                not null,
    note         text                                null,
    order_date   timestamp default CURRENT_TIMESTAMP not null
);

create table if not exists order_detail
(
    order_id  int not null,
    flower_id int not null,
    quantity  int not null,
    primary key (order_id, flower_id),
    constraint order_detail_ibfk_1
        foreign key (order_id) references orders (id),
    constraint order_detail_ibfk_2
        foreign key (flower_id) references flower (id)
);

create index flower_id
    on order_detail (flower_id);

create definer = springstudent@localhost trigger calculate_total_price
    after insert
    on order_detail
    for each row
BEGIN
    DECLARE sum_price FLOAT;

    SELECT SUM(f.price * od.quantity) + 30000 INTO sum_price
    FROM flower f
    INNER JOIN order_detail od ON od.flower_id = f.id
    WHERE od.order_id = NEW.order_id;

    UPDATE `orders` SET total_price = sum_price
    WHERE id = NEW.order_id;
END;

create table if not exists role
(
    id   int auto_increment
        primary key,
    name varchar(255) null
);

create table if not exists user
(
    id         int auto_increment
        primary key,
    username   varchar(255) null,
    password   varchar(255) null,
    first_name varchar(255) null,
    last_name  varchar(255) null,
    email      varchar(255) null
);

create table if not exists users_roles
(
    user_id int not null,
    role_id int not null,
    primary key (user_id, role_id),
    constraint FK_ROLE
        foreign key (role_id) references role (id),
    constraint FK_USER_05
        foreign key (user_id) references user (id)
);

create index FK_ROLE_idx
    on users_roles (role_id);

