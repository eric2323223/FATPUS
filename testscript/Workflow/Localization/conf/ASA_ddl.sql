/**
	To run this script successfully, please make sure 'Quoted identifer' option 
 	(Window->Preferences->Sybase,Inc->Database Development->Connection Level Options)
	is unchecked. 
*/ 
DROP TABLE IF EXISTS ASA;
CREATE TABLE dba.ASA (
a INT NOT NULL, 
b DOUBLE NULL, 
c FLOAT(4) NULL, 
)
IN SYSTEM
;
ALTER TABLE ASA 
	ADD CONSTRAINT ASA124 PRIMARY KEY CLUSTERED (a)
;
insert into sampledb.dba.ASA 
 (a,b,c) 
values(2,2.0,2.0)


insert into sampledb.dba.ASA 
 (a,b,c) 
values(1,1.0,1.0)


;