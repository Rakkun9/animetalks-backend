CREATE TABLE topic (
    id bigint not null auto_increment,
    tile varchar (100) not null,
    message varchar (1000) not null,
    createdDate varchar (100) not null,
    status varchar (100) not null,
    author varchar (100) not null,
    course varchar (100) not null,

    primary key (id)
);