SELECT account();
SELECT esoda();
SELECT ekpompes();
SELECT expenses();
SELECT program_pr();
SELECT staff();
SELECT syntelestes();

SELECT dropdb();
SELECT dropekpompes();
SELECT createekpompes();
SELECT ekpompes();
select * from getekpompes();
select addbroadcast('gia sas','18','50');
select taskperiods();

    insert into TaskPeriods
    select
    1,'Task A', '20180110 10:00:00', '20180120 18:00:00'; 
    insert into TaskPeriods
    select
    2,'Task A', '20180115 05:00:00 ', '20180130 23:00:00';
select taskset();

select
 case when
  ('20180110 10:00:00' between t2.startDate and t2.endDate) or
  ('20180120 18:00:00' between t2.startDate and t2.endDate) or
  ('20180110 10:00:00' < t2.startDate and '20180120 18:00:00' > t2.endDate) or
  ('20180110 10:00:00' > t2.startDate and '20180120 18:00:00' < t2.endDate)
 then
  'yes'
 else
  'no'
 end as OverLapping
from TaskPeriods as t2
--getResult
select * from getResult(null,null,null,50,200);
select * from getResultSyntelestes(null,null,null,'Painter',null);
select deleteEkpompi(1);
select Distinct role from getSyntelestes();
select rating from pg_type where typname;
SELECT * FROM pg_type;
select pg_type(rating);
SELECT unnest(enum_range(NULL::rating)) 
SELECT enum_range(NULL::rating) 

drop table IF EXISTS syntelestesekp cascade;
    create table IF NOT EXISTS syntelestesekp
    (   
        sidek  integer,
        nameek varchar(20),
        sidsy integer,
        name varchar(20),
        surname varchar(20),
        role varchar(20),
        phoneNumber varchar(15),
        primary key(sidek,sidsy)
    );
INSERT INTO syntelestesekp (sidek,sidsy,name,surname,role,phoneNumber)
        VALUES (1,2,'Boba','The Builder','Builder','09099109346');
INSERT INTO syntelestesekp (sidek,sidsy,name,surname,role,phoneNumber)
        VALUES (1,3,'Boba','The Builder','Builder','09099109346');

INSERT INTO syntelestesekp (sidek,nameek,sidsy,name,surname,role,phoneNumber)
SELECT ekpompes.sid, ekpompes.name, syntelestes.sid, syntelestes.name, surname, role, phoneNumber
FROM ekpompes,syntelestes
where ekpompes.sid=1 and syntelestes.sid=1;

INSERT INTO syntelestesekp (sidek,nameek,sidsy,name,surname,role,phoneNumber)
SELECT ekpompes.sid, ekpompes.name, syntelestes.sid, syntelestes.name, surname, role, phoneNumber
FROM ekpompes,syntelestes
where ekpompes.sid=2 and syntelestes.sid=2 ;

INSERT INTO syntelestesekp (sidek,nameek,sidsy,name,surname,role,phoneNumber)
SELECT ekpompes.sid, ekpompes.name, syntelestes.sid, syntelestes.name, surname, role, phoneNumber
FROM ekpompes,syntelestes
where ekpompes.sid=2 and syntelestes.sid=1 ;


INSERT INTO syntelestesekp (sidek,nameek,sidsy,name,surname,role,phoneNumber)
SELECT ekpompes.sid, ekpompes.name, syntelestes.sid, syntelestes.name, surname, role, phoneNumber
FROM ekpompes,syntelestes
where ekpompes.sid=1 and syntelestes.sid=3 ;



Select * from table1 crossjoin table2;

INSERT INTO syntelestesekp (sidek,sidsy,name,surname,role,phoneNumber)
SELECT column1, column2, column3, ...
FROM table1
WHERE condition;

select * from syntelestesekp order by sidek,sidsy;

select addSyntelestesek(3,3);
select deleteEkpompi(6);