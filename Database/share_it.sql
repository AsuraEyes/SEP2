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
    username VARCHAR (100) UNIQUE NOT NULL,
    password VARCHAR (100) NOT NULL,
    email_address VARCHAR (200),
    other_information bla_bla_type,
    address_street VARCHAR (100) NOT NULL,
    address_no VARCHAR(100) NOT NULL,
    address_postal_code int NOT NULL,
    address_city_name VARCHAR (100) NOT NULL,
    average_review DECIMAL(2, 1) DEFAULT 0 NOT NULL,
    FOREIGN KEY (address_city_name) REFERENCES city(name)
);



DROP TABLE IF EXISTS rating;
CREATE TABLE rating(
    value int CHECK ( value > 0 AND value < 6 ) NOT NULL,
    commentary bla_bla_type,
    member_from INTEGER NOT NULL,
    member_to INTEGER NOT NULL,
    PRIMARY KEY (member_from, member_to),
    FOREIGN KEY (member_from) REFERENCES member(id) ON DELETE CASCADE ,
    FOREIGN KEY (member_to) REFERENCES member(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS message;
CREATE TABLE message(
    text bla_bla_type NOT NULL,
    time TIMESTAMP NOT NULL,
    member_from INTEGER NOT NULL,
    member_to INTEGER NOT NULL,
    PRIMARY KEY (member_from, member_to, time),
    FOREIGN KEY (member_from) REFERENCES member(id) ON DELETE CASCADE ,
    FOREIGN KEY (member_to) REFERENCES member(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS rental;
CREATE TABLE rental(
    id SERIAL PRIMARY KEY ,
    name VARCHAR (200) NOT NULL,
    picture_link bla_bla_type UNIQUE,
    description bla_bla_type,
    price int NOT NULL NOT NULL,
    otherInformation bla_bla_type,
    state_name VARCHAR (100) NOT NULL,
    member_id INTEGER NOT NULL,
    FOREIGN KEY (state_name) REFERENCES state(name),
    FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS rental_category;
CREATE TABLE rental_category(
    rental_id INTEGER NOT NULL,
    category_name VARCHAR(100) NOT NULL,
    PRIMARY KEY (rental_id, category_name),
    FOREIGN KEY (rental_id) REFERENCES rental (id) ON DELETE CASCADE ,
    FOREIGN KEY (category_name) REFERENCES category(name)
);

DROP TABLE IF EXISTS report;
CREATE TABLE report(
    commentary bla_bla_type,
    member_from INTEGER NOT NULL,
    member_to INTEGER NOT NULL,
    PRIMARY KEY (member_from, member_to),
    FOREIGN KEY (member_from) REFERENCES member(id) ON DELETE CASCADE ,
    FOREIGN KEY (member_to) REFERENCES member(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS administrator;
CREATE TABLE administrator(
    username VARCHAR (100) PRIMARY KEY ,
    password VARCHAR (100) NOT NULL
);

DROP TABLE IF EXISTS warning;
CREATE TABLE warning(
    text bla_bla_type NOT NULL,
    time TIMESTAMP NOT NULL ,
    administrator_from VARCHAR(100) NOT NULL ,
    member_to INTEGER NOT NULL ,
    PRIMARY KEY (administrator_from, member_to, time),
    FOREIGN KEY (administrator_from) REFERENCES administrator(username) ON DELETE CASCADE ,
    FOREIGN KEY (member_to) REFERENCES member(id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS phone_no;
CREATE TABLE phone_no(
    number VARCHAR (50) NOT NULL ,
    member_id INTEGER NOT NULL ,
    PRIMARY KEY (number),
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