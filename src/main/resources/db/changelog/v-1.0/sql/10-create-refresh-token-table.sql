create table refresh_token
(
    id int8 generated by default as identity,
    expiry_date timestamp not null,
    token varchar(255) not null,
    users_id int8,
    primary key (id)
);

alter table if exists refresh_token
    add constraint UK_r4k4edos30bx9neoq81mdvwph unique (token);

alter table if exists refresh_token
    add constraint FKsa85a9lsg3ldy1nkmvs9o5ium
        foreign key (users_id)
            references users;