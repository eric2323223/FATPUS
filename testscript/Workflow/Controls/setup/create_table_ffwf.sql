CREATE TABLE dba.ff_wf_department (
dept_id INT NOT NULL, 
dept_name CHAR(5) NULL, 
dept_about XML NULL, 
dept_sign VARCHAR(400) NULL, 
)
IN SYSTEM
;
ALTER TABLE dba.ff_wf_department 
	ADD CONSTRAINT ASA137 PRIMARY KEY CLUSTERED (dept_id)
;


CREATE TABLE dba.ff_wf_employee (
emp_id INT NOT NULL, 
emp_name CHAR(5 CHAR) NULL, 
dept_id INT NOT NULL, 
emp_gender BIT NULL, 
emp_about XML NULL, 
emp_sign VARCHAR(400) NULL, 
)
IN SYSTEM
;
ALTER TABLE dba.ff_wf_employee 
	ADD CONSTRAINT ASA138 PRIMARY KEY CLUSTERED (emp_id)
;




insert into ff_wf_department values(1,'ff','<i><b>html</b></i>','sybase') ;
insert into ff_wf_department values(2,'sybas','<b><u>htmlhtml<u/></b>','sup') ;




insert into ff_wf_employee values(1,'emplo',1,1,'<i>html</i>','-1x-1,45x50,121x51,186x52,203x52,-1x-1,132x31,110x68,92x91,86x96,-1x-1,126x65,172x80,192x88,199x91') ;
insert into ff_wf_employee values(2,'sybas',2,0,'<b><u>html2</u></b>',null) ;