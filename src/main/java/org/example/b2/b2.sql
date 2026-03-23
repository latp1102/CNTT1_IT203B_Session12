create database b2;
use b2;

create table patients(
	patient_id int auto_increment primary key,
    temp double,
    heart_rate int
);
insert into patients value(1, 36.5, 70);