create table IF NOT EXISTS homework.user (
    id bigint PRIMARY KEY not null,
    name varchar(16) not null,
    info varchar(256)
);