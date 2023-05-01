create table location (
id INT NOT NULL AUTO_INCREMENT,
date DATETIME,
city varchar(255) NOT NULL,
country varchar(255) NOT NULL,
region varchar(255) DEFAULT NULL,
latitude FLOAT,
longitude FLOAT,
temperature FLOAT,
pressure FLOAT,
humidity FLOAT,
wind_direction varchar(255) DEFAULT NULL,
wind_speed FLOAT,
PRIMARY KEY (id)
);

