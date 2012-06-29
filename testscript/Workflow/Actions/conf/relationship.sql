/**
	To run this script successfully, please make sure 'Quoted identifer' option 
 	(Window->Preferences->Sybase,Inc->Database Development->Connection Level Options)
	is unchecked. 
*/ 
DROP TABLE IF EXISTS P;
DROP TABLE IF EXISTS C;
CREATE TABLE dba.P (
pid INT NOT NULL, 
pname VARCHAR(20) NULL, 
)
IN SYSTEM
;
CREATE TABLE dba.C (
cid INT NOT NULL, 
pid INT NULL, 
cname VARCHAR(20) NULL, 
)
IN SYSTEM
;
ALTER TABLE P 
	ADD CONSTRAINT ASA122 PRIMARY KEY CLUSTERED (pid)
;
ALTER TABLE C 
	ADD CONSTRAINT ASA123 PRIMARY KEY CLUSTERED (cid)
;
