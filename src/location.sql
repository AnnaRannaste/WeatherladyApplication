create table location (
id INT NOT NULL AUTO_INCREMENT,
/*date DATE (DEFAULT NULL),*/
city varchar(255) NOT NULL,
country varchar(255) NOT NULL,
region varchar(255) DEFAULT NULL,
latitude double DEFAULT NULL,
longitude double DEFAULT NULL,
temperature double DEFAULT NULL,
pressure double NOT NULL,
humidity double NOT NULL,
wind_direction varchar(255) DEFAULT NULL,
wind_speed double NOT NULL,
PRIMARY KEY (id)
);

