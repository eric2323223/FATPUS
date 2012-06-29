/**
	To run this script successfully, please make sure 'Quoted identifer' option 
 	(Window->Preferences->Sybase,Inc->Database Development->Connection Level Options)
	is unchecked. 
*/ 
DROP TABLE IF EXISTS AllDT;
CREATE TABLE dba.AllDT (
int1 SMALLINT NOT NULL, 
string1 CHAR(5) NOT NULL, 
string2 VARCHAR(20) NOT NULL, 
long1 INT NOT NULL, 
date1 DATE NOT NULL, 
datetime1 datetime NOT NULL, 
time1 TIME NOT NULL, 
decimal1 NUMERIC(8 , 3) NOT NULL, 
float1 FLOAT(4) NOT NULL, 
double1 DOUBLE NOT NULL, 
bool1 BIT NOT NULL, 
byte1 CHAR(1) NULL, 
short1 SMALLINT NULL, 
)
IN SYSTEM
;
ALTER TABLE AllDT 
	ADD CONSTRAINT AllDT_8605270681 PRIMARY KEY CLUSTERED (int1)
;
