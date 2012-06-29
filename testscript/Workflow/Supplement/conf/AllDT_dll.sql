DROP TABLE IF EXISTS AllDT;
CREATE TABLE dba.AllDT (
int1 SMALLINT NOT NULL, 
string1 CHAR(5) NOT NULL, 
string2 VARCHAR(40) NOT NULL, 
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

insert into sampledb.dba.AllDT 
 (int1,string1,string2,long1,date1,datetime1,time1,decimal1,float1,double1,bool1,byte1,short1) 
values(1,'1    ','~!@#$%^&*()_+|}{:"?><,./;''[]\=-',1,'2011-10-27','2011-10-27 16:00:00.0','10:10:10',1.000,1.0,1.0,1,'1',1)


insert into sampledb.dba.AllDT 
 (int1,string1,string2,long1,date1,datetime1,time1,decimal1,float1,double1,bool1,byte1,short1) 
values(2,'2    ','Q\A',2,'2011-05-11','2011-06-06 10:00:00.0','15:56:56',1.230,123.123,100.1234,1,'s',3)


insert into sampledb.dba.AllDT 
 (int1,string1,string2,long1,date1,datetime1,time1,decimal1,float1,double1,bool1,byte1,short1) 
values(3,'3    ','Q/A',3,'2011-05-17','2011-06-06 09:00:00.0','15:04:34',0.000,0.0,0.0,0,'3',3)


;


