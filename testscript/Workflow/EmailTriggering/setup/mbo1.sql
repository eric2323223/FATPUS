CREATE TABLE dba.mbo1 (
C1 INT NOT NULL, 
C2 VARCHAR(20) NOT NULL, 
)
IN SYSTEM
;
ALTER TABLE dba.mbo1 
	ADD CONSTRAINT ASA144 PRIMARY KEY NONCLUSTERED (C1)
;

insert into dba.mbo1 (C1,C2)values(1,'one');
insert into dba.mbo1 (C1,C2)values(2,'two');
