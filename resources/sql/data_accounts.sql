CREATE TABLE Users(
  user_id INT AUTO_INCREMENT,
  username VARCHAR(50) PRIMARY KEY,
  password VARCHAR(255) NOT NULL,
  date_of_birth DATE NOT NULL,
  country VARCHAR(50),
  top_genre VARCHAR(50),
  date_user_created DATE DEFAULT CURRENT_DATE
);

-- INSERTS

INSERT INTO Users (username, password, date_of_birth, country, top_genre)
VALUES ('thistlezhi', 'zh15p4ssword', '2006-08-21', 'China', 'Action');

INSERT INTO Users (username, password, date_of_birth, country, top_genre)
VALUES ('cerizz_', 'xLuvr8', '2014-06-16', 'Philippines', 'Comedy');

INSERT INTO Users (username, password, date_of_birth, country, top_genre)
VALUES ('_zafibleh', 'm3oW!', '2005-10-28', 'Canada', 'Romance');

INSERT INTO Users (username, password, date_of_birth, country, top_genre)
VALUES ('icep0p', 'xD_rul3z', '2005-07-27', 'South Korea', 'Horror');

INSERT INTO Users (username, password, date_of_birth, country, top_genre)
VALUES ('Mother_Coco', 'AFDG2028.switz!', '2004-12-18', 'Switzerland', 'Ecchi');

INSERT INTO Users (username, password, date_of_birth, country, top_genre)
VALUES ('IJ.Orts', '1ts_IJcUhh', '2005-06-29', 'Taipei', 'Supernatural');

INSERT INTO Users (username, password, date_of_birth, country, top_genre)
VALUES ('Azii95', 'k4tch0W!', '2005-08-14', 'America', 'Fantasy');

INSERT INTO Users (username, password, date_of_birth, country, top_genre)
VALUES ('SMHMyHead', 'r4ndOm_p@ssw0rd!', '2006-10-06', 'Japan', 'Adventure');

INSERT INTO Users (username, password, date_of_birth, country, top_genre)
VALUES ('aridesu', 'gl@ssy!!', '2012-08-10', 'Romania', 'Drama');

INSERT INTO Users (username, password, date_of_birth, country, top_genre)
VALUES ('RYAN12345801', 's3cret!S4NT4', '2007-12-05', 'Philippines', 'Sci-Fi');

