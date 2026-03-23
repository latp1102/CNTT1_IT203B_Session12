create database b3;
use b3;
delimiter $$
create procedure getSur(
	in surgery_id int,
    out total_sur decimal(10,2)
) begin set total_sur = surgery_id * 10000;
end $$
delimiter ;