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
ALTER TABLE dba.AllDT 
	ADD CONSTRAINT AllDT_8605270681 PRIMARY KEY CLUSTERED (int1)
;

insert into dba.AllDT values(1,'1','1',1,'2012-01-03','2012-01-03 00:00:00.0','12:25:13',1.000,1.0,1.0,0,'1',1);

insert into dba.AllDT values(2,'2','2',2,'2012-02-03','2012-02-03 00:00:00.0','02:02:13',2.000,2.0,2.0,0,'2',2);