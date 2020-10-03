drop sequence if exists hibernate_sequence;
drop table if exists item;
drop table if exists shoppinglist;
drop table if exists shoppinglist_item;

create sequence hibernate_sequence start with 1 increment by 1;

create table item
(
    id          bigint       not null,
    name        varchar(100) not null,
    quantity    bigint       not null,
    measurement varchar(10)  not null,
    language    varchar(10)  not null,
    price_per_quantity   double not null,
    shop varchar(50) not null,
    notes varchar(100),
    currency varchar(10) not null

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

