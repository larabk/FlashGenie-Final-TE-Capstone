BEGIN TRANSACTION;

DROP TABLE IF EXISTS cards, decks, users;


DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;



CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) UNIQUE NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE decks (
	deck_id serial NOT NULL,
	user_id int NOT NULL,
	deck_name varchar(50) NOT NULL,
	click_count int DEFAULT 0,
	topics varchar(50) NOT NULL,
	CONSTRAINT PK_deck PRIMARY KEY (deck_id),
	CONSTRAINT FK_deck_user FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE cards (
	card_id serial NOT NULL,
	deck_id int NOT NULL,
	front_text varchar(500) NOT NULL,
	back_text varchar(500) NOT NULL,
	keywords varchar(50) NOT NULL,
	CONSTRAINT PK_card PRIMARY KEY (card_id),
	CONSTRAINT FK_deck FOREIGN KEY(deck_id) REFERENCES decks(deck_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');


--- USER SETUP (Do Not Modify)
DROP USER IF EXISTS final_capstone_owner;
DROP USER IF EXISTS final_capstone_appuser;

CREATE USER final_capstone_owner
WITH PASSWORD 'finalcapstone';

GRANT ALL
ON ALL TABLES IN SCHEMA public
TO final_capstone_owner;

GRANT ALL
ON ALL SEQUENCES IN SCHEMA public
TO final_capstone_owner;

CREATE USER final_capstone_appuser
WITH PASSWORD 'finalcapstone';

GRANT SELECT, INSERT, UPDATE, DELETE
ON ALL TABLES IN SCHEMA public
TO final_capstone_appuser;

GRANT USAGE, SELECT
ON ALL SEQUENCES IN SCHEMA public
TO final_capstone_appuser;

--User setup above this line 

INSERT INTO decks (user_id, deck_name, topics)
VALUES (1, 'Cars', 'Vehicles');


INSERT INTO cards (deck_id, front_text, back_text, keywords)
VALUES(1, 'Ford Sports Car', 'Mustang', 'Ford');

INSERT INTO cards (deck_id, front_text, back_text, keywords)
VALUES (1, 'Chevy Sports Car', 'Corvette', 'Chevrolet');


COMMIT TRANSACTION;