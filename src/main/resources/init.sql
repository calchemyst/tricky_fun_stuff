CREATE TABLE IF NOT EXISTS peons ( name varchar(50), health int(500), money int(10000), location varchar(50));
CREATE TABLE IF NOT EXISTS rooms ( name varchar(50), type varchar(50), north varchar(50),
                            south varchar(50), west varchar(50), east varchar(50));
CREATE TABLE IF NOT EXISTS crops (name varchar(50), health int(100));
CREATE TABLE IF NOT EXISTS weapons (name varchar(50), damage int(100));



