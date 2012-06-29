CREATE TABLE dba.testtable (
a INT NOT NULL, 
b CHAR(5) NULL, 
c BIT NULL, 
)
IN SYSTEM
;
ALTER TABLE dba.testtable 
	ADD CONSTRAINT ASA161 PRIMARY KEY CLUSTERED (a)
;


insert into dba.testtable (a,b,c) values(1,'ai',0);
insert into dba.testtable (a,b,c) values(2,'bi',1);

