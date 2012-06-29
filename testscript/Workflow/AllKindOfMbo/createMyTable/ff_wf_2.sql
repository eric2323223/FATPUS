CREATE TABLE dba.ff_wf_threeLevel_a (
aid INT NOT NULL, 
aname VARCHAR(20) NULL, 
)
IN SYSTEM
;
ALTER TABLE dba.ff_wf_threeLevel_a 
	ADD CONSTRAINT ASA156 PRIMARY KEY CLUSTERED (aid)
;

insert into ff_wf_threeLevel_a (aid,aname) values(1,'aone') ;
insert into ff_wf_threeLevel_a (aid,aname) values(2,'atwo') ;


CREATE TABLE dba.ff_wf_threeLevel_b (
bid INT NOT NULL, 
bname VARCHAR(20) NULL, 
aid INT NULL, 
)
IN SYSTEM
;
ALTER TABLE dba.ff_wf_threeLevel_b 
	ADD CONSTRAINT ASA157 PRIMARY KEY CLUSTERED (bid)
;

insert into ff_wf_threeLevel_b (bid,bname,aid) values(22,'twotwo',1) ;
insert into ff_wf_threeLevel_b (bid,bname,aid) values(13,'onethree',2) ;


CREATE TABLE dba.ff_wf_threeLevel_c (
cid INT NOT NULL, 
cname VARCHAR(20) NULL, 
bid INT NULL, 
)
IN SYSTEM
;
ALTER TABLE dba.ff_wf_threeLevel_c 
	ADD CONSTRAINT ASA158 PRIMARY KEY CLUSTERED (cid)
;

insert into ff_wf_threeLevel_c (cid,bid,cname) values(111,22,'cone') ;
insert into ff_wf_threeLevel_c (cid,bid,cname) values(222,13,'ctwo') ;


