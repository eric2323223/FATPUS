CREATE TABLE dba.AllMBODataType (
id INT NOT NULL, 
Type_INT INT NULL, 
Type_LONG BIGINT NULL, 
Type_STRING VARCHAR(20) NULL, 
Type_DATE DATE NULL, 
Type_DATETIME datetime NULL, 
Type_TIME TIME NULL, 
Type_DECIMAL DECIMAL(8 , 3) NULL, 
Type_FLOAT FLOAT(4) NULL, 
Type_DOUBLE DOUBLE NULL, 
Type_BOOLEAN BIT NOT NULL, 
Type_BINARY VARBINARY(10) NULL, 
Type_multi VARCHAR(1000) NULL, 
)
IN SYSTEM
;
ALTER TABLE dba.AllMBODataType 
	ADD CONSTRAINT ASA80 PRIMARY KEY NONCLUSTERED (id)
;

INSERT INTO AllMBODataType(id, Type_INT, Type_LONG, Type_STRING, Type_DATE, Type_DATETIME, TYpe_TIME, Type_DECIMAL, Type_FLOAT, Type_DOUBLE, Type_BOOLEAN, Type_BINARY, Type_multi) VALUES (1, 1, 1, 'a', '2010-01-01', '2010-08-08 20:00:00.0', '20:00:00', '1.000', '1.0', '1.0', 1, NULL, 'a');

INSERT INTO AllMBODataType(id, Type_INT, Type_LONG, Type_STRING, Type_DATE, Type_DATETIME, TYpe_TIME, Type_DECIMAL, Type_FLOAT, Type_DOUBLE, Type_BOOLEAN, Type_BINARY, Type_multi) VALUES (2, 2, 2, 'b', '2011-08-08', '2009-06-06 21:09:00.0', '21:00:00', '2.000', '2.0', '2.0', 0, NULL, 'b');

INSERT INTO AllMBODataType(id, Type_INT, Type_LONG, Type_STRING, Type_DATE, Type_DATETIME, TYpe_TIME, Type_DECIMAL, Type_FLOAT, Type_DOUBLE, Type_BOOLEAN, Type_BINARY, Type_multi) VALUES (3, 3, 3, 'c', '2011-12-12', '2009-08-09 22:09:00.0', '21:00:00', '3.000', '3.0', '3.0', 0, NULL, 'c');

INSERT INTO AllMBODataType(id, Type_INT, Type_LONG, Type_STRING, Type_DATE, Type_DATETIME, TYpe_TIME, Type_DECIMAL, Type_FLOAT, Type_DOUBLE, Type_BOOLEAN, Type_BINARY, Type_multi) VALUES (4, 4, 4, 'd', '2011-09-09', '2010-06-06 21:09:00.0', '21:00:00', '4.000', '4.0', '4.0', 0, NULL, 'd');

