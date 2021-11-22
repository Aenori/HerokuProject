INSERT INTO cerebook_user(id, human_name, name, password, username)
VALUES(1,'James Logan Howlett','Wolverine',null,'wolverine');

INSERT INTO cerebook_user(id, human_name, name, password, username)
VALUES(2,'Jean Grey','Jean Grey',null,'jean_grey');

INSERT INTO cerebook_user(id, human_name, name, password, username)
VALUES(3,'Scott Summers','Cyclope',null,'cyclope');

-- The password is password --
INSERT INTO cerebook_user(id, human_name, name, password, username)
VALUES(4, 'Clark Kent', 'Superman', '$2a$11$Azmy0ET550TMI/ym0le0Rex0Oof3hQ./A8ECGBnSg9br.jn9ns15W', 'superman');

INSERT INTO cerebook_user_friends VALUES(1, 2), (3, 2);

INSERT INTO cerebook_message(id, content, author_id) VALUES(1, 'Hello world', 1);

SELECT nextval('hibernate_sequence');

-- H2 syntax, not the same on postgresql --
ALTER SEQUENCE hibernate_sequence RESTART WITH 100;
