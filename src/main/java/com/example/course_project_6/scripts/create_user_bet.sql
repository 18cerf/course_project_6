create table if not exists userbet
(
    id         bigserial
        primary key,
    bet_sum    double precision,
    bet_id     bigint
        constraint fkn0888o5g6as14rdfq8u509evx
            references bet,
    user_id    bigint
        constraint fk7ep4tgmgqor1tlmmmfiucnocy
            references users,
    userbet_id bigint
        constraint fktl39fd50i8bmirki1mcrkpnnp
            references users
);