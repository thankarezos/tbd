SELECT createdb();
SELECT dropdb();
SELECT createekpompes();
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