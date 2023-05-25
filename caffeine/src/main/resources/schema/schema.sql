CREATE TABLE city
(
    city_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name    VARCHAR(15)
) ENGINE = INNODB;

CREATE TABLE district
(
    district_id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(15),
    city_id     BIGINT NOT NULL,

    CONSTRAINT fk_city_city_id FOREIGN KEY (city_id) REFERENCES city (city_id)
) ENGINE = INNODB
