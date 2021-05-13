SET SCHEMA 'share_it';

INSERT INTO category(name)
VALUES ('Kitchenware'),
       ('Computer hardware'),
       ('Medical equipment'),
       ('Fishing gear'),
       ('Office equipment'),
       ('Tools'),
       ('Furniture'),
       ('Sport equipment'),
       ('Musical instruments'),
       ('Boardgames'),
       ('Garden'),
       ('Other');

SET SCHEMA 'share_it';

INSERT INTO city (name)
VALUES('CopenHagen'), ('Aarhus'), ('Vejle'), ('Odense'), ('Aalborg'),
       ('Esbjerg'), ('Randers'), ('Kolding'), ('Horsens'), ('Roskilde');

INSERT INTO state VALUES ('New');
INSERT INTO state VALUES ('Like New');
INSERT INTO state VALUES ('Good');
INSERT INTO state VALUES ('Fair');
INSERT INTO state VALUES ('Poor');