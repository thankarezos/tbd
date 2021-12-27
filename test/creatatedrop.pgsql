SELECT createdb();
SELECT dropdb();
SELECT dropekpompes();
SELECT createekpompes();
SELECT program_pr();
SELECT account();
select expenses();
select esoda();
SELECT * from getByName('thanasis','karezos');
SELECT * from Syntelestes where name = 'thanasis' AND surname = 'karezos';

INSERT INTO ekpompes (sid,name,rating)
    VALUES (1,'kalhmera','18+');
INSERT INTO ekpompes (sid,name,rating)
    VALUES (2,'kalhnyxta','18+');
INSERT INTO ekpompes (sid,name,rating)
    VALUES (3,'kalhspera','18+');    
UPDATE ekpompes SET name = 'kalhnyxta' where sid = 3 order by sid;
Select * from ekpompes;
Select * from ekpompes order by sid;
Select * from getorder();
INSERT INTO account (username,pass)
    VALUES ('Peris', '1234');
INSERT INTO account (username,pass)
    VALUES ('thanasis', '1234');
Select checkaccount('Thanasis', '1234');
Select checkaccount('thanasis', '1234');
Select * from checkaccount('Thanadsis', '1234');
Select * from checkaccount('Peris', '1234');