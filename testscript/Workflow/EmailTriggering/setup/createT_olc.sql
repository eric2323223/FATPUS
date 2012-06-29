CREATE TABLE dba.T_olc (
 a INT NOT NULL,
 b BIT NULL, 
c TIME NULL,
 d datetime NULL,
 ) 
IN SYSTEM ;
 ALTER TABLE dba.T_olc ADD CONSTRAINT ASA122 PRIMARY KEY CLUSTERED (a) ; 

insert into T_olc values(1,1,'10:30:30','2011-10-30 12:02:00.0') ;