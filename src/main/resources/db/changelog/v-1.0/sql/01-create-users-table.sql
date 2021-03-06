create table users
(
    id                int8 generated by default as identity,
    email             varchar(255) not null,
    first_name        varchar(255) not null,
    last_name         varchar(255) not null,
    password          varchar(255) not null,
    username          varchar(255) not null,
    role              varchar(255) not null,
    status            varchar(255) not null,
    primary key (id)
);

alter table if exists users
    add constraint UK_6dotkott2kjsp8vw4d0m25fb7 unique (email);

alter table if exists users
    add constraint UK_r43af9ap4edm43mmtq01oddj6 unique (username);