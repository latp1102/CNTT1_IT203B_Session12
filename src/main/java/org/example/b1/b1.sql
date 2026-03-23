create database b1;
use b1;

create table doctors(
	id int auto_increment primary key,
    code varchar(50),
    pass varchar(50)
);
insert into doctors(code, pass) values('doc01', '1234');