drop table if exists bookings;
drop table if exists rooms;
drop table if exists profiles;
drop table if exists subscribings;

------------------------------
-- table: rooms             --
------------------------------
create table rooms
(
    id                  bigserial
        constraint rooms_pk
            primary key,
    room_name           text not null,
    capacity            int  not null,
    has_conditioner     boolean   default false,
    has_videoconference boolean   default false,
    create_date         timestamp default current_timestamp,
    update_date         timestamp,
    delete_date         timestamp,
    constraint rooms_uk unique (room_name, delete_date)
);

comment on table rooms is 'Переговорные комнаты';
comment on column rooms.room_name is 'Название комнаты';
comment on column rooms.capacity is 'Вместимость (кол-во человек)';
comment on column rooms.has_videoconference is 'Возможность проводить видеоконференции';
comment on column rooms.has_conditioner is 'Наличие кондиционера';

------------------------------
-- table: bookings          --
------------------------------
create table bookings
(
    id          bigserial
        constraint bookings_pk
            primary key,
    room_id     int       not null,
    login       text      not null,
    begin_date  timestamp not null,
    end_date    timestamp not null,
    create_date timestamp default current_timestamp,
    update_date timestamp,
    delete_date timestamp
);

comment on table bookings is 'Брони';
comment on column bookings.room_id is 'Идентификатор комнаты';
comment on column bookings.login is 'Логин пользователя';
comment on column bookings.begin_date is 'Дата и время начала брони';
comment on column bookings.end_date is 'Дата и время окончания брони';
comment on column bookings.create_date is 'Дата создания записи';
comment on column bookings.update_date is 'Дата обновления записи';
comment on column bookings.delete_date is 'Дата удаления записи (брони)';

alter table if exists bookings
    add constraint bookings_room_fk
        foreign key (room_id)
            references rooms;

------------------------------
-- table: profiles          --
------------------------------

create table profiles
(
    id              bigserial
        constraint profiles_pk
            primary key,
    login           text not null,
    email           text,
    mobile_phone    text,
    is_email_notify boolean default false,
    is_phone_notify boolean default false,
    constraint profiles_uk unique (login)
);

comment on table profiles is 'Профиль пользователя';
comment on column profiles.email is 'email пользователя';
comment on column profiles.mobile_phone is 'Номер сотового телефона пользователя';
comment on column profiles.is_email_notify is 'Оповещение по email';
comment on column profiles.is_phone_notify is 'Оповещение по sms';

------------------------------
-- table: subscriptions     --
------------------------------
create table subscriptions
(
    id          bigserial
        constraint subscriptions_pk
            primary key,
    room_id     int       not null,
    login       text      not null,
    create_date timestamp default current_timestamp,
    update_date timestamp,
    delete_date timestamp,
    constraint subscriptions_uk unique (room_id, login, delete_date)
);

comment on table subscriptions is 'Подписки';
comment on column subscriptions.room_id is 'Идентификатор комнаты';
comment on column subscriptions.login is 'Логин пользователя';
comment on column subscriptions.create_date is 'Дата создания записи';
comment on column subscriptions.update_date is 'Дата обновления записи';
comment on column subscriptions.delete_date is 'Дата удаления записи (подписки)';

alter table if exists subscribings
    add constraint subscribings_room_fk
        foreign key (room_id)
            references rooms;

alter sequence if exists rooms_id_seq restart with 10;
alter sequence if exists profiles_id_seq restart with 10;