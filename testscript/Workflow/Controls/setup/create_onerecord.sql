CREATE TABLE dba.onerecord (
id INT NOT NULL, 
age INT NULL, 
name CHAR(5) NULL, 
)
IN SYSTEM
;
ALTER TABLE dba.onerecord 
	ADD CONSTRAINT ASA142 PRIMARY KEY CLUSTERED (id)
;


insert into onerecord (id,age,name) values(1,12,"ff") ;
insert into onerecord (id,age,name) values(2,21,"ffgg") ;