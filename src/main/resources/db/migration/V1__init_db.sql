create table url (
    id bigserial not null,
    long_url varchar(255) not null,
    short_url varchar(5) not null,
    primary key (id)
);