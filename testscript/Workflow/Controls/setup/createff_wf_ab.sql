CREATE TABLE dba.ff_wf_a (
aid INT NOT NULL, 
bid INT NOT NULL, 
)
IN SYSTEM
;
ALTER TABLE dba.ff_wf_a 
	ADD CONSTRAINT ASA145 PRIMARY KEY CLUSTERED (aid)
;

CREATE TABLE dba.ff_wf_b (
bid INT NOT NULL, 
bname VARCHAR(20) NULL, 
)
IN SYSTEM
;
ALTER TABLE dba.ff_wf_b 
	ADD CONSTRAINT ASA146 PRIMARY KEY CLUSTERED (bid)
;



insert into ff_wf_a (aid,bid) values(1,22) ;
insert into ff_wf_a (aid,bid) values(2,22) ;

insert into ff_wf_b (bid,bname) values(2,'two') ;
insert into ff_wf_b (bid,bname) values(11,'oneone') ;
insert into ff_wf_b (bid,bname) values(22,'twotwo') ;

