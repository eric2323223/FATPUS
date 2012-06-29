
CREATE TABLE dba.wfff (
dept_id INT NOT NULL, 
dept_decimal DECIMAL(8 , 3) NULL, 
dept_double DOUBLE NULL, 
dept_long BIGINT NULL, 
dept_binary BINARY(10) NULL, 
dept_float float NULL, 
)
IN SYSTEM
;
ALTER TABLE dba.wfff 
	ADD CONSTRAINT ASA137 PRIMARY KEY CLUSTERED (dept_id)
;

