create table if not exists wallet
(
    id      bigserial
        primary key,
    balance double precision not null
);