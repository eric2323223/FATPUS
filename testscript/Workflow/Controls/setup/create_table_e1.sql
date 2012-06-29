CREATE TABLE dba.e1 (
emp_id INT NOT NULL, 
dept_id INT NOT NULL, 
)
IN SYSTEM
;
ALTER TABLE dba.e1 
	ADD CONSTRAINT ASA79 PRIMARY KEY NONCLUSTERED (emp_id)
;


insert into sampledb.dba.e1 
 (emp_id,dept_id) 
values(102,100)


insert into sampledb.dba.e1 
 (emp_id,dept_id) 
values(201,100)


insert into sampledb.dba.e1 
 (emp_id,dept_id) 
values(600,100)


;


