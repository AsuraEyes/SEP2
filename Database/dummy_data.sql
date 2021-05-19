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

INSERT INTO member (username, password, email_address, phone_number, other_information, address_street, address_no, address_postal_code, address_city_name)
VALUES ('bob','strongpassword1234','bobbobbinsky@via.dk', '26465768', 'retired janitor','Hulvej','19','8700','Horsens'),
       ('HappyGarry1','qwerty123','imoutofblood@gmail.com', '+45 76849212', 'doing transactions only on odd mondays','Sankt Annæ Pl.','26','1250','Copenhagen'),
       ('stillTommy','Tommy420','tommytommy@gmail.com',null,'preferred meeting in populated areas','Skelagervej','83','9000','Aalborg'),
       ('AlbertA','austriaisbestest','science@science.com', '+420 265476563', 'it would be nice if person that rent my belongs would be interested in science','Schleppegrellsgade','15','8000','Aarhus'),
       ('notaserialkiller','killkill','notaserialkiller@darknet.org','+48 512_113_543','there are no worries making trades with me as im not a serial killer','Skolegade','3B','7100','Vejle'),
       ('bonelessbanana','potasiuuum','yelowskins@fruit.com',null, 'certified banana','Holkebjergvej','87B','5250','Odense'),
       ('chesspawnplayer','checkmate123', null, '+48 564 354 43', 'moving only one step at a time','Svietorve','1','6000','Kolding'),
       ('cleaner','zxzxzx123','mrclean@rengoring.dk', '00 45 23 44 56 70', 'my rentals are always clean and in perfect stand','Hattemagervej','25','8920','Randers'),
       ('bmwdriver','noblinkers123','noseatbelts@bmw.dk','+49 345 675 643 93', 'please dont get scared of baseball bat in my car im a nice person','Københavnsvej','261','4000','Roskilde'),
       ('destroyer','destruction1212','canceleverything@crash.com', '+31 12 33 24 35', 'most of my items are in poor state and i usually destroy things i rent','Gudenåvej','20','6710','Esbjerg'),
       ('pizzamaker34','qweqwe23','myfavouritedishispizza@lidl.dk', '00 40 546235213', 'all my thing i rent are pizza related','Grønnegade','27','8700','Horsens'),
       ('progamerrr','steam221','warrior18@gamers.com', '+45 21376942', 'renting out my old computer gear :)','Hospitalsgade','11','8700','Horsens'),
       ('saveallthemoney','blingbling1','karen@gmail.com', '+45 34891032', 'i created account here just to save money renting from others','Flintebakken','150','8700','Horsens');

INSERT INTO rating
VALUES (5,'Amazing! I even got a coffee together with the tool i borrowed. I recommend!!!',12,2),
       (3,'If I could I would. Wether or not I should, I still would.',12,4),
       (5,'I recommend the person. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ex nisi, tempus vel sem feugiat, mollis placerat dui. Maecenas elit orci, tincidunt sollicitudin consequat sed, elementum et massa. Donec lacinia sollicitudin felis, sed lobortis urna rutrum at. Donec dictum augue purus, sit amet ultricies erat mollis nec. Mauris egestas, lacus a lobortis feugiat, nunc sapien elementum metus, at posuere neque risus non lectus. Ut convallis turpis ante, eget convallis mi dapibus a. Sed magna mi, sagittis at dapibus ac, convallis sed leo.',2,3),
       (4,'',3,2),
       (5,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi euismod massa a ex lobortis venenatis. Aenean rutrum enim ac lorem finibus, ut gravida nibh facilisis. Nam tortor lorem, rutrum eget tempor vel, luctus nec elit. Quisque pellentesque massa nulla, nec dapibus sem viverra ut. Maecenas a orci vel enim cursus volutpat ac at nisi. Vestibulum in suscipit arcu. Mauris scelerisque orci laoreet, dignissim ipsum nec, tempus massa. Maecenas orci dui, vestibulum ut nibh eu, viverra iaculis lectus. Morbi porttitor sem non sapien rhoncus, eget hendrerit dolor commodo. Nam in ligula a turpis auctor pretium ut a sem. Quisque tempus eleifend urna sit amet fermentum. Mauris bibendum lobortis efficitur. Mauris in scelerisque ex. Donec finibus efficitur nisi, in faucibus dui sagittis eu. Suspendisse quis enim eget tellus vulputate sagittis. Donec dictum vitae dui in faucibus.',5,6),
       (4,'',5,9),
       (4,'Never again.',5,7),
       (5,'Awesome person, awesome service!',6,10),
       (2,'Meh... ',6,3),
       (3,'',6,7),
       (5,'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque malesuada turpis nulla, ut egestas turpis blandit quis. Praesent consectetur ac sem id ornare. Praesent volutpat, sapien ac pretium placerat, enim purus laoreet metus, a finibus dui sapien vel nibh. Aliquam a lobortis ante. Donec accumsan lobortis orci, vel bibendum tortor auctor in. Donec ac nulla urna. Curabitur accumsan tempus nibh, eget scelerisque ex eleifend nec. Vestibulum mollis pharetra facilisis. Suspendisse gravida arcu id sodales pretium. Nam sit amet consectetur orci, nec interdum lectus. Maecenas et arcu nisl. In ut justo malesuada, interdum sapien et, maximus tellus. Nullam sed libero sapien. Sed sit amet accumsan urna, finibus bibendum nunc. Quisque cursus dignissim sem, et pretium odio rhoncus ac. Phasellus laoreet, mauris ut varius volutpat, augue magna tempus quam, eget molestie leo massa a odio.',7,2),
       (2,'',7,4),
       (4,'',7,5);

INSERT INTO administrator
VALUES ('administrator','123456');

INSERT INTO report
VALUES ('Donec ipsum velit, varius non egestas molestie, consectetur quis eros. Sed sed mauris eu enim tempor suscipit. Sed purus ligula, egestas nec nisl posuere, cursus pharetra diam. Maecenas finibus convallis diam sit amet gravida. Etiam non lacus tortor. Pellentesque a neque id sapien hendrerit suscipit sed ut libero. Donec volutpat faucibus magna nec tempor. Pellentesque commodo, orci ornare tempus pellentesque, sapien turpis volutpat arcu, et pharetra dui magna nec arcu. Suspendisse in dictum nisl. Nam ut lectus id tellus malesuada posuere a in eros. Ut tortor mi, maximus et ipsum eu, lobortis molestie quam. Integer dui enim, egestas nec velit ac, rhoncus posuere justo. Praesent lectus nibh, mattis hendrerit velit sed, aliquam efficitur augue.',1,7),
       ('Quisque eros arcu, sollicitudin nec erat in, maximus vehicula enim. Nunc auctor erat egestas nibh molestie, nec luctus tortor auctor. Sed libero felis, accumsan at orci quis, suscipit sodales odio. Sed pulvinar lorem vitae quam convallis tincidunt. Nunc ullamcorper condimentum gravida. Vivamus eget scelerisque orci. Praesent sed sollicitudin leo, non suscipit ligula. Sed posuere in arcu at vehicula. Praesent finibus nisl quis urna dapibus vestibulum quis quis augue.',2,7),
       ('This person was rude to me and it is not fair.',4,2),
       ('This user is not worthy of this community.',4,3),
       ('Delete this user!',4,5),
       ('Nullam suscipit metus at lectus auctor, vitae rhoncus est tempor. Sed sit amet enim tempus, blandit velit sit amet, cursus sem. Ut tristique lorem id erat consectetur malesuada. Pellentesque sit amet diam neque. Cras cursus laoreet rhoncus. Etiam quis mi vulputate, consequat augue ut, fringilla nisi. Proin rutrum tortor eu leo tempor, sed egestas enim consectetur. Donec quis sem elementum, finibus justo quis, tincidunt ligula. Vivamus ultrices erat rhoncus consequat eleifend. Aliquam malesuada lacus a lacus posuere dignissim. Vivamus quis nisi auctor, fringilla enim eget, suscipit arcu. Vestibulum et elit euismod, consectetur neque sed, finibus nisi. Sed nunc metus, vulputate sit amet tortor nec, pretium molestie ipsum. Vestibulum ligula felis, aliquet id turpis non, pulvinar consequat libero.',4,7),
       ('Sed ut vestibulum neque. Vivamus nec tempus ligula. Curabitur leo tortor, convallis at suscipit quis, pellentesque ut neque. Praesent a consectetur ligula. Maecenas condimentum enim ligula, non dictum est accumsan tincidunt. Aliquam ornare neque leo, vitae vulputate ipsum aliquet id. Vivamus feugiat, purus non maximus placerat, ex nisi lacinia neque, a fermentum velit arcu eget metus. Morbi ac sapien nisi. Ut ultrices elementum gravida. Aliquam pharetra turpis est, eget maximus est dictum in. In ut pharetra massa.',5,7),
       ('Vestibulum sit amet turpis non velit tincidunt consectetur. Cras tempus urna id sapien efficitur, in bibendum sem tincidunt. Proin viverra ultrices augue, in blandit tortor accumsan eget. Etiam non eros nec ante hendrerit accumsan non volutpat diam. Duis tempor ex in lorem placerat, sed venenatis nisi iaculis. Sed at erat eu lacus mattis tempor. Aenean ac libero mattis, aliquet leo quis, pharetra purus.',6,7);

INSERT INTO rental(name, picture_link, description, price, other_information, state_name, member_id)
VALUES ('Super duper chisels', 'x','This chisels are really amazing but sometimes does not work', 20, 'Not available for rent on Fridays', 'Good', 1),
       ('Even better drill','y', 'This drill is even more amazing and always works', 40, '', 'Like New', 5),
       ('Fishing rod','<' ,'Good for beginners to try out, not suitable for advanced fishers', 100, '', 'Fair', 3),
       ('Gardening hose','z', '', 35, 'I would very much prefer if you would only use it for water', 'Fair',  4),
       ('Monopoly', 'a','Great family fun for Friday evenings. Very fun!', 10, 'Not available at the weekend', 'Good', 5),
       ('Electric guitar Gibson', 'ao','1989, vibrato not working, strings are set high', 200, '', 'Like New', 5),
       ('Kitchen robot', 'q','Available with metal bowl and 3 hooks, 5 speeds, can also mince meat', 70, 'Missing simple hook', 'Good', 10);

INSERT INTO rental_category
VALUES (1, 'Tools'),
       (2, 'Tools'),
       (3, 'Fishing gear'),
       (4, 'Garden'),
       (5, 'Boardgames'),
       (6, 'Musical instruments'),
       (7, 'Kitchenware'),
       (7, 'Tools');

INSERT INTO message
VALUES ('Hi, is this product available', '2021-05-03 15:15:38', 7, 4),
       ('Hello, yes this product is currently available for rent', '2021-05-03 15:18:47', 6, 1),
       ('Great, would it be possible for us to meet somewhere?', '2021-05-03 15:33:09', 8, 4),
       ('You can swing by tomorrow from 1-3 in the afternoon', '2021-05-03 15:37:52', 3, 11),
       ('Great', '2021-05-03 15:38:06', 5, 13),
       ('Sorry, this product has just been rented', '2021-05-03 15:16:38', 4, 7),
       ('When can I come pick it up?', '2021-05-03 15:15:39', 12, 2);

INSERT INTO warning
VALUES ('This is a warning for your misconduct', '2021-05-03 15:15:38', 'administrator', 9),
       ('This is a warning for your misconduct', '2021-05-03 15:18:47', 'administrator', 1),
       ('This is a warning for your misconduct.', '2021-05-03 15:33:09', 'administrator', 3),
       ('This user is not worthy of this community.', '2021-05-03 15:38:06', 'administrator', 2),
       ('Your account is currently being suspended and put under investigation', '2021-05-03 15:37:52', 'administrator', 5),
       ('Your account shall be deleted in the next 24 hours, due to your repeated inability to stick to our community guidelines.', '2021-05-03 15:15:39', 'administrator', 7),
       ('Your account is currently being suspended and put under investigation', '2021-05-03 15:16:38', 'administrator', 4);

SELECT * FROM member;
INSERT INTO rating
VALUES (5, 'Good rating', 8, 5);

SELECT * FROM share_it.city