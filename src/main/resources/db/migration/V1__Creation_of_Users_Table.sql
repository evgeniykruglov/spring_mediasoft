CREATE SCHEMA homework;

create table homework.users (
    uuid uuid PRIMARY KEY,
    name varchar(16) not null,
    info varchar(256)
);