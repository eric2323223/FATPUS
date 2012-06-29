
CREATE TABLE dba.asdepartment (
dept_id INT NOT NULL, 
dept_name CHAR(40) NOT NULL, 
dept_head_id INT NULL, 
)
IN SYSTEM
;
ALTER TABLE dba.asdepartment 
	ADD CONSTRAINT ASA159 PRIMARY KEY CLUSTERED (dept_id)
;

insert into dba.asdepartment (dept_id,dept_name,dept_head_id) values(1,'my',905) ;
insert into dba.asdepartment (dept_id,dept_name,dept_head_id) values(2,'asaa',521) ;

insert into dba.department (dept_id,dept_name,dept_head_id) values(1,'ff',12) ;
insert into dba.department (dept_id,dept_name,dept_head_id) values(2,'aa',14) ;

