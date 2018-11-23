
-- create ride table
create table ride_tracker.ride (
	id int not null auto_increment,
	name varchar(100) not null,
	duration int not null,	
	constraint ride_pk primary key(id)
);

