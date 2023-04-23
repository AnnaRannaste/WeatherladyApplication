DROP TABLE IF EXISTS locations;

CREATE TABLE locations (
  id INT NOT NULL AUTO_INCREMENT,
  city VARCHAR(255) NOT NULL,
  longitude DOUBLE NOT NULL,
  latitude DOUBLE NOT NULL,
  region VARCHAR(255) NOT NULL,
  country VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);