CREATE TABLE dba.OneAttribute (
id INT NOT NULL, 
)
IN SYSTEM
;
ALTER TABLE dba.OneAttribute 
	ADD CONSTRAINT ASA165 PRIMARY KEY CLUSTERED (id)
;



insert into dba.OneAttribute values(1);
insert into dba.OneAttribute values(2);
