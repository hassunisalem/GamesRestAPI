DROP TABLE IF EXISTS game;

create table game
(
    id int,
    title varchar not null,
    release_year int not null,
    price int,
    broken tinyint,
    constraint game_pk
        primary key (id)
);