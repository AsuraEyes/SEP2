DROP SCHEMA IF EXISTS  share_it CASCADE ;
CREATE SCHEMA share_it;
SET SCHEMA 'share_it';

DROP DOMAIN IF EXISTS bla_bla_type CASCADE ;
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
    id SERIAL PRIMARY KEY,
    username VARCHAR (100) UNIQUE ,
    password VARCHAR (100),
    email_address VARCHAR (200),
    other_information bla_bla_type,
    address_street VARCHAR (100),
    address_no VARCHAR(100),
    address_postal_code int,
    address_city_name VARCHAR (100),
    average_review DECIMAL(2, 1),
    FOREIGN KEY (address_city_name) REFERENCES city(name)
);



DROP TABLE IF EXISTS rating;
CREATE TABLE rating(
    value int CHECK ( value > 0 AND value < 6 ),
    commentary bla_bla_type,
    member_from SERIAL,
    member_to SERIAL,
    PRIMARY KEY (member_from, member_to),
    FOREIGN KEY (member_from) REFERENCES member(id) ON DELETE CASCADE ,
    FOREIGN KEY (member_to) REFERENCES member(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS message;
CREATE TABLE message(
    text bla_bla_type,
    time TIMESTAMP,
    member_from SERIAL,
    member_to SERIAL,
    PRIMARY KEY (member_from, member_to),
    FOREIGN KEY (member_from) REFERENCES member(id) ON DELETE CASCADE ,
    FOREIGN KEY (member_to) REFERENCES member(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS rental;
CREATE TABLE rental(
    id SERIAL PRIMARY KEY ,
    name VARCHAR (200),
    description bla_bla_type,
    price int,
    otherInformation bla_bla_type,
    state_name VARCHAR (100),
    member_id SERIAL,
    FOREIGN KEY (state_name) REFERENCES state(name),
    FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS rental_category;
CREATE TABLE rental_category(
    rental_id SERIAL,
    category_name VARCHAR(100),
    PRIMARY KEY (rental_id, category_name),
    FOREIGN KEY (rental_id) REFERENCES rental (id) ON DELETE CASCADE ,
    FOREIGN KEY (category_name) REFERENCES category(name)
);

DROP TABLE IF EXISTS picture;
CREATE TABLE picture(
    link bla_bla_type PRIMARY KEY ,
    description bla_bla_type,
    rental_id SERIAL,
    FOREIGN KEY (rental_id) REFERENCES rental(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS report;
CREATE TABLE report(
    commentary bla_bla_type,
    member_from SERIAL,
    member_to SERIAL,
    PRIMARY KEY (member_from, member_to),
    FOREIGN KEY (member_from) REFERENCES member(id) ON DELETE CASCADE ,
    FOREIGN KEY (member_to) REFERENCES member(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS administrator;
CREATE TABLE administrator(
    username VARCHAR (100) PRIMARY KEY ,
    password VARCHAR (100)
);

DROP TABLE IF EXISTS warning;
CREATE TABLE warning(
    text bla_bla_type,
    time TIMESTAMP,
    member_from SERIAL,
    member_to SERIAL,
    PRIMARY KEY (member_from, member_to),
    FOREIGN KEY (member_from) REFERENCES member(id) ON DELETE CASCADE ,
    FOREIGN KEY (member_to) REFERENCES member(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS phone_no;
CREATE TABLE phone_no(
    country_code VARCHAR(50),
    number VARCHAR (50),
    member_id SERIAL,
    PRIMARY KEY (country_code, number),
    FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE CASCADE
);

CREATE OR REPLACE FUNCTION upd_average_review()
    RETURNS TRIGGER
    LANGUAGE plpgsql
AS
    $$
    DECLARE calculated_review DECIMAL(2,1);
        BEGIN
        SELECT AVG(value)
        INTO calculated_review
        FROM share_it.rating
        WHERE member_to = NEW.member_to;

        UPDATE share_it.member
        SET average_review = calculated_review
        WHERE id = NEW.member_to;

        RETURN NEW;
    END;
    $$;

CREATE TRIGGER update_avg_review_trigger
    AFTER INSERT OR UPDATE
    ON share_it.rating
    FOR EACH ROW
    EXECUTE FUNCTION upd_average_review();