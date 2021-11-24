drop database `addonis`;
create database if not exists `addonis`;

use `addonis`;


create table roles
(
    id        int auto_increment
        primary key,
    role_name varchar(30) not null
);


create table users
(
    id           int auto_increment
        primary key,
    username     varchar(20)          not null,
    password     varchar(68)          not null,
    first_name   varchar(30)          not null,
    last_name    varchar(30)          not null,
    email        varchar(30)          not null,
    picture_url  varchar(100)         null,
    blocked      tinyint(1) default 0 not null,
    role_id      int        default 2 not null,
    phone_number varchar(10)          not null,
    enabled      tinyint(1)           null,
    constraint users_email_uindex
        unique (email),
    constraint users_phone_number_uindex
        unique (phone_number),
    constraint users_username_uindex
        unique (username),
    constraint users_roles_id_fk
        foreign key (role_id) references roles (id)
);

create table verifications_token
(
    id      int auto_increment
        primary key,
    user_id int          null,
    name    varchar(100) null,
    constraint verifications_token_users_id_fk
        foreign key (user_id) references users (id)
);

create table tags
(
    id   int auto_increment
        primary key,
    name varchar(50) not null,
    constraint tags_name_uindex
        unique (name)
);


create table target_ide
(
    id   int auto_increment
        primary key,
    name varchar(50) not null,
    constraint target_ide_name_uindex
        unique (name)
);

-- auto-generated definition
create table addons
(
    id                  int auto_increment primary key,
    name                varchar(30)   not null,
    target_ide_id       int           not null,
    creator_id          int           not null,
    description         varchar(256)  not null,
    origin_url          varchar(256)  not null,
    binary_content_url  varchar(256)  not null,
    number_of_downloads int default 0 null,
    creation_date       date          not null,
    featured            tinyint(1)    not null,
    constraint addons_id_uindex
        unique (id),
    constraint addons_name_uindex
        unique (name),
    constraint addons_target_ide_id_fk
        foreign key (target_ide_id) references target_ide (id),
    constraint addons_users_id_fk
        foreign key (creator_id) references users (id)
);

create table addons_tags
(
    id       int auto_increment
        primary key,
    addon_id int not null,
    tag_id   int not null,
    constraint addons_tags_addons_id_fk
        foreign key (addon_id) references addons (id),
    constraint addons_tags_tags_id_fk
        foreign key (tag_id) references tags (id)
);

create table ratings
(
    id       int auto_increment
        primary key,
    user_id  int not null,
    addon_id int not null,
    rating   int not null,
    constraint ratings_addons_id_fk
        foreign key (addon_id) references addons (id),
    constraint ratings_users_id_fk
        foreign key (user_id) references users (id)
);



