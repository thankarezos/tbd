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
    2,'Task A', '20180115 05:00:00', '20180130 23:00:00';
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


