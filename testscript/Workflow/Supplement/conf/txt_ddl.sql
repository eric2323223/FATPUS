/**
	To run this script successfully, please make sure 'Quoted identifer' option 
 	(Window->Preferences->Sybase,Inc->Database Development->Connection Level Options)
	is unchecked. 
*/ 
DROP TABLE IF EXISTS txt;
CREATE TABLE dba.txt (
id INT NOT NULL, 
content text NULL, 
)
IN SYSTEM
;
ALTER TABLE dba.txt 
	ADD CONSTRAINT ASA159 PRIMARY KEY CLUSTERED (id)
;
