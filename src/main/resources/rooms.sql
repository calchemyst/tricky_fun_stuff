 INSERT INTO rooms ( name, type, north, south, east, west) VALUES (
 'home', 'farm', 'general store', 'cave', 'tavern', 'neighbor farm');
 INSERT INTO rooms ( name, type, north, south, east, west)  VALUES ( 'neighbor farm',
 'farm', 'apothecary', '', 'home', '');
 INSERT INTO rooms ( name, type, north, south, east, west)  VALUES ( 'tavern', 'inn', '',
 '', '', 'home');
 INSERT INTO rooms ( name, type, north, south, east, west)  VALUES ( 'apothecary', 'store', '',
  'neighbor farm', 'general store', '');
 INSERT INTO rooms ( name, type, north, south, east, west)  VALUES ( 'general store', 'store',
 '', 'home', '', 'apothecary');
 INSERT INTO rooms ( name, type, north, south, east, west)  VALUES ( 'cave', 'dungeon', 'home', '', '', '');