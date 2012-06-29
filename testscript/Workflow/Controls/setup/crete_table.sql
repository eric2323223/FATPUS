CREATE TABLE dba.T_olc (
 a INT NOT NULL,
 b BIT NULL, 
c TIME NULL,
 d datetime NULL,
 ) 
IN SYSTEM ;
 ALTER TABLE dba.T_olc ADD CONSTRAINT ASA122 PRIMARY KEY CLUSTERED (a) ; 

insert into T_olc values(1,1,'10:30:30','2011-10-30 12:02:00.0') ;


CREATE TABLE dba.myControls (

		id INT NOT NULL, 
		number INT NULL, 
		myname VARCHAR(20) NULL, 
		gender BIT NULL, 
		age INT NULL, 
		sign VARCHAR(400) NULL, 
		city VARCHAR(20) NULL, 
		company VARCHAR(20) NULL, 
		birth DATE NULL, 
		country VARCHAR(20) NULL, 
		mytime TIME NULL, 
		mypassword CHAR(5) NULL, 
		about XML NULL, 
		)
		IN SYSTEM
		;
		ALTER TABLE dba.myControls 
			ADD CONSTRAINT ASA144 PRIMARY KEY CLUSTERED (id)
		;


insert into myControls values(1,7,'sybase',1,2,'-1x-1,45x50,121x51,186x52,203x52,-1x-1,132x31,110x68,92x91,86x96,-1x-1,126x65,172x80,192x88,199x91','beijing','sybase',2000-12-19,'china','12:12:12','123','<b><i>sybase</i></b>') ;