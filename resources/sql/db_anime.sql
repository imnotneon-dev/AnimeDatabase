CREATE DATABASE IF NOT EXISTS anime_db;
USE anime_db;

DROP TABLE IF EXISTS users;
CREATE TABLE Users(
	user_id INT AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(50) NOT NULL,
	password VARCHAR(255) NOT NULL,
	date_of_birth DATE NOT NULL,
	country VARCHAR(50) NOT NULL,
	top_genre VARCHAR(50) DEFAULT 'N/A',
	date_user_created DATE DEFAULT CURRENT_DATE,
	status VARCHAR(20) DEFAULT 'Active' --for archives
);

-- INSERTS FOR USERS
--INSERT INTO Users (username, password, date_of_birth, country)
--VALUES 

--('thistlezhi', 'zh15p4ssword', '2006-08-21', 'Malaysia', '2014-03-18'),
--('cerizz_', 'xLuvr8', '2014-06-16', 'Philippines', '2025-11-02'),
--('_zafibleh', 'm3oW!', '2005-10-28', 'Singapore', '2023-05-27'),
--('icep0p', 'xD_rul3z', '2006-07-27', 'Philippines', '2016-08-09'),
--('Mother_Coco', 'AFDG2028.switz!', '2004-12-18', 'Philippines', '2023-02-14'),
--('IJ.Orts', '1ts_IJcUhh', '2005-06-29', 'Malaysia', '2020-12-21'),
--('Azii95', 'k4tch0W!', '2005-08-14', 'Malaysia', '2019-07-30'),
--('YSA', 'gl@ssy!!', '2012-08-10', 'Singapore', '2024-05-04'),
--('SMHMyHead', 'r4ndOm_p@ssw0rd!', '2006-10-06', 'Malaysia', '2023-09-18'),
--('RYAN12345801', 's3cret!S4NT4', '2007-12-05', 'Philippines', '2025-01-12');


DROP TABLE IF EXISTS series;
CREATE TABLE series (
	series_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    genre VARCHAR(100) NOT NULL,
    release_year INT,
    total_episode_count INT,
    status_of_series VARCHAR(10)
);

DROP TABLE IF EXISTS episodes;
CREATE TABLE episodes (
    episode_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(70) NOT NULL,
    release_date DATE NOT NULL,
    synopsis VARCHAR(100),
    no_of_views INT DEFAULT 0,
    series_id INT
    runtime INT NOT NULL
    FOREIGN KEY (series_id) REFERENCES series(series_id)
);
DROP TABLE IF EXISTS actors;
CREATE TABLE actors (
    actors_id INT NOT NULL AUTO_INCREMENT,
    last_name VARCHAR(50),
    first_name VARCHAR(50),
    gender VARCHAR(10),
    date_of_birth DATE,
    place_of_birth VARCHAR(100),
    agency VARCHAR(100),
    PRIMARY KEY (actors_id)
);
DROP TABLE IF EXISTS likedEpisode;
CREATE TABLE likedEpisode(
  like_id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT NOT NULL,
  episode_id INT,
  date_added DATE DEFAULT CURRENT_DATE,
  FOREIGN KEY (user_id) REFERENCES Users(user_id),
  FOREIGN KEY (episode_id) REFERENCES Episodes(episode_id) --episode_id variable name depends on what variable name will be used in the episodes.sql file
);

--DROP TABLE IF EXISTS seriesEpisodes;
--CREATE TABLE series_episodes_t (
--    series_id INT NOT NULL,
--    episode_id INT NOT NULL,
--    PRIMARY KEY (series_id, episode_id),
--    FOREIGN KEY (series_id) REFERENCES series(series_id),
--    FOREIGN KEY (episode_id) REFERENCES episodes_t(episode_id)
--);

DROP TABLE IF EXISTS watchHistory;
CREATE TABLE watchHistory (
    watch_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    episode_id INT NOT NULL,
    watch_date DATE DEFAULT CURRENT_DATE,
    FOREIGN KEY (username) REFERENCES users(username),
    FOREIGN KEY (episode_id) REFERENCES episodes_t(episode_id)
);

DROP TABLE IF EXISTS actorSeries;
CREATE TABLE actorSeries (
    act_id INT NOT NULL AUTO_INCREMENT,
    actors_id INT NOT NULL,
    series_id INT NOT NULL,
    character_name VARCHAR(100),
    PRIMARY KEY (act_id),
    FOREIGN KEY (actors_id) REFERENCES actors(actors_id),
    FOREIGN KEY (series_id) REFERENCES series(series_id)
);


DROP TABLE IF EXISTS episodeReviews;
CREATE TABLE episodeReviews (
    review_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    episode_id INT NOT NULL,
    comment TEXT,
	date_reviewed DATE DEFAULT CURRENT_DATE,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (episode_id) REFERENCES episodes(episode_id)
);

INSERT INTO actors (last_name, first_name, gender, date_of_birth, place_of_birth, agency)
VALUES 
('Furukawa', 'Makoto', 'Male', '1989-09-29', 'Kumamoto Prefecture', 'Japan Toy''s Factory'), -- ONE PUNCH MAN -- !! also in KAGUYA SAMA
('Ishikawa', 'Kaito', 'Male', '1993-10-13', 'Bunkyo, Tokyo, Japan', 'Stay Luck'),
('Kaji', 'Yūki', 'Male', '1985-09-03', 'Tokyo, Japan', 'VIMS'),
('Hayami', 'Saori', 'Female', '1991-05-29', 'Tokyo, Japan', 'I''m Enterprise'), -- also in MY HERO ACADEMIA and SPY X FAMILY
('Sakamoto', 'Maaya', 'Female', '1980-03-31', 'Tokyo, Japan', 'Flying Dog'), -- OURAN HIGH SCHOOL HOST CLUB
('Miyano', 'Mamoru', 'Male', '1983-06-08', 'Saitama Prefecture, Japan', 'Ken-On'),
('Matsukaze', 'Masaya', 'Male', '1976-09-09', 'Fukushima Prefecture, Japan', 'Aoni Production'),
('Suzumura', 'Kenichi', 'Male', '1974-09-12', 'Niigata Prefecture, Japan', 'INTENTION'), -- also in DEMON SLAYER
('Hanae', 'Natsuki', 'Male', '1991-06-26', 'Kanagawa Prefecture, Japan', 'Across Entertainment'), -- DEMON SLAYER -- !! hanae natsuki is also tougen anki
('Kito', 'Akari', 'Female', '1994-10-16', 'Nagoya, Aichi Prefecture, Japan', 'Raccoon Dog'), 
('Shimono', 'Hiro', 'Male', '1980-04-21', 'Tokyo, Japan', 'I''m Enterprise'),
('Matsuoka', 'Yoshitsugu', 'Male', '1985-09-17', 'Chiba Prefecture, Japan', 'I''m Enterprise'), -- also in GACHIAKUTA and GIVEN -- ('Suzumura', 'Kenichi', 'Male', '1974-09-12', 'Niigata Prefecture, Japan', 'INTENTION'),
('Yamashita', 'Daiki', 'Male', '1989-09-07', 'Hamamatsu, Shizuoka Prefecture, Japan', 'Arts Vision'), -- MY HERO ACADEMIA
('Okamoto', 'Nobuhiko', 'Male', '1986-10-24', 'Tokyo, Japan', 'Raccoon Dog'),
('Sakura', 'Ayane', 'Female', '1994-01-29', 'Shibuya, Tokyo, Japan', 'Aoni Production'), -- ('Hayami', 'Saori', 'Female', '1991-05-29', 'Tokyo, Japan', 'I''m Enterprise'),
('Eguchi', 'Takuya', 'Male', '1987-05-22', 'Setagaya, Tokyo, Japan', '81 Produce'), -- SPY X FAMILY 
('Tanezaki', 'Atsumi', 'Female', '1990-09-27', 'Oita Prefecture, Japan', 'Tokyo Actor''s Consumer''s Cooperative Society / Haikyo'), -- 3
('Matsuda', 'Kenichiro', 'Male', '1978-09-22', 'Saitama Prefecture, Japan', 'Arts Vision'), 
('Ono', 'Kensho', 'Male', '1989-10-05', 'Fukuoka, Fukuoka Prefecture, Japan', 'Animo Produce'), -- ('Hayami', 'Saori', 'Female', '1991-05-29', 'Tokyo, Japan', 'I''m Enterprise'), 
('Ichikawa', 'Aoi', 'Male', '1991-10-02', 'Fukuoka, Japan', 'Office Osawa'), -- GACHIAKUTA
('Konishi', 'Katsuyuki', 'Male', '1973-04-21', 'Kanagawa Prefecture, Japan', 'Ken Production'), 
('Hanamori', 'Yumiri', 'Female', '1997-08-29', 'Saitama Prefecture, Japan', 'Aster Nine'),
('Morikawa', 'Toshiyuki', 'Male', '1967-05-26', 'Toyama Prefecture, Japan', 'Axlone'), -- ('Matsuoka', 'Yoshitsugu', 'Male', '1985-09-17', 'Chiba Prefecture, Japan', 'I''m Enterprise'),
('Ura', 'Kazuki', 'Male', '1995-10-18', 'Hyogo Prefecture, Japan', 'VIMS'), -- TOUGEN ANKI
('Kamiya', 'Hiroshi', 'Male', '1975-01-28', 'Yokohama, Kanagawa Prefecture, Japan', 'Aoni Production'), 
('Nishiyama', 'Kotaro', 'Male', '1989-06-07', 'Niigata Prefecture, Japan', 'Aoni Production'),
('Iwami', 'Manaka', 'Female', '1998-11-30', 'Hiroshima Prefecture, Japan', 'Raccoon Dog'), -- ('Hanae', 'Natsuki', 'Male', '1991-06-26', 'Kanagawa Prefecture, Japan', 'Across Entertainment'),
('Koga', 'Aoi', 'Female', '1998-08-24', 'Saga Prefecture, Japan', '81 Produce'), -- KAGUYA SAMA
('Kohara', 'Konomi', 'Female', '1992-06-28', 'Chiba Prefecture, Japan', 'Office Osawa'), 
('Suzuki', 'Ryota', 'Male', '1998-03-29', 'Aichi Prefecture, Japan', 'INTENTION'),
('Tomita', 'Miyu', 'Female', '1999-11-15', 'Saitama Prefecture, Japan', 'Amuse'), -- ('Furukawa', 'Makoto', 'Male', '1989-09-29', 'Kumamoto Prefecture', 'Japan Toy''s Factory'),
('Katou', 'Keiko', 'Female', '1969-02-06', 'Tokyo, Japan', 'Production Baobab'), -- PARADISE KISS
('Kawanishi', 'Kenn', 'Male', '1965-02-14', 'Tokyo, Japan', 'Aoni Production'), 
('Fukuyama', 'Jun', 'Male', '1978-11-26', 'Hiroshima Prefecture, Japan', 'Axlone'), 
('Taneda', 'Risa', 'Female', '1988-07-12', 'Tokyo, Japan', 'Office Osawa'), 
('Horie', 'Yui', 'Female', '1987-09-05', 'Tokyo, Japan', 'Arts Vision'), -- also in GIVEN
('Ichimichi', 'Mikako', 'Female', '1996-10-26', 'Tokyo, Japan', 'Haikyo'), -- GIVEN
('Fukuhara', 'Kaori', 'Female', '1981-07-29', 'Tokyo, Japan', '81 Produce'),
('Sugita', 'Tomokazu', 'Male', '1980-10-11', 'Tokyo, Japan', 'Haikyo'),  -- ('Horie', 'Yui', 'Female', '1987-09-05', 'Tokyo, Japan', 'Arts Vision'), -- ('Matsuoka', 'Yoshitsugu', 'Male', '1985-09-17', 'Chiba Prefecture, Japan', 'I''m Enterprise'), 
('Kobayashi', 'Sanae', 'Female', '1980-01-26', 'Hamakita, Shizuoka, Japan', 'Sigma Seven'), -- ELFEN LIED
('Suzuki', 'Chihiro', 'Male', '1977-02-17', 'Yamagata Prefecture, Japan', 'Haikyou'),
('Noto', 'Mamiko', 'Female', '1980-02-06', 'Kanazawa, Ishikawa, Japan', 'Office Osawa'), 
('Okamura', 'Akemi', 'Female', '1969-03-12', 'Tokyo, Japan', 'Mausu Promotion'), 
('Gibu', 'Yūko', 'Female', '1981-01-28', 'Kobe, Hyogo, Japan', 'Freelance'); 

INSERT INTO actorSeries(actors_id, series_id, character_name)
VALUES
(1, 1, 'Saitama'), -- ONE PUNCH MAN
(5, 2, 'Fujioka Haruhi'), -- OURAN
(9, 3, 'Tanjiro Kamado'), -- DEMON SLAYER
(13, 4, 'Izuku Midoriya'), -- MY HERO ACADEMIA
(16, 5, 'Loid Forger'), -- SPY X FAMILY
(20, 6, 'Rudo'), -- GACHI AKUTA
(24, 7, 'Shiki Ichinose') -- TOUGEN ANKI
(28, 8, 'Kaguya Shinomiya'), -- KAGUYA SAMA
(32, 9, 'Yukari Hayasaka/Caroline'), -- PARADISE KISS
(37, 10, 'Mafuyu Satou'), -- GIVEN
(40, 11, 'Kaede/Lucy/Nyu'); -- ELFEN LIED
