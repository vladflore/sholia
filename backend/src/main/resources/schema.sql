create sequence hibernate_sequence start with 1 increment by 1;

create table item
(
    id          bigint       not null,
    name        varchar(100) not null,
    quantity    bigint       not null,
    measurement varchar(10)  not null,
    language    varchar(10)  not null
);


create table shoppinglist
(
    id   bigint       not null,
    name varchar(100) not null
);


create table shoppinglist_item
(
    shoppinglist_id bigint not null,
    item_id         bigint not null,
    primary key (shoppinglist_id, item_id)
);

