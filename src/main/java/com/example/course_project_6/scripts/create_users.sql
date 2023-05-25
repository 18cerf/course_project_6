create table if not exists users
(
    id            bigserial
        primary key,
    email         varchar(255),
    lastname      varchar(255),
    name          varchar(255),
    password      varchar(255),
    phone_number  varchar(255),
    username      varchar(255),
    user_image_id bigint
        constraint fksey36buii8br23hg6u0ixrcxs
            references user_image,
    wallet_id     bigint
        constraint fk2ndfo1foff7a36v7f6sst12ix
            references wallet
);