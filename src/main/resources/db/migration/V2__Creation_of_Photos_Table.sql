create table IF NOT EXISTS homework.photo
(
    id      bigint PRIMARY KEY not null,
    path    varchar(16)        not null,
    user_id bigint
        CONSTRAINT fk_user
            REFERENCES homework.user (id)
);

