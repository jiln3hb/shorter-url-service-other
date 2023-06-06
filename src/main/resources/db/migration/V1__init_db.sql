create sequence url_seq start with 1 increment by 1;

create table url (
    id bigint not null,
    long_url varchar(255) not null,
    short_url varchar(5) not null,
    primary key (id)
);

