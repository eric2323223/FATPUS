
CREATE TABLE dba.mydept (
dept_id INT NOT NULL, 
dept_name CHAR(40) NULL, 
dept_head_id INT NULL, 
)
IN SYSTEM
;
ALTER TABLE dba.mydept 
	ADD CONSTRAINT ASA143 PRIMARY KEY CLUSTERED (dept_id)
;



insert into dba.mydept values(100,"one",11);


insert into dba.mydept values(200,"two",22);