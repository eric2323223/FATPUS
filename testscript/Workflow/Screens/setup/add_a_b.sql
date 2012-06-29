CREATE TABLE dba.wf_ff_a (
aid INT NOT NULL, 
aname VARCHAR(20) NULL, 
)
IN SYSTEM
;
ALTER TABLE dba.wf_ff_a 
	ADD CONSTRAINT ASA162 PRIMARY KEY CLUSTERED (aid)
;

insert into dba.wf_ff_a values(1,'Aone') ;
insert into dba.wf_ff_a values(2,'Atwo') ;


CREATE TABLE dba.wf_ff_b (
bid INT NOT NULL, 
aid INT NOT NULL, 
bname VARCHAR(20) NULL, 
)
IN SYSTEM
;
ALTER TABLE dba.wf_ff_b 
	ADD CONSTRAINT ASA163 PRIMARY KEY CLUSTERED (bid)
;

insert into dba.wf_ff_b values(1,1,'Bone') ;
insert into dba.wf_ff_b values(2,2,'Btwo') ;