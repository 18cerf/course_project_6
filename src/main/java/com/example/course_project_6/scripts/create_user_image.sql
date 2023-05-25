create table if not exists user_image
(
    id         bigserial
        primary key,
    image_data oid,
    user_id    bigint
        constraint fko80y1v1f5vsfflp3jlax0x1c6
            references users
);