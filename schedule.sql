create database scd;
show databases;
use scd;

create table user (
                      user_id bigint auto_increment primary key not null,
                      user_name varchar(4) not null,
                      user_email varchar(20) not null,
                      password varchar(10) not null,
                      created_at timestamp not null,
                      updated_at timestamp not null
);


create table schedule (
                          schedule_id bigint auto_increment primary key not null,
                          user_id bigint not null,
                          password varchar(10) not null,
                          title varchar(10) not null,
                          contents varchar(100),
                          created_date timestamp not null ,
                          updated_date timestamp not null ,
                          foreign key (user_id) references user (user_id)
);

create table reply (
                        reply_id bigint auto_increment primary key  not null,
                        schedule_id bigint not null,
                        user_id bigint not null,
                        contents varchar(100),
                        created_date timestamp not null ,
                        updated_date timestamp not null ,
                        foreign key (user_id) references user (user_id),
                        foreign key (schedule_id) references schedule (schedule_id)
);

show tables;
desc user;
desc schedule;
desc reply;