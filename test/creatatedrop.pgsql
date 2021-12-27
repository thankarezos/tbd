SELECT * from getByName('thanasis','karezos');
SELECT * from Syntelestes where name = 'thanasis' AND surname = 'karezos';

UPDATE ekpompes SET name = 'kalhnyxta' where sid = 3 order by sid;
Select * from ekpompes;
Select * from ekpompes order by sid;
Select * from getorder();

Select checkaccount('Thanasis', '1234');
Select checkaccount('thanasis', '1234');
Select * from checkaccount('Thanadsis', '1234');
Select * from checkaccount('Peris', '1234');