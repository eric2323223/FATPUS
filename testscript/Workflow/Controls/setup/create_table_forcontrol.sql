CREATE TABLE dba.forcontrol (
id INT NOT NULL, 
Type_STRING VARCHAR(400) NULL, 
Type_DATE DATE NULL, 
Type_TIME TIME NULL, 
Type_BOOLEAN BIT NOT NULL, 
)
IN SYSTEM
;
ALTER TABLE dba.forcontrol 
	ADD CONSTRAINT ASA80 PRIMARY KEY NONCLUSTERED (id)
;



insert into sampledb.dba.forcontrol 
 (id,Type_STRING,Type_DATE,Type_TIME,Type_BOOLEAN) 
values(8,'<form action="form_action.asp" method="get">First name: <input type="text" name="fname" /><br />Last name: <input type="text" name="lname" /><br /><input type="submit" value="Submit" /></form>','2011-10-01','21:10:00',1)


insert into sampledb.dba.forcontrol 
 (id,Type_STRING,Type_DATE,Type_TIME,Type_BOOLEAN) 
values(32,'ew','2011-02-03','21:00:00',0)


insert into sampledb.dba.forcontrol 
 (id,Type_STRING,Type_DATE,Type_TIME,Type_BOOLEAN) 
values(100,'-1x-1,30x34,48x34,-1x-1,48x34,-1x-1,49x35,43x52','2011-07-14','16:20:57',1)


insert into sampledb.dba.forcontrol 
 (id,Type_STRING,Type_DATE,Type_TIME,Type_BOOLEAN) 
values(10,'ooo','2011-08-31','07:34:10',1)


insert into sampledb.dba.forcontrol 
 (id,Type_STRING,Type_DATE,Type_TIME,Type_BOOLEAN) 
values(20,'ooo','2011-08-31','07:58:11',0)


insert into sampledb.dba.forcontrol 
 (id,Type_STRING,Type_DATE,Type_TIME,Type_BOOLEAN) 
values(6633,'expression111','2011-08-31','21:00:00',0)


insert into sampledb.dba.forcontrol 
 (id,Type_STRING,Type_DATE,Type_TIME,Type_BOOLEAN) 
values(3,'expression1','2011-08-30','17:23:28',0)


insert into sampledb.dba.forcontrol 
 (id,Type_STRING,Type_DATE,Type_TIME,Type_BOOLEAN) 
values(9,'6t','2011-08-30','17:40:20',1)


insert into sampledb.dba.forcontrol 
 (id,Type_STRING,Type_DATE,Type_TIME,Type_BOOLEAN) 
values(564,'expression1','2011-08-30','17:47:00',1)


insert into sampledb.dba.forcontrol 
 (id,Type_STRING,Type_DATE,Type_TIME,Type_BOOLEAN) 
values(5,'Tt','2011-08-31','21:34:11',0)


insert into sampledb.dba.forcontrol 
 (id,Type_STRING,Type_DATE,Type_TIME,Type_BOOLEAN) 
values(5323,'expression1','2011-02-28','21:00:00',1)


insert into sampledb.dba.forcontrol 
 (id,Type_STRING,Type_DATE,Type_TIME,Type_BOOLEAN) 
values(445,'expression1','2011-08-31','03:37:38',1)


;


