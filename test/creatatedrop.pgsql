SELECT * FROM getByName('thanasis','karezos');
SELECT * FROM Syntelestes WHERE name = 'thanasis' AND surname = 'karezos';

UPDATE ekpompes SET name = 'kalhnyxta' WHERE sid = 3 ORDER BY sid;
SELECT * FROM ekpompes;
SELECT * FROM ekpompes ORDER BY sid;
SELECT * FROM getorder();

SELECT checkaccount('Thanasis', '1234');
SELECT checkaccount('thanasis', '1234');
SELECT * FROM checkaccount('Thanadsis', '1234');
SELECT * FROM checkaccount('Peris', '1234');