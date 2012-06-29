CREATE TABLE dba.myemployee (
emp_id INT NOT NULL, 
manager_id INT NULL, 
emp_fname CHAR(20) NOT NULL, 
emp_lname CHAR(20) NOT NULL, 
dept_id INT NOT NULL, 
street CHAR(40) NOT NULL, 
city CHAR(20) NOT NULL, 
state CHAR(4) NOT NULL, 
zip_code CHAR(9) NOT NULL, 
phone CHAR(10) NULL, 
status CHAR(1) NULL, 
ss_number CHAR(11) NOT NULL, 
salary NUMERIC(20 , 3) NOT NULL, 
start_date DATE NOT NULL, 
termination_date DATE NULL, 
birth_date DATE NULL, 
bene_health_ins CHAR(1) NULL, 
bene_life_ins CHAR(1) NULL, 
bene_day_care CHAR(1) NULL, 
sex CHAR(1) NULL, 
)
IN SYSTEM
;
ALTER TABLE dba.myemployee 
	ADD CONSTRAINT ASA79 PRIMARY KEY NONCLUSTERED (emp_id)
;



insert into sampledb.dba.myemployee 
 (emp_id,manager_id,emp_fname,emp_lname,dept_id,street,city,state,zip_code,phone,status,ss_number,salary,start_date,termination_date,birth_date,bene_health_ins,bene_life_ins,bene_day_care,sex) 
values(102,501,'Fran                ','Whitney             ',100,'49 East Washington Street               ','Needham             ','MA  ','02192    ','6175553985','A','017349033  ',45700.000,'1987-02-26','2077-08-04','1959-06-05','Y','Y','N','F')


insert into sampledb.dba.myemployee 
 (emp_id,manager_id,emp_fname,emp_lname,dept_id,street,city,state,zip_code,phone,status,ss_number,salary,start_date,termination_date,birth_date,bene_health_ins,bene_life_ins,bene_day_care,sex) 
values(105,501,'Matthew             ','Cobb                ',100,'77 Pleasant Street                      ','Waltham             ','MA  ','02154    ','6175553840','A','052345739  ',62000.000,'1987-07-02','2077-08-04','1961-12-04','Y','Y','N','M')


insert into sampledb.dba.myemployee 
 (emp_id,manager_id,emp_fname,emp_lname,dept_id,street,city,state,zip_code,phone,status,ss_number,salary,start_date,termination_date,birth_date,bene_health_ins,bene_life_ins,bene_day_care,sex) 
values(129,902,'Philip              ','Chin                ',200,'59 Pond Street                          ','Atlanta             ','GA  ','30339    ','4045552341','A','024608923  ',38500.000,'1998-08-04','2077-08-04','1967-10-30','Y','Y','N','M')


insert into sampledb.dba.myemployee 
 (emp_id,manager_id,emp_fname,emp_lname,dept_id,street,city,state,zip_code,phone,status,ss_number,salary,start_date,termination_date,birth_date,bene_health_ins,bene_life_ins,bene_day_care,sex) 
values(148,1293,'Julie               ','Jordan              ',300,'144 Great Plain Avenue                  ','Winchester          ','MA  ','01890    ','6175557835','A','501704733  ',51432.000,'1997-10-04','2077-08-04','1952-12-13','Y','Y','N','F')


insert into sampledb.dba.myemployee 
 (emp_id,manager_id,emp_fname,emp_lname,dept_id,street,city,state,zip_code,phone,status,ss_number,salary,start_date,termination_date,birth_date,bene_health_ins,bene_life_ins,bene_day_care,sex) 
values(160,501,'Robert              ','Breault             ',100,'58 Cherry Street                        ','Milton              ','MA  ','02186    ','6175553099','A','025487623  ',57490.000,'1987-12-16','2077-08-04','1948-05-13','Y','Y','Y','M')


insert into sampledb.dba.myemployee 
 (emp_id,manager_id,emp_fname,emp_lname,dept_id,street,city,state,zip_code,phone,status,ss_number,salary,start_date,termination_date,birth_date,bene_health_ins,bene_life_ins,bene_day_care,sex) 
values(184,1576,'Melissa             ','Espinoza            ',400,'112 Apple Tree Way                      ','Stow                ','MA  ','01775    ','5085552319','A','025481943  ',36490.000,'1988-04-18','2077-08-04','1940-12-14','Y','Y','N','F')


insert into sampledb.dba.myemployee 
 (emp_id,manager_id,emp_fname,emp_lname,dept_id,street,city,state,zip_code,phone,status,ss_number,salary,start_date,termination_date,birth_date,bene_health_ins,bene_life_ins,bene_day_care,sex) 
values(191,703,'Jeannette           ','Bertrand            ',500,'209 Concord Street                      ','Acton               ','MA  ','01720    ','5085558138','A','017348821  ',32780.000,'1990-05-20','2077-08-04','1965-12-21','Y','Y','Y','F')


insert into sampledb.dba.myemployee 
 (emp_id,manager_id,emp_fname,emp_lname,dept_id,street,city,state,zip_code,phone,status,ss_number,salary,start_date,termination_date,birth_date,bene_health_ins,bene_life_ins,bene_day_care,sex) 
values(200,902,'Marc                ','Dill                ',200,'89 Hancock Street                       ','Milton              ','MA  ','02186    ','6175552144','A','079486634  ',54800.000,'1988-06-06','2077-08-04','1964-07-19','Y','Y','N','M')


insert into sampledb.dba.myemployee 
 (emp_id,manager_id,emp_fname,emp_lname,dept_id,street,city,state,zip_code,phone,status,ss_number,salary,start_date,termination_date,birth_date,bene_health_ins,bene_life_ins,bene_day_care,sex) 
values(207,1576,'Jane                ','Francis             ',400,'12 Hawthorne Drive                      ','Concord             ','MA  ','01742    ','5085559022','A','501708992  ',53870.000,'1995-08-04','2077-08-04','1972-09-12','Y','Y','N','F')


;


