CREATE SCHEMA share_it;
SET SCHEMA 'share_it';

DROP DOMAIN IF EXISTS bla_bla_type;
CREATE DOMAIN bla_bla_type VARCHAR (1000000);

DROP TABLE IF EXISTS city;
CREATE TABLE city(
    name VARCHAR(100) PRIMARY KEY
);

DROP TABLE IF EXISTS category;
CREATE TABLE category(
    name VARCHAR (100) PRIMARY KEY
);

DROP TABLE IF EXISTS state;
CREATE TABLE state(
    name VARCHAR (100) PRIMARY KEY
);

DROP TABLE IF EXISTS member;
CREATE TABLE member(
    username VARCHAR (100) PRIMARY KEY,
    password VARCHAR (100),
    email_address VARCHAR (200),
    other_information bla_bla_type,
    address_street VARCHAR (100),
    address_no VARCHAR(100),
    address_postal_code int,
    address_city_name VARCHAR (100),
    FOREIGN KEY (address_city_name) REFERENCES city(name)
);

DROP TABLE IF EXISTS rating;
CREATE TABLE rating(
    value int CHECK ( value > 0 AND value < 6 ),
    commentary bla_bla_type,
    member_from VARCHAR (100),
    member_to VARCHAR (100),
    PRIMARY KEY (member_from, member_to),
    FOREIGN KEY (member_from) REFERENCES member(username),
    FOREIGN KEY (member_to) REFERENCES member(username)
);

DROP TABLE IF EXISTS message;
CREATE TABLE message(
    text bla_bla_type,
    time TIMESTAMP,
    member_from VARCHAR (100),
    member_to VARCHAR (100),
    PRIMARY KEY (member_from, member_to),
    FOREIGN KEY (member_from) REFERENCES member(username),
    FOREIGN KEY (member_to) REFERENCES member(username)
);

