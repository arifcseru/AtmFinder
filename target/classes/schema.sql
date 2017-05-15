CREATE TABLE IF NOT EXISTS `City` (
    `city_id` bigint(20) NOT NULL auto_increment,
    `city_name` varchar(200) NOT NULL,
    PRIMARY KEY (`city_id`)
);
CREATE TABLE IF NOT EXISTS `GeoLocation`(
    `geo_id` bigint(20) NOT NULL auto_increment,
    `lat` decimal(10,6) NOT NULL,
    `lng` decimal(10,6) NOT NULL,
    PRIMARY KEY (`geo_id`)
);

CREATE TABLE IF NOT EXISTS `Address` (
    `address_id` bigint(20) NOT NULL auto_increment, 
    `street` varchar(200) NOT NULL,
    `house_number` varchar(200) NOT NULL,
    `postal_code` varchar(100) NOT NULL,
    `city_id` bigint(20) NOT NULL,
    `geo_id` bigint(20) NOT NULL,
    PRIMARY KEY (`address_id`),
    FOREIGN KEY (`city_id`)  REFERENCES CITY(`city_id`),
    FOREIGN KEY (`geo_id`)  REFERENCES GEOLOCATION(`geo_id`)
);



CREATE TABLE IF NOT EXISTS  `Atm` 
( 
`atm_id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
`distance` BIGINT(20) NOT NULL,
`atm_type` VARCHAR(100) NOT NULL,
`address_id` BIGINT(20)  NOT NULL,
PRIMARY KEY (`atm_id`),
FOREIGN KEY (`address_id`) REFERENCES ADDRESS (`address_id`)
);

