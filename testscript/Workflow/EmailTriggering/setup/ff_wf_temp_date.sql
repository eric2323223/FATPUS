CREATE TABLE dba.ff_wf_temp_date (
dept_id INT NOT NULL, 
dept_date DATE NULL, 
)
IN SYSTEM
;
ALTER TABLE dba.ff_wf_temp_date 
	ADD CONSTRAINT ASA143 PRIMARY KEY CLUSTERED (dept_id)
;

INSERT INTO dba.ff_wf_temp_date values(1,'2011-10-01');