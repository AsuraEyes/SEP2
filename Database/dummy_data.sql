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
VALUES('Copenhagen'), ('Aarhus'), ('Vejle'), ('Odense'), ('Aalborg'),
       ('Esbjerg'), ('Randers'), ('Kolding'), ('Horsens'), ('Roskilde');

INSERT INTO state VALUES ('New');
INSERT INTO state VALUES ('Like New');
INSERT INTO state VALUES ('Good');
INSERT INTO state VALUES ('Fair');
INSERT INTO state VALUES ('Poor');

INSERT INTO member (username, password, email_address, other_information, address_street, address_no, address_postal_code, address_city_name)
VALUES ('bob','strongpassword1234','bobbobbinsky@via.dk','retired janitor','Hulvej','19','8700','Horsens'),
       ('HappyGarry1','qwerty123','imoutofblood@gmail.com','doing transactions only on odd mondays','Sankt Annæ Pl.','26','1250','CopenHagen'),
       ('stillTommy','Tommy420','tommytommy@gmail.com','preferred meeting in populated areas','Skelagervej','83','9000','Aalborg'),
       ('AlbertA','austriaisbestest','science@science.com','it would be nice if person that rent my belongs would be interested in science','Schleppegrellsgade','15','8000','Aarhus'),
       ('notaserialkiller','killkill','notaserialkiller@darknet.org','there are no worries making trades with me as im not a serial killer','Skolegade','3B','7100','Vejle'),
       ('bonelessbanana','potasiuuum','yelowskins@fruit.com','certified banana','Holkebjergvej','87B','5250','Odense'),
       ('chesspawnplayer','checkmate123','magnuscarlsen@chess.com','moving only one step at a time','Svietorve','1','6000','Kolding'),
       ('cleaner','zxzxzx123','mrclean@rengoring.dk','my rentals are always clean and in perfect stand','Hattemagervej','25','8920','Randers'),
       ('bmwdriver','noblinkers123','noseatbelts@bmw.dk','please dont get scared of baseball bat in my car im a nice person','Københavnsvej','261','4000','Roskilde'),
       ('destroyer','destruction1212','canceleverything@crash.com','most of my items are in poor state and i usually destroy things i rent','Gudenåvej','20','6710','Esbjerg'),
       ('pizzamaker34','qweqwe23','myfavouritedishispizza@lidl.dk','all my thing i rent are pizza related','Grønnegade','27','8700','Horsens'),
       ('progamerrr','steam221','warrior18@gamers.com','renting out my old computer gear :)','Hospitalsgade','11','8700','Horsens'),
       ('saveallthemoney','blingbling1','karen@gmail.com','i created account here just to save money renting from others','Flintebakken','150','8700','Horsens');