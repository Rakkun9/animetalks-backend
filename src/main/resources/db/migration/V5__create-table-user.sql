create table user (
                      id bigint not null auto_increment,
                      name varchar(100) not null,
                      password varchar(300) not null,

                      primary key (id)
);