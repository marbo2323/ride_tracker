
-- create ride table
create table ride_tracker.ride (
	id int not null auto_increment,
	name varchar(100) not null,
	duration int not null,	
	constraint ride_pk primary key(id)
);


-- add ride_date field
alter table ride add ride_date datetime after duration;
