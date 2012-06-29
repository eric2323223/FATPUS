DROP TABLE IF EXISTS I18N;
CREATE TABLE dba.I18N (
name VARCHAR(50) NOT NULL, 
info VARCHAR(100) NULL, 
)
IN SYSTEM
;
ALTER TABLE dba.I18N 
	ADD CONSTRAINT ASA146 PRIMARY KEY CLUSTERED (name)
;

insert into sampledb.dba.I18N 
 (name,info) 
values('пуцШ','пео╒')


insert into sampledb.dba.I18N 
 (name,info) 
values('ут','ут')


;
