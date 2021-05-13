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

INSERT INTO rating
VALUES ('5','Amazing! I even got a coffee together with the tool i borrowed. I recommend!!!',1,2),
       ('3','If I could I would. Wether or not I should, I still would.',1,4),
       ('5','I recommend the person. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed ex nisi, tempus vel sem feugiat, mollis placerat dui. Maecenas elit orci, tincidunt sollicitudin consequat sed, elementum et massa. Donec lacinia sollicitudin felis, sed lobortis urna rutrum at. Donec dictum augue purus, sit amet ultricies erat mollis nec. Mauris egestas, lacus a lobortis feugiat, nunc sapien elementum metus, at posuere neque risus non lectus. Ut convallis turpis ante, eget convallis mi dapibus a. Sed magna mi, sagittis at dapibus ac, convallis sed leo.',2,3),
       ('4','',3,2),
       ('5','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi euismod massa a ex lobortis venenatis. Aenean rutrum enim ac lorem finibus, ut gravida nibh facilisis. Nam tortor lorem, rutrum eget tempor vel, luctus nec elit. Quisque pellentesque massa nulla, nec dapibus sem viverra ut. Maecenas a orci vel enim cursus volutpat ac at nisi. Vestibulum in suscipit arcu. Mauris scelerisque orci laoreet, dignissim ipsum nec, tempus massa. Maecenas orci dui, vestibulum ut nibh eu, viverra iaculis lectus. Morbi porttitor sem non sapien rhoncus, eget hendrerit dolor commodo. Nam in ligula a turpis auctor pretium ut a sem. Quisque tempus eleifend urna sit amet fermentum. Mauris bibendum lobortis efficitur. Mauris in scelerisque ex. Donec finibus efficitur nisi, in faucibus dui sagittis eu. Suspendisse quis enim eget tellus vulputate sagittis. Donec dictum vitae dui in faucibus.',5,6),
       ('4','',5,1),
       ('1','Never again.',5,7),
       ('5','Awesome person, awesome service!',6,1),
       ('2','Meh... ',6,3),
       ('3','',6,7),
       ('5','Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque malesuada turpis nulla, ut egestas turpis blandit quis. Praesent consectetur ac sem id ornare. Praesent volutpat, sapien ac pretium placerat, enim purus laoreet metus, a finibus dui sapien vel nibh. Aliquam a lobortis ante. Donec accumsan lobortis orci, vel bibendum tortor auctor in. Donec ac nulla urna. Curabitur accumsan tempus nibh, eget scelerisque ex eleifend nec. Vestibulum mollis pharetra facilisis. Suspendisse gravida arcu id sodales pretium. Nam sit amet consectetur orci, nec interdum lectus. Maecenas et arcu nisl. In ut justo malesuada, interdum sapien et, maximus tellus. Nullam sed libero sapien. Sed sit amet accumsan urna, finibus bibendum nunc. Quisque cursus dignissim sem, et pretium odio rhoncus ac. Phasellus laoreet, mauris ut varius volutpat, augue magna tempus quam, eget molestie leo massa a odio.',7,2),
       ('2','',7,4),
       ('4','',7,5);

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




