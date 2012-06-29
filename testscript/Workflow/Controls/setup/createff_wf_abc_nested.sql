CREATE TABLE dba.ff_wf_ac_b (
bid INT NOT NULL, 
bname VARCHAR(20) NULL, 
cid INT NULL, 
)
IN SYSTEM
;
ALTER TABLE dba.ff_wf_ac_b 
	ADD CONSTRAINT ASA148 PRIMARY KEY CLUSTERED (bid)
;


CREATE TABLE dba.ff_wf_c (
cid INT NOT NULL, 
cname VARCHAR(20) NULL, 
)
IN SYSTEM
;
ALTER TABLE dba.ff_wf_c 
	ADD CONSTRAINT ASA147 PRIMARY KEY CLUSTERED (cid)
;


insert into ff_wf_c (cid,cname) values(111,'cone') ;
insert into ff_wf_c (cid,cname) values(222,'ctwo') ;
insert into ff_wf_c (cid,cname) values(333,'cthree') ;


insert into ff_wf_ac_b (bid,bname,cid) values(2,'two',111) ;
insert into ff_wf_ac_b (bid,bname,cid) values(22,'twotwo',111) ;
insert into ff_wf_ac_b (bid,bname,cid) values(13,'onethree',222) ;

