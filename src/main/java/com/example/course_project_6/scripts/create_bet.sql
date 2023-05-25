create table if not exists bet
(
    id                bigserial
        primary key,
    description       varchar(255),
    header            varchar(255),
    short_description varchar(255),
    status            boolean,
    win_result        boolean
);